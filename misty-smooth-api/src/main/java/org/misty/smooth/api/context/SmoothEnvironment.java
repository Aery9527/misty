package org.misty.smooth.api.context;

import org.misty.smooth.api.mark.Guide;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Guide(needCross = true,
        implementationBy = Guide.Scope.CORE,
        usedBy = Guide.Scope.ANY
)
public interface SmoothEnvironment {

    // flag

    boolean containsFlag(String flag);

    boolean containsExactlyFlags(Collection<String> flags);

    boolean containsExactlyFlags(String... flags);

    boolean containsAllFlags(Collection<String> flags);

    boolean containsAllFlags(String... flags);

    boolean containsAnyFlags(Collection<String> flags);

    boolean containsAnyFlags(String... flags);

    Set<String> getFlags();

    // argument key

    boolean containsKey(String key);

    boolean containsExactlyKeys(Collection<String> keys);

    boolean containsExactlyKeys(String... keys);

    boolean containsAllKeys(Collection<String> keys);

    boolean containsAllKeys(String... keys);

    boolean containsAnyKeys(Collection<String> keys);

    boolean containsAnyKeys(String... keys);

    // arguments value

    boolean equalsValue(String key, String value);

    boolean equalsAnyValues(String key, Collection<String> values);

    boolean equalsAnyValues(String key, String... values);

    <Type> Type getValue(String key, Function<String, Type> transformer);

    String getValue(String key);

    Set<String> getKeys();

    Map<String, String> getArguments();

}
