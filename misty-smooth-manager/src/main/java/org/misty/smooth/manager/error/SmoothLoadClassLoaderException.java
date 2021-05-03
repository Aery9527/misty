package org.misty.smooth.manager.error;

public class SmoothLoadClassLoaderException extends SmoothLoadException {
    public SmoothLoadClassLoaderException() {
    }

    public SmoothLoadClassLoaderException(String message) {
        super(message);
    }

    public SmoothLoadClassLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothLoadClassLoaderException(Throwable cause) {
        super(cause);
    }
}
