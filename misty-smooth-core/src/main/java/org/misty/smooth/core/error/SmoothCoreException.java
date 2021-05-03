package org.misty.smooth.core.error;

import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;

public class SmoothCoreException extends MistyException {

    public SmoothCoreException(MistyErrorDefinition<?> errorDefinition) {
        super(errorDefinition);
    }

    public SmoothCoreException(MistyErrorDefinition<?> errorDefinition, String message) {
        super(errorDefinition, message);
    }

    public SmoothCoreException(MistyErrorDefinition<?> errorDefinition, String message, Throwable cause) {
        super(errorDefinition, message, cause);
    }

    public SmoothCoreException(MistyErrorDefinition<?> errorDefinition, Throwable cause) {
        super(errorDefinition, cause);
    }

}
