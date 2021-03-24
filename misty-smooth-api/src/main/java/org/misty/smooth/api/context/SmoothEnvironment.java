package org.misty.smooth.api.context;

import java.util.*;

public interface SmoothEnvironment {

    boolean containsFlag(String flag);

    boolean containsAllFlags(Collection<String> flags);

    default boolean containsAllFlags(String... flags) {
        return containsAllFlags(Arrays.asList(flags));
    }

    boolean containsAnyFlags(Collection<String> flags);

    default boolean containsAnyFlags(String... flags) {
        return containsAnyFlags(Arrays.asList(flags));
    }

    Set<String> listFlags();

    boolean containsKey(String key);

    boolean containsAllKeys(Collection<String> keys);

    default boolean containsAllKeys(String... keys) {
        return containsAllKeys(Arrays.asList(keys));
    }

    boolean containsAnyKeys(Collection<String> keys);

    default boolean containsAnyKeys(String... keys) {
        return containsAnyKeys(Arrays.asList(keys));
    }

    boolean containsValue(String key, String value);

    boolean containsAllValues(String key, Collection<String> values);

    default boolean containsAllValues(String key, String... values) {
        return containsAllValues(key, Arrays.asList(values));
    }

    boolean containsAnyValues(String key, Collection<String> values);

    default boolean containsAnyValues(String key, String... values) {
        return containsAnyValues(key, Arrays.asList(values));
    }

    Optional<Set<String>> getValues(String key);

    Optional<String> getValue(String key);

    Set<String> listKeys();

    Map<String, Set<String>> listArguments();

}
