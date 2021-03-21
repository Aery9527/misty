package org.misty.smooth.api.error;

public class SmoothCrossException extends RuntimeException {

    public SmoothCrossException() {
    }

    public SmoothCrossException(String message) {
        super(message);
    }

    public SmoothCrossException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothCrossException(Throwable cause) {
        super(cause);
    }
}
