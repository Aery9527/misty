package org.misty.util.error;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

public class MistyErrorUtil {

    public static Throwable ignoreProxyException(Throwable t) {
        if (t instanceof UndeclaredThrowableException || t instanceof InvocationTargetException) {
            Throwable cause = t.getCause();
            if (cause == null) {
                return t;
            } else {
                return ignoreProxyException(cause);
            }
        } else {
            return t;
        }
    }

}
