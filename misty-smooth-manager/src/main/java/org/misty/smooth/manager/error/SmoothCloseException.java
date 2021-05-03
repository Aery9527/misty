package org.misty.smooth.manager.error;

public class SmoothCloseException extends SmoothManagerException {

    public static SmoothCloseException wrap(Throwable t) {
        if (t instanceof SmoothCloseException) {
            throw (SmoothCloseException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new SmoothCloseException(t);
        }
    }

    public SmoothCloseException() {
    }

    public SmoothCloseException(String message) {
        super(message);
    }

    public SmoothCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothCloseException(Throwable cause) {
        super(cause);
    }
}
