package org.misty.smooth.api.error;

public class SmoothEnvironmentException extends RuntimeException {

    public SmoothEnvironmentException() {
        super();
    }

    public SmoothEnvironmentException(String message) {
        super(message);
    }

    public SmoothEnvironmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothEnvironmentException(Throwable cause) {
        super(cause);
    }
}
