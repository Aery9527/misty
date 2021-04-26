package org.misty.smooth.core.error;

import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;

public enum SmoothCoreError implements MistyErrorDefinition<MistyException> {
    // 0xx series [TEMPORARILY RESERVED]
    // 1xx series is about initial error
    SMOOTH_ID_LINKAGE_ERROR("100")
    // 2xx series [TEMPORARILY RESERVED]
    // 3xx series [TEMPORARILY RESERVED]
    // 4xx series [TEMPORARILY RESERVED]
    // 5xx series [TEMPORARILY RESERVED]
    // 6xx series [TEMPORARILY RESERVED]
    // 7xx series [TEMPORARILY RESERVED]
    // 8xx series [TEMPORARILY RESERVED]
    // 9xx series [TEMPORARILY RESERVED]
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
    public MistyException thrown() throws MistyException {
        throw new MistyException(this);
    }

    @Override
    public MistyException thrown(String msg) throws MistyException {
        throw new MistyException(this, msg);
    }

    @Override
    public MistyException thrown(Throwable cause) throws MistyException {
        throw new MistyException(this, cause);
    }

    @Override
    public MistyException thrown(String msg, Throwable cause) throws MistyException {
        throw new MistyException(this, msg, cause);
    }

}
