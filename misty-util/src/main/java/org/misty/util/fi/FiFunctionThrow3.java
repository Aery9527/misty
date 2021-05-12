package org.misty.util.fi;

@FunctionalInterface
public interface FiFunctionThrow3<ArgType, ReturnType, T1 extends Throwable, T2 extends Throwable, T3 extends Throwable> extends FI {

    ReturnType applyOrThrow(ArgType arg) throws Throwable;

    default ReturnType applyOrHandle(ArgType arg) throws T1, T2, T3 {
        return FI.wrap(() -> applyOrThrow(arg));
    }

}
