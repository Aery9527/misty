package org.misty.util.fi;

@FunctionalInterface
public interface FiBiFunctionThrow1<ArgType1, ArgType2, ReturnType, T1 extends Throwable> extends FI {

    ReturnType applyOrThrow(ArgType1 arg1, ArgType2 arg2) throws Throwable;

    default ReturnType applyOrHandle(ArgType1 arg1, ArgType2 arg2) throws T1 {
        return FI.wrap(() -> applyOrThrow(arg1, arg2));
    }

}
