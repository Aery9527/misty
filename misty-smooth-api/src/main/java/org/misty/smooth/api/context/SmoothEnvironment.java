package org.misty.smooth.api.context;

import java.util.*;

public interface SmoothEnvironment {

    boolean containsFlag(String key);

    boolean containsAllFlags(Collection<String> keys);

    default boolean containsAllFlags(String... keys) {
        return containsAllFlags(Arrays.asList(keys));
    }

    boolean containsAnyFlags(Collection<String> keys);

    default boolean containsAnyFlags(String... keys) {
        return containsAnyFlags(Arrays.asList(keys));
    }

    Set<String> listFlags();

    boolean containsKey(String key);

    boolean containsAllKeys(Collection<String> keys);

    default boolean containsAllKeys(String... keys) {
        return containsAllKeys(Arrays.asList(keys));
    }

    boolean containsAnyKey(Collection<String> keys);

    default boolean containsAnyKey(String... keys) {
        return containsAnyKey(Arrays.asList(keys));
    }

    Optional<Set<String>> getValues(String key);

    Optional<String> getValue(String key);

    Set<String> listKeys();

    Map<String, Set<String>> listArguments();

}
