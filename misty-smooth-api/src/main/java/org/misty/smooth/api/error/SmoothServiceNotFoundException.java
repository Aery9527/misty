package org.misty.smooth.api.error;

public class SmoothServiceNotFoundException extends SmoothException {

    public SmoothServiceNotFoundException() {
    }

    public SmoothServiceNotFoundException(String message) {
        super(message);
    }

    public SmoothServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothServiceNotFoundException(Throwable cause) {
        super(cause);
    }

}
