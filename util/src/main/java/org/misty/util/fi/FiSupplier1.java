package org.misty.util.fi;

@FunctionalInterface
public interface FiSupplier1<ReturnType, T1 extends Throwable> extends FI {

    ReturnType getOrThrow() throws Exception;

    default ReturnType getOrHandle() throws T1 {
        return FI.wrap(this);
    }

}
