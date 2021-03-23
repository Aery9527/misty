package org.misty.smooth.core.context.api;

import org.misty.smooth.api.context.SmoothEnvironment;

import java.util.List;

public interface SmoothCoreEnvironment extends SmoothEnvironment {

    String ARGUMENT_PREFIX = "misty.smooth";

    /**
     * -key is flag, --key=value is argument
     *
     * @param args arguments
     * @return arg not prefix with {@link #ARGUMENT_PREFIX} will return
     */
    List<String> parseArgument(String... args);

    /**
     * @param key flag key
     * @return true is key start with {@link #ARGUMENT_PREFIX}, false is not.
     */
    boolean addFlag(String key);

    /**
     * @param key    argument key
     * @param values argument values
     * @return true is key start with {@link #ARGUMENT_PREFIX}, false is not.
     */
    boolean addArgument(String key, String... values);

}
