package org.misty.util.fi;

@FunctionalInterface
public interface FiBiConsumer<ArgType1, ArgType2> extends FI {

    void acceptOrThrow(ArgType1 arg1, ArgType2 arg2) throws Exception;

    default void acceptOrHandle(ArgType1 arg1, ArgType2 arg2) {
        FI.wrap(() -> acceptOrThrow(arg1, arg2));
    }

}
