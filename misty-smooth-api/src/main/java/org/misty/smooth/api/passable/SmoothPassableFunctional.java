package org.misty.smooth.api.passable;

public interface SmoothPassableFunctional {

    static <ThrowableType extends Throwable> void wrap(SmoothPassableRunnable runnable) throws ThrowableType {
        wrap(() -> {
            runnable.runOrThrow();
            return null;
        });
    }

    @SuppressWarnings("unchecked")
    static <ReturnType, ThrowableType extends Throwable> ReturnType wrap(SmoothPassableSupplier<ReturnType> supplier)
            throws ThrowableType {
        try {
            return supplier.getOrThrow();
        } catch (Throwable t) {
            try {
                throw (ThrowableType) t;
            } catch (ClassCastException cce) {
                cce.printStackTrace();
                throw new RuntimeException(t);
            }
        }
    }

}
