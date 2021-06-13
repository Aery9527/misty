package org.misty.smooth.api.vo;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * basically is Map&lt;String, Set&lt;String&gt;&gt;
 */
public final class SmoothUnscalableMap implements Map<String, SmoothUnscalableSet> {

    private final Map<String, SmoothUnscalableSet> map;

    public SmoothUnscalableMap() {
        this(false);
    }

    public SmoothUnscalableMap(boolean sequential) {
        this.map = sequential ? new TreeMap<>() : new HashMap<>();
    }

    /**
     * for unit test
     */
    protected SmoothUnscalableMap(Map<String, SmoothUnscalableSet> map) {
        this.map = map;
    }

    public SmoothUnscalableSet getOrPutDefault(String key) {
        return getOrPutDefault(key, false);
    }

    public SmoothUnscalableSet getOrPutDefault(String key, boolean sequential) {
        SmoothUnscalableSet content = this.map.get(key);
        if (content == null) {
            content = new SmoothUnscalableSet(sequential);
            this.map.put(key, content);
        }
        return content;
    }

    @Override
    public String toString() {
        return this.map.toString();
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public SmoothUnscalableSet get(Object key) {
        return this.map.get(key);
    }

    @Override
    public SmoothUnscalableSet put(String key, SmoothUnscalableSet value) {
        return this.map.put(key, value);
    }

    @Override
    public SmoothUnscalableSet remove(Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends SmoothUnscalableSet> m) {
        this.map.putAll(m);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<SmoothUnscalableSet> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<String, SmoothUnscalableSet>> entrySet() {
        return this.map.entrySet();
    }

    @Override
    public SmoothUnscalableSet getOrDefault(Object key, SmoothUnscalableSet defaultValue) {
        return this.map.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super SmoothUnscalableSet> action) {
        this.map.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super SmoothUnscalableSet, ? extends SmoothUnscalableSet> function) {
        this.map.replaceAll(function);
    }

    @Override
    public SmoothUnscalableSet putIfAbsent(String key, SmoothUnscalableSet value) {
        return this.map.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return this.map.remove(key, value);
    }

    @Override
    public boolean replace(String key, SmoothUnscalableSet oldValue, SmoothUnscalableSet newValue) {
        return this.map.replace(key, oldValue, newValue);
    }

    @Override
    public SmoothUnscalableSet replace(String key, SmoothUnscalableSet value) {
        return this.map.replace(key, value);
    }

    @Override
    public SmoothUnscalableSet computeIfAbsent(String key, Function<? super String, ? extends SmoothUnscalableSet> mappingFunction) {
        return this.map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public SmoothUnscalableSet computeIfPresent(String key, BiFunction<? super String, ? super SmoothUnscalableSet, ? extends SmoothUnscalableSet> remappingFunction) {
        return this.map.computeIfPresent(key, remappingFunction);
    }

    @Override
    public SmoothUnscalableSet compute(String key, BiFunction<? super String, ? super SmoothUnscalableSet, ? extends SmoothUnscalableSet> remappingFunction) {
        return this.map.compute(key, remappingFunction);
    }

    @Override
    public SmoothUnscalableSet merge(String key, SmoothUnscalableSet value, BiFunction<? super SmoothUnscalableSet, ? super SmoothUnscalableSet, ? extends SmoothUnscalableSet> remappingFunction) {
        return this.map.merge(key, value, remappingFunction);
    }
}


