package org.misty.smooth.manager.error;

public class SmoothLoadException extends SmoothManagerException {

    public static SmoothLoadException wrap(Throwable t) {
        if (t instanceof SmoothLoadException) {
            throw (SmoothLoadException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new SmoothLoadException(t);
        }
    }

    public SmoothLoadException() {
    }

    public SmoothLoadException(String message) {
        super(message);
    }

    public SmoothLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothLoadException(Throwable cause) {
        super(cause);
    }

}
