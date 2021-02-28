package org.misty.util.fi;

@FunctionalInterface
public interface FiFunction<ArgType, ReturnType> extends FI {

    ReturnType applyOrThrow(ArgType arg) throws Exception;

    default ReturnType applyOrHandle(ArgType arg) {
        return FI.wrap(() -> applyOrThrow(arg));
    }

}
