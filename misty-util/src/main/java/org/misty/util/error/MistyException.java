package org.misty.util.error;

public class MistyException extends RuntimeException {

    private final MistyErrorDefinition errorDefinition;

    public MistyException(MistyErrorDefinition errorDefinition) {
        super(errorDefinition.getDescription());
        this.errorDefinition = errorDefinition;
    }

    public MistyException(MistyErrorDefinition errorDefinition, String message) {
        super(errorDefinition.getDescription() + ": " + message);
        this.errorDefinition = errorDefinition;
    }

    public MistyException(MistyErrorDefinition errorDefinition, String message, Throwable cause) {
        super(errorDefinition.getDescription() + ": " + message, cause);
        this.errorDefinition = errorDefinition;
    }

    public MistyException(MistyErrorDefinition errorDefinition, Throwable cause) {
        super(errorDefinition.getDescription(), cause);
        this.errorDefinition = errorDefinition;
    }

    public MistyErrorDefinition getErrorDefinition() {
        return errorDefinition;
    }
}
