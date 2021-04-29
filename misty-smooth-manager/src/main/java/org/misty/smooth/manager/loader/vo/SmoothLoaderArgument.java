package org.misty.smooth.manager.loader.vo;

import org.misty.smooth.api.constant.SmoothArgument;
import org.misty.smooth.api.context.SmoothEnvironment;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public final class SmoothLoaderArgument implements Map<String, Set<String>> {

    private Map<String, Set<String>> map = new TreeMap<>();

    public boolean putValue(String key, String value) {
        Set<String> set = get(key);
        return set.add(value);
    }

    public void putValue(Map<String, String> map) {
        map.forEach((k, v) -> {
            Set<String> set = get(k);
            set.add(v);
        });
    }

    public Optional<String> getValue(String key) {
        Set<String> set = this.map.get(key);
        if (set == null || set.isEmpty()) {
            return Optional.empty();
        } else {
            String value = set.iterator().next();
            return Optional.of(value);
        }
    }

    public String getValue(String key, String preset) {
        return getValue(key, (v) -> v, () -> preset);
    }

    public <ReturnType> ReturnType getValue(String key, Function<String, ReturnType> converter, Supplier<ReturnType> presetSupplier) {
        Set<String> set = this.map.get(key);
        if (set == null || set.isEmpty()) {
            return presetSupplier.get();
        } else {
            String value = set.iterator().next();
            return converter.apply(value);
        }
    }

    public void mergeEnvironment(SmoothEnvironment environment) {
        Set<String> flags = environment.getFlags();
        Map<String, String> arguments = environment.getArguments();

        getEnvironmentFlags().addAll(flags);
        putValue(arguments);
    }

    public Set<String> getEnvironmentFlags() {
        return get(SmoothArgument.LoaderArgument.ENVIRONMENT_FLAGS);
    }

    public void lock() {
        this.map.keySet().forEach((key) -> {
            Set<String> set = this.map.get(key);
            this.map.put(key, Collections.unmodifiableSet(set));
        });

        this.map = Collections.unmodifiableMap(this.map);
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

    @Deprecated
    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    /**
     * if key not exists, will create new <b>Set</b> put in <b>Map</b> and return
     *
     * @param key just key
     * @return value <b>Set</b>
     */
    @Override
    public Set<String> get(Object key) {
        Set<String> set = this.map.get(key);
        if (set == null) {
            set = new TreeSet<>();
            this.map.put((String) key, set);
        }

        return set;
    }

    /**
     * merge new <b>Set</b> into existed <b>Set</b>, if key not exists, reference {@link #get(Object)}
     *
     * @param key   just key
     * @param value new <b>Set</b>
     * @return new put in values
     */
    @Override
    public Set<String> put(String key, Set<String> value) {
        Set<String> set = get(key);

        Set<String> newPutinValues = new TreeSet<>(value);
        newPutinValues.removeAll(set);

        set.addAll(value);

        return newPutinValues;
    }

    @Override
    public Set<String> remove(Object key) {
        return this.map.remove(key);
    }

    /**
     * @param m this <b>Map</b> will merge into current <b>Map</b>
     */
    @Override
    public void putAll(Map<? extends String, ? extends Set<String>> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Deprecated
    @Override
    public Collection<Set<String>> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<String, Set<String>>> entrySet() {
        return this.map.entrySet();
    }

}
