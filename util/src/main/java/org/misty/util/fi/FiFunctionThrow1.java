package org.misty.util.fi;

@FunctionalInterface
public interface FiFunctionThrow1<ArgType, ReturnType, T1 extends Throwable> extends FI {

    ReturnType applyOrThrow(ArgType arg) throws Exception;

    default ReturnType applyOrHandle(ArgType arg) throws T1 {
        return FI.wrap(() -> applyOrThrow(arg));
    }

}
