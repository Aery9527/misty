package org.misty.smooth.manager.error;

public class SmoothLoadTypeDuplicateException extends SmoothLoadException {
    public SmoothLoadTypeDuplicateException() {
    }

    public SmoothLoadTypeDuplicateException(String message) {
        super(message);
    }

    public SmoothLoadTypeDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothLoadTypeDuplicateException(Throwable cause) {
        super(cause);
    }
}
