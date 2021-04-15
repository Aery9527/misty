package org.misty.util.fi;

public interface FI {

    @SuppressWarnings("unchecked")
    static <ThrowableType extends Throwable> void wrap(FiRunnableThrow1<ThrowableType> runnableThrow1) throws ThrowableType {
        try {
            runnableThrow1.runOrThrow();
        } catch (Exception e) {
            try {
                throw (ThrowableType) e; // ThrowableType被外部呼叫時決定為A-exception, 但真正執行時拋出B-exception時, 這裡轉型會過...滿神奇的...
            } catch (ClassCastException cce) {
                cce.printStackTrace();
                throw new RuntimeException(e); // 所以基本上不會跑到這...因為不會轉型錯誤...
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
                throw (ThrowableType) e; // ThrowableType被外部呼叫時決定為A-exception, 但真正執行時拋出B-exception時, 這裡轉型會過...滿神奇的...
            } catch (ClassCastException cce) {
                cce.printStackTrace();
                throw new RuntimeException(e); // 所以基本上不會跑到這...因為不會轉型錯誤...
            }
        }
    }

}
