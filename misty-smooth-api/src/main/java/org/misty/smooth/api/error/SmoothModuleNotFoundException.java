package org.misty.smooth.api.error;

public class SmoothModuleNotFoundException extends SmoothException {

    public SmoothModuleNotFoundException() {
    }

    public SmoothModuleNotFoundException(String message) {
        super(message);
    }

    public SmoothModuleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmoothModuleNotFoundException(Throwable cause) {
        super(cause);
    }

}
