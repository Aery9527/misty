package org.misty.smooth.api.service.vo;

import java.util.*;

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

}
