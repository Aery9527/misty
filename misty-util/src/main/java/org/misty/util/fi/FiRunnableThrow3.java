package org.misty.util.fi;

@FunctionalInterface
public interface FiRunnableThrow3<T1 extends Throwable, T2 extends Throwable, T3 extends Throwable> extends FI {

    void runOrThrow() throws Throwable;

    default void runOrHandle() throws T1, T2, T3 {
        FI.wrap(this::runOrThrow);
    }

}
