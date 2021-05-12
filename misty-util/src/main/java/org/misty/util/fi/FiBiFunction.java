package org.misty.util.fi;

@FunctionalInterface
public interface FiBiFunction<ArgType1, ArgType2, ReturnType> extends FI {

    ReturnType applyOrThrow(ArgType1 arg1, ArgType2 arg2) throws Throwable;

    default ReturnType applyOrHandle(ArgType1 arg1, ArgType2 arg2) {
        return FI.wrap(() -> applyOrThrow(arg1, arg2));
    }

}
