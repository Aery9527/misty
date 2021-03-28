package org.misty.smooth.core.context.api;

import org.misty.smooth.api.context.SmoothEnvironment;

import java.util.*;

public interface SmoothCoreEnvironment extends SmoothEnvironment {

    /**
     * "--aaa" is flag "aaa", "-key=value" is argument "key:value",
     * for example, args [--kerker, -aery=handsome], then kerker will classified to flag,
     * and aery=handsome will to argument aery:handsome.
     *
     * @param args arguments
     * @return can't parse args will return
     */
    Set<String> parseArgument(Collection<String> args);

    /**
     * {@link #parseArgument(Collection)}
     */
    default Set<String> parseArgument(String... args) {
        if (args == null) {
            return parseArgument((Collection<String>) null);
        } else {
            return parseArgument(Arrays.asList(args));
        }
    }

    void addFlag(String flag);

    void addFlags(Collection<String> flags);

    default void addFlags(String... flags) {
        if (flags == null) {
            addFlags((Collection<String>) null);
        } else {
            addFlags(Arrays.asList(flags));
        }
    }

    void addArgument(String key, String value);

    void addArguments(String key, Collection<String> values);

    default void addArguments(String key, String... values) {
        if (values == null) {
            addArguments(key, (Collection<String>) null);
        } else {
            addArguments(key, Arrays.asList(values));
        }
    }

    void addArguments(Map<String, String> args);

}
