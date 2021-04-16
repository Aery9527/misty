package org.misty.smooth.core.tool;

import org.misty.util.ex.ReentrantLockEx;
import org.misty.util.fi.FiRunnable;
import org.misty.util.verify.Examiner;

import java.util.*;
import java.util.function.BiConsumer;

public class SmoothIdGearingMap<Key2 extends Comparable<Key2>, Value> {

    public static class OldValue<Key2 extends Comparable<Key2>, Value> {
        private final String key1;
        private final Key2 key2;
        private final Value value;

        public OldValue(String key1, Key2 key2, Value value) {
            this.key1 = key1;
            this.key2 = key2;
            this.value = value;
        }

        public String getKey1() {
            return key1;
        }

        public Key2 getKey2() {
            return key2;
        }

        public Value getValue() {
            return value;
        }
    }

    private final ReentrantLockEx lock = new ReentrantLockEx();

    private volatile Map<String, Key2> map1 = new TreeMap<>();

    private volatile Map<Key2, Value> map2 = new TreeMap<>();

    public Optional<OldValue<Key2, Value>> put(String key1, Key2 key2, Value value) {
        Examiner.refuseNullAndEmpty("key1", key1);
        Examiner.refuseNullAndEmpty("key2", key2);
        Examiner.refuseNullAndEmpty("value", value);

        return this.lock.use(() -> {
            boolean key1Exists = this.map1.containsKey(key1);
            boolean key2Exists = this.map2.containsKey(key2);

            boolean notGearing = key1Exists ^ key2Exists;
            if (notGearing) {
                // FIXME throw exception
            }

            Key2 oldKey = this.map1.put(key1, key2);
            Value oldValue = this.map2.put(key2, value);

            if (oldKey == null) {
                return Optional.empty();
            } else {
                return Optional.of(new OldValue<>(key1, oldKey, oldValue));
            }
        });
    }

    public Key2 getKey2(String key1) {
        Examiner.refuseNullAndEmpty("key1", key1);
        return this.map1.get(key1);
    }

    public Value getValue(String key1) {
        Key2 key2 = getKey2(key1);
        return key2 == null ? null : getValue(key2);
    }

    public Value getValue(Key2 key2) {
        Examiner.refuseNullAndEmpty("key2", key2);
        return this.map2.get(key2);
    }

    public Set<Key2> listKey2() {
        return Collections.unmodifiableSet(this.map2.keySet());
    }

    public void lock(FiRunnable action) {
        this.lock.use(action);
    }

    public void foreach(BiConsumer<Key2, Value> consumer) {
        lock(() -> this.map2.forEach(consumer));
    }

    public void close() {
        this.map1 = null;
        this.map2 = null;
    }

}
