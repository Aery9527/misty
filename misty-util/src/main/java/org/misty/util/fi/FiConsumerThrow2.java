package org.misty.util.fi;

@FunctionalInterface
public interface FiConsumerThrow2<ArgType, T1 extends Throwable, T2 extends Throwable> extends FI {

    void acceptOrThrow(ArgType arg) throws Throwable;

    default void acceptOrHandle(ArgType arg) throws T1, T2 {
        FI.wrap(() -> acceptOrThrow(arg));
    }

}
