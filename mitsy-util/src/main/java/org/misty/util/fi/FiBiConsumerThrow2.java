package org.misty.util.fi;

@FunctionalInterface
public interface FiBiConsumerThrow2<ArgType1, ArgType2, T1 extends Throwable, T2 extends Throwable> extends FI {

    void acceptOrThrow(ArgType1 arg1, ArgType2 arg2) throws Exception;

    default void acceptOrHandle(ArgType1 arg1, ArgType2 arg2) throws T1, T2 {
        FI.wrap(() -> acceptOrThrow(arg1, arg2));
    }

}
