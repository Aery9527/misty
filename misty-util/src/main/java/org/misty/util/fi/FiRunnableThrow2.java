package org.misty.util.fi;

@FunctionalInterface
public interface FiRunnableThrow2<T1 extends Throwable, T2 extends Throwable> extends FI {

    void runOrThrow() throws Throwable;

    default void runOrHandle() throws T1, T2 {
        FI.wrap(this::runOrThrow);
    }

}
