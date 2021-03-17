package org.misty.util.fi;

@FunctionalInterface
public interface FiFunctionThrow2<ArgType, ReturnType, T1 extends Throwable, T2 extends Throwable> extends FI {

    ReturnType applyOrThrow(ArgType arg) throws Exception;

    default ReturnType applyOrHandle(ArgType arg) throws T1, T2 {
        return FI.wrap(() -> applyOrThrow(arg));
    }

}
