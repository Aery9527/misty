package org.misty.util.fi;

public interface FI {

    @SuppressWarnings("unchecked")
    static <ThrowableType extends Throwable> void wrap(FiRunnableThrow1<ThrowableType> runnableThrow1) throws ThrowableType {
        try {
            runnableThrow1.runOrThrow();
        } catch (Exception e) {
            try {
                throw (ThrowableType) e;
            } catch (ClassCastException cce) {
                cce.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    static <ReturnType, ThrowableType extends Throwable> ReturnType wrap(FiSupplier1<ReturnType, ThrowableType> supplierThrow1)
            throws ThrowableType {
        try {
            return supplierThrow1.getOrThrow();
        } catch (Exception e) {
            try {
                throw (ThrowableType) e;
            } catch (ClassCastException cce) {
                cce.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
