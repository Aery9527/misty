package org.misty.util.fi;

@FunctionalInterface
public interface FiBiFunctionThrow3<ArgType1, ArgType2, ReturnType, T1 extends Throwable, T2 extends Throwable, T3 extends Throwable> extends FI {

    ReturnType applyOrThrow(ArgType1 arg1, ArgType2 arg2) throws Throwable;

    default ReturnType applyOrHandle(ArgType1 arg1, ArgType2 arg2) throws T1, T2, T3 {
        return FI.wrap(() -> applyOrThrow(arg1, arg2));
    }

}
