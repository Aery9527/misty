package org.misty.util.fi;

@FunctionalInterface
public interface FiConsumerThrow1<ArgType, T1 extends Throwable> extends FI {

    void acceptOrThrow(ArgType arg) throws Throwable;

    default void acceptOrHandle(ArgType arg) throws T1 {
        FI.wrap(() -> acceptOrThrow(arg));
    }

}
