package org.misty.smooth.core.error.adpater;

import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.util.error.MistyErrorDefinition;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SmoothManagerLoadExceptionThrownAdapter {

    private final Function<String, SmoothLoadException> thrown1;

    private final BiFunction<String, Throwable, SmoothLoadException> thrown2;

    public SmoothManagerLoadExceptionThrownAdapter(
            Function<String, SmoothLoadException> thrown1,
            BiFunction<String, Throwable, SmoothLoadException> thrown2
    ) {
        this.thrown1 = thrown1;
        this.thrown2 = thrown2;
    }

    public SmoothLoadException thrown(MistyErrorDefinition<?> errorDefinition) throws SmoothLoadException {
        String message = errorDefinition.getDescription();
        throw this.thrown1.apply(message);
    }

    public SmoothLoadException thrown(MistyErrorDefinition<?> errorDefinition, String msg) throws SmoothLoadException {
        String message = errorDefinition.getDescription() + ": " + msg;
        throw this.thrown1.apply(message);
    }

    public SmoothLoadException thrown(MistyErrorDefinition<?> errorDefinition, Throwable cause) throws SmoothLoadException {
        String message = errorDefinition.getDescription();
        throw this.thrown2.apply(message, cause);
    }

    public SmoothLoadException thrown(MistyErrorDefinition<?> errorDefinition, String msg, Throwable cause) throws SmoothLoadException {
        String message = errorDefinition.getDescription() + ": " + msg;
        throw this.thrown2.apply(message, cause);
    }

}
