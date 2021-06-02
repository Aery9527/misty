package org.misty.smooth.api.passable;

@FunctionalInterface
public interface SmoothPassableSupplier<ReturnType> extends SmoothPassableFunctional {

    ReturnType getOrThrow() throws Throwable;

    default ReturnType getOrHandle() {
        return SmoothPassableFunctional.wrap(this);
    }

}
