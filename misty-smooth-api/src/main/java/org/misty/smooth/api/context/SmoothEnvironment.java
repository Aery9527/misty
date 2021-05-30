package org.misty.smooth.api.context;

import org.misty.smooth.api.mark.Guide;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Guide(
        implementationBy = Guide.Scope.CORE,
        usedBy = Guide.Scope.ANY
)
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

    boolean equalsValue(String key, String value);

    boolean equalsAnyValues(String key, Collection<String> values);

    default boolean equalsAnyValues(String key, String... values) {
        if (values == null) {
            return equalsAnyValues(key, (Collection<String>) null);
        } else {
            return equalsAnyValues(key, Arrays.asList(values));
        }
    }

    <Type> Type getValue(String key, Function<String, Type> transformer);

    default String getValue(String key) {
        return getValue(key, (v) -> v);
    }

    Set<String> getKeys();

    Map<String, String> getArguments();

}
