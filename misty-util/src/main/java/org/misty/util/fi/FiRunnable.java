package org.misty.util.fi;

@FunctionalInterface
public interface FiRunnable extends FI {

    void runOrThrow() throws Throwable;

    default void runOrHandle() {
        FI.wrap(this::runOrThrow);
    }

}
