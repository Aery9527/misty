package org.misty.smooth.manager.loader.vo;

import java.util.*;

public final class SmoothLoaderArgument implements Map<String, Set<String>> {

    private final Map<String, Set<String>> map = new TreeMap<>();

    public String getFirstValue() {
        // TODO
        return null;
    }

    private Set<String> supplySet() {
        return new TreeSet<>();
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
    public Set<String> get(Object key) {
        Set<String> set = this.map.get(key);
        if (set == null) {
            set = supplySet();
            this.map.put((String) key, set);
        }

        return set;
    }

    @Override
    public Set<String> put(String key, Set<String> value) {
        return this.map.put(key, value);
    }

    @Override
    public Set<String> remove(Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Set<String>> m) {
        m.forEach((k, s) -> {
            Set<String> set = get(k);
            set.addAll(s);
        });
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
    public Collection<Set<String>> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<String, Set<String>>> entrySet() {
        return this.map.entrySet();
    }

}
