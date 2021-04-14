package org.misty.smooth.manager.error;

import org.misty.smooth.api.error.SmoothException;

public class SmoothLoadException extends SmoothException {

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
