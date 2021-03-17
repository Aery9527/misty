package org.misty.util.fi;

@FunctionalInterface
public interface FiSupplier3<ReturnType, T1 extends Throwable, T2 extends Throwable, T3 extends Throwable> extends FI {

    ReturnType getOrThrow() throws Exception;

    default ReturnType getOrHandle() throws T1, T2, T3 {
        return FI.wrap(this::getOrThrow);
    }

}
