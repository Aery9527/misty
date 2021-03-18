package org.misty.util.fi;

@FunctionalInterface
public interface FiConsumerThrow3<ArgType, T1 extends Throwable, T2 extends Throwable, T3 extends Throwable> extends FI {

    void acceptOrThrow(ArgType arg) throws Exception;

    default void acceptOrHandle(ArgType arg) throws T1, T2, T3 {
        FI.wrap(() -> acceptOrThrow(arg));
    }

}
