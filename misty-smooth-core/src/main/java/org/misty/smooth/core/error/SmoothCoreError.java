package org.misty.smooth.core.error;

import org.misty.util.error.MistyErrorDefinition;

public enum SmoothCoreError implements MistyErrorDefinition<SmoothCoreException> {
    ARGUMENT_ERROR("01")
    ;

    private final String type = getClass().getSimpleName();

    private final String code;

    SmoothCoreError(String code) {
        this.code = code;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public SmoothCoreException thrown() throws SmoothCoreException {
        throw new SmoothCoreException(this);
    }

    @Override
    public SmoothCoreException thrown(String msg) throws SmoothCoreException {
        throw new SmoothCoreException(this, msg);
    }

    @Override
    public SmoothCoreException thrown(Throwable cause) throws SmoothCoreException {
        throw new SmoothCoreException(this, cause);
    }

    @Override
    public SmoothCoreException thrown(String msg, Throwable cause) throws SmoothCoreException {
        throw new SmoothCoreException(this, msg, cause);
    }

}
