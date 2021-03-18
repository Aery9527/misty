package org.misty.util.fi;

@FunctionalInterface
public interface FiRunnableThrow1<T1 extends Throwable> extends FI {

    void runOrThrow() throws Exception;

    default void runOrHandle() throws T1 {
        FI.wrap(this);
    }

}
