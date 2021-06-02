package org.misty.smooth.api.passable;

@FunctionalInterface
public interface SmoothPassableRunnable extends SmoothPassableFunctional {

    void runOrThrow() throws Throwable;

    default void acceptOrHandle() {
        SmoothPassableFunctional.wrap(this);
    }

}
