package org.misty.smooth.core.context.api;

import org.misty.smooth.api.context.SmoothEnvironment;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface SmoothCoreEnvironment extends SmoothEnvironment {

    String ARGUMENT_PREFIX = "misty.smooth";

    /**
     * --key is flag, -key=value is argument
     *
     * @param args arguments
     * @return arg not prefix with {@link #ARGUMENT_PREFIX} will return
     */
    List<String> parseArgument(Collection<String> args);

    /**
     * {@link #parseArgument(Collection)}
     */
    default List<String> parseArgument(String... args) {
        return parseArgument(Arrays.asList(args));
    }

    /**
     * @param flag flag name
     * @return true is key start with {@link #ARGUMENT_PREFIX}, false is not.
     */
    boolean addFlag(String flag);

    List<String> addFlags(Collection<String> flags);

    default List<String> addFlags(String... flags) {
        return addFlags(Arrays.asList(flags));
    }

    boolean addArgument(String key, String value);

    /**
     * @param key    argument key
     * @param values argument values
     * @return true is key start with {@link #ARGUMENT_PREFIX}, false is not.
     */
    boolean addArguments(String key, List<String> values);

    /**
     * {@link #addArguments(String, List)}
     */
    default boolean addArguments(String key, String... values) {
        return addArguments(key, Arrays.asList(values));
    }

}