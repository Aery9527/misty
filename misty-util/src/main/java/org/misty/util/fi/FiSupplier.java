package org.misty.util.fi;

@FunctionalInterface
public interface FiSupplier<ReturnType> extends FI {

    ReturnType getOrThrow() throws Throwable;

    default ReturnType getOrHandle() {
        return FI.wrap(this::getOrThrow);
    }

}
