package org.misty.smooth.manager.error;

public class SmoothLoadStateWrongException extends SmoothLoadException {
    public SmoothLoadStateWrongException() {
    }

    public SmoothLoadStateWrongException(String message) {
        super(message);
    }

    public SmoothLoadStateWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothLoadStateWrongException(Throwable cause) {
        super(cause);
    }
}
