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

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public SmoothServicePayloadHeaderContent get(Object key) {
        return null;
    }

    @Override
    public SmoothServicePayloadHeaderContent put(String key, SmoothServicePayloadHeaderContent value) {
        return null;
    }

    @Override
    public SmoothServicePayloadHeaderContent remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends SmoothServicePayloadHeaderContent> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<SmoothServicePayloadHeaderContent> values() {
        return null;
    }

    @Override
    public Set<Entry<String, SmoothServicePayloadHeaderContent>> entrySet() {
        return null;
    }

    @Override
    public SmoothServicePayloadHeaderContent getOrDefault(Object key, SmoothServicePayloadHeaderContent defaultValue) {
        return Map.super.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super SmoothServicePayloadHeaderContent> action) {
        Map.super.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super SmoothServicePayloadHeaderContent, ? extends SmoothServicePayloadHeaderContent> function) {
        Map.super.replaceAll(function);
    }

    @Override
    public SmoothServicePayloadHeaderContent putIfAbsent(String key, SmoothServicePayloadHeaderContent value) {
        return Map.super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return Map.super.remove(key, value);
    }

    @Override
    public boolean replace(String key, SmoothServicePayloadHeaderContent oldValue, SmoothServicePayloadHeaderContent newValue) {
        return Map.super.replace(key, oldValue, newValue);
    }

    @Override
    public SmoothServicePayloadHeaderContent replace(String key, SmoothServicePayloadHeaderContent value) {
        return Map.super.replace(key, value);
    }

    @Override
    public SmoothServicePayloadHeaderContent computeIfAbsent(String key, Function<? super String, ? extends SmoothServicePayloadHeaderContent> mappingFunction) {
        return Map.super.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public SmoothServicePayloadHeaderContent computeIfPresent(String key, BiFunction<? super String, ? super SmoothServicePayloadHeaderContent, ? extends SmoothServicePayloadHeaderContent> remappingFunction) {
        return Map.super.computeIfPresent(key, remappingFunction);
    }

    @Override
    public SmoothServicePayloadHeaderContent compute(String key, BiFunction<? super String, ? super SmoothServicePayloadHeaderContent, ? extends SmoothServicePayloadHeaderContent> remappingFunction) {
        return Map.super.compute(key, remappingFunction);
    }

    @Override
    public SmoothServicePayloadHeaderContent merge(String key, SmoothServicePayloadHeaderContent value, BiFunction<? super SmoothServicePayloadHeaderContent, ? super SmoothServicePayloadHeaderContent, ? extends SmoothServicePayloadHeaderContent> remappingFunction) {
        return Map.super.merge(key, value, remappingFunction);
    }
}
