package org.misty.smooth.api.error;

public class SmoothException extends RuntimeException {

    public SmoothException() {
    }

    public SmoothException(String message) {
        super(message);
    }

    public SmoothException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothException(Throwable cause) {
        super(cause);
    }

}
