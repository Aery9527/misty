package org.misty.smooth.core.tool;

import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.core.error.SmoothCoreError;
import org.misty.util.error.MistyException;
import org.misty.util.ex.ReentrantLockEx;
import org.misty.util.fi.FiRunnable;
import org.misty.util.verify.Examiner;

import java.util.*;
import java.util.function.BiConsumer;

public class SmoothIdLinkageMap<Key extends SmoothId<Key>, Value> {

    public static class ErrorMsgFormat {
        public static final String SMOOTH_ID_LINKAGE_ERROR = "linkage key error with %s and %s";
    }

    public static class OldTuple<Key extends SmoothId<Key>, Value> {
        private final String typeKey;
        private final Key key;
        private final Value value;

        public OldTuple(Key key, Value value) {
            this.typeKey = key.getTypeKey();
            this.key = key;
            this.value = value;
        }

        public String getTypeKey() {
            return typeKey;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }
    }

    private final ReentrantLockEx lock = new ReentrantLockEx();

    private volatile Map<String, Key> map1 = new HashMap<>();

    private volatile Map<Key, Value> map2 = new HashMap<>();

    public Optional<OldTuple<Key, Value>> put(Key key, Value value) throws MistyException {
        Examiner.refuseNullAndEmpty("key", key);
        Examiner.refuseNullAndEmpty("value", value);

        return this.lock.use(() -> {
            String typeKey = key.getTypeKey();
            Key oldKey = this.map1.put(typeKey, key);
            Value oldValue = this.map2.put(key, value);

            boolean key1Exists = oldKey != null;
            boolean key2Exists = oldValue != null;

            boolean notLinkage = key1Exists ^ key2Exists;
            if (notLinkage) {
                String msg = String.format(ErrorMsgFormat.SMOOTH_ID_LINKAGE_ERROR, oldKey, key);
                throw SmoothCoreError.SMOOTH_ID_LINKAGE_ERROR.thrown(msg);
            }

            if (oldKey == null) {
                return Optional.empty();
            } else {
                return Optional.of(new OldTuple<>(oldKey, oldValue));
            }
        });
    }

//    public Key getKey(String typeKey) {
//        Examiner.refuseNullAndEmpty("typeKey", typeKey);
//        return this.map1.get(typeKey);
//    }
//
//    public Value getValue(String typeKey) {
//        Key key = getKey(typeKey);
//        return key == null ? null : getValue(key);
//    }
//
//    public Value getValue(Key key) {
//        Examiner.refuseNullAndEmpty("key", key);
//        return this.map2.get(key);
//    }

    public Set<Key> listKey() {
        TreeSet<Key> keys = new TreeSet<>(this.map2.keySet());
        return Collections.unmodifiableSet(keys);
    }

    public void atomic(FiRunnable action) {
        this.lock.use(action);
    }

    public void foreach(BiConsumer<Key, Value> consumer) {
        this.map2.forEach(consumer);
    }

    public void close() {
        this.map1 = null;
        this.map2 = null;
    }

}
