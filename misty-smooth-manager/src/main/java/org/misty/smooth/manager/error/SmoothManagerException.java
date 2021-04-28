package org.misty.smooth.manager.error;

public class SmoothManagerException extends RuntimeException {

    public SmoothManagerException() {
    }

    public SmoothManagerException(String message) {
        super(message);
    }

    public SmoothManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothManagerException(Throwable cause) {
        super(cause);
    }

}
