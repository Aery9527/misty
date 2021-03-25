package org.misty.smooth.manager.error;

public class SmoothLoadException extends RuntimeException {

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