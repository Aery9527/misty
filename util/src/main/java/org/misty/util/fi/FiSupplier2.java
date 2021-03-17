package org.misty.util.fi;

@FunctionalInterface
public interface FiSupplier2<ReturnType, T1 extends Throwable, T2 extends Throwable> extends FI {

    ReturnType getOrThrow() throws Exception;

    default ReturnType getOrHandle() throws T1, T2 {
        return FI.wrap(this::getOrThrow);
    }

}
