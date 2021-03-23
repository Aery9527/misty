package org.misty.smooth.api.context;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface SmoothEnvironment {

    boolean containsFlag(String key);

    boolean containsAllFlags(String... keys);

    boolean containsAnyFlags(String... keys);

    Set<String> listFlags();

    boolean containsKey(String key);

    Optional<Set<String>> getValue(String key);

    Optional<String> getFirstValue(String key);

    Set<String> listKeys();

    Map<String, Set<String>> listArguments();

}
