package org.misty.util.fi;

@FunctionalInterface
public interface FiConsumer<ArgType> extends FI {

    void acceptOrThrow(ArgType arg) throws Throwable;

    default void acceptOrHandle(ArgType arg) {
        FI.wrap(() -> acceptOrThrow(arg));
    }

}
