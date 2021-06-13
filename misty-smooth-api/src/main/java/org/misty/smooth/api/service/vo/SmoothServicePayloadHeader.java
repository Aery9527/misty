package org.misty.smooth.api.service.vo;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class SmoothServicePayloadHeader implements Map<String, SmoothServicePayloadHeaderContent> {

    private final Map<String, SmoothServicePayloadHeaderContent> map;

    public SmoothServicePayloadHeader() {
        this(false);
    }

    public SmoothServicePayloadHeader(boolean sequential) {
        this.map = sequential ? new TreeMap<>() : new HashMap<>();
    }

    /**
     * for unit test
     */
    protected SmoothServicePayloadHeader(Map<String, SmoothServicePayloadHeaderContent> map) {
        this.map = map;
    }

    public SmoothServicePayloadHeaderContent getOrPutDefault(String key) {
        return getOrPutDefault(key, false);
    }

    public SmoothServicePayloadHeaderContent getOrPutDefault(String key, boolean sequential) {
        SmoothServicePayloadHeaderContent content = this.map.get(key);
        if (content == null) {
            content = new SmoothServicePayloadHeaderContent(sequential);
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
    public SmoothServicePayloadHeaderContent get(Object key) {
        return this.map.get(key);
    }

    @Override
    public SmoothServicePayloadHeaderContent put(String key, SmoothServicePayloadHeaderContent value) {
        return this.map.put(key, value);
    }

    @Override
    public SmoothServicePayloadHeaderContent remove(Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends SmoothServicePayloadHeaderContent> m) {
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
    public Collection<SmoothServicePayloadHeaderContent> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<String, SmoothServicePayloadHeaderContent>> entrySet() {
        return this.map.entrySet();
    }

    @Override
    public SmoothServicePayloadHeaderContent getOrDefault(Object key, SmoothServicePayloadHeaderContent defaultValue) {
        return this.map.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super SmoothServicePayloadHeaderContent> action) {
        this.map.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super SmoothServicePayloadHeaderContent, ? extends SmoothServicePayloadHeaderContent> function) {
        this.map.replaceAll(function);
    }

    @Override
    public SmoothServicePayloadHeaderContent putIfAbsent(String key, SmoothServicePayloadHeaderContent value) {
        return this.map.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return this.map.remove(key, value);
    }

    @Override
    public boolean replace(String key, SmoothServicePayloadHeaderContent oldValue, SmoothServicePayloadHeaderContent newValue) {
        return this.map.replace(key, oldValue, newValue);
    }

    @Override
    public SmoothServicePayloadHeaderContent replace(String key, SmoothServicePayloadHeaderContent value) {
        return this.map.replace(key, value);
    }

    @Override
    public SmoothServicePayloadHeaderContent computeIfAbsent(String key, Function<? super String, ? extends SmoothServicePayloadHeaderContent> mappingFunction) {
        return this.map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public SmoothServicePayloadHeaderContent computeIfPresent(String key, BiFunction<? super String, ? super SmoothServicePayloadHeaderContent, ? extends SmoothServicePayloadHeaderContent> remappingFunction) {
        return this.map.computeIfPresent(key, remappingFunction);
    }

    @Override
    public SmoothServicePayloadHeaderContent compute(String key, BiFunction<? super String, ? super SmoothServicePayloadHeaderContent, ? extends SmoothServicePayloadHeaderContent> remappingFunction) {
        return this.map.compute(key, remappingFunction);
    }

    @Override
    public SmoothServicePayloadHeaderContent merge(String key, SmoothServicePayloadHeaderContent value, BiFunction<? super SmoothServicePayloadHeaderContent, ? super SmoothServicePayloadHeaderContent, ? extends SmoothServicePayloadHeaderContent> remappingFunction) {
        return this.map.merge(key, value, remappingFunction);
    }
}
