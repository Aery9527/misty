package org.misty.smooth.api.context;

import java.util.*;

public interface SmoothEnvironment {

    // flag

    boolean containsFlag(String flag);

    boolean containsExactlyFlags(Collection<String> flags);

    default boolean containsExactlyFlags(String... flags) {
        if (flags == null) {
            return containsExactlyFlags((Collection<String>) null);
        } else {
            return containsExactlyFlags(Arrays.asList(flags));
        }
    }

    boolean containsAllFlags(Collection<String> flags);

    default boolean containsAllFlags(String... flags) {
        if (flags == null) {
            return containsAllFlags((Collection<String>) null);
        } else {
            return containsAllFlags(Arrays.asList(flags));
        }
    }

    boolean containsAnyFlags(Collection<String> flags);

    default boolean containsAnyFlags(String... flags) {
        if (flags == null) {
            return containsAnyFlags((Collection<String>) null);
        } else {
            return containsAnyFlags(Arrays.asList(flags));
        }
    }

    Set<String> getFlags();

    // argument key

    boolean containsKey(String key);

    boolean containsExactlyKeys(Collection<String> keys);

    default boolean containsExactlyKeys(String... keys) {
        if (keys == null) {
            return containsExactlyKeys((Collection<String>) null);
        } else {
            return containsExactlyKeys(Arrays.asList(keys));
        }
    }

    boolean containsAllKeys(Collection<String> keys);

    default boolean containsAllKeys(String... keys) {
        if (keys == null) {
            return containsAllKeys((Collection<String>) null);
        } else {
            return containsAllKeys(Arrays.asList(keys));
        }
    }

    boolean containsAnyKeys(Collection<String> keys);

    default boolean containsAnyKeys(String... keys) {
        if (keys == null) {
            return containsAnyKeys((Collection<String>) null);
        } else {
            return containsAnyKeys(Arrays.asList(keys));
        }
    }

    // arguments value

    boolean containsValue(String key, String value);

    boolean containsExactlyValues(String key, Collection<String> values);

    default boolean containsExactlyValues(String key, String... values) {
        if (values == null) {
            return containsExactlyValues(key, (Collection<String>) null);
        } else {
            return containsExactlyValues(key, Arrays.asList(values));
        }
    }

    boolean containsAllValues(String key, Collection<String> values);

    default boolean containsAllValues(String key, String... values) {
        if (values == null) {
            return containsAllValues(key, (Collection<String>) null);
        } else {
            return containsAllValues(key, Arrays.asList(values));
        }
    }

    boolean containsAnyValues(String key, Collection<String> values);

    default boolean containsAnyValues(String key, String... values) {
        if (values == null) {
            return containsAnyValues(key, (Collection<String>) null);
        } else {
            return containsAnyValues(key, Arrays.asList(values));
        }
    }

    Set<String> getValues(String key);

    Set<String> getKeys();

    Map<String, Set<String>> getArguments();

}
