package org.misty.smooth.core.error;

import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;

public enum SmoothCoreError implements MistyErrorDefinition<MistyException> {
    // 0x series : [UNDEFINED]
    // 1x series : loader error
    CLASSLOADER_BUILD_ERROR("100")
    // 2x series : [UNDEFINED]
    // 3x series : [UNDEFINED]
    // 4x series : [UNDEFINED]
    // 5x series : [UNDEFINED]
    // 6x series : [UNDEFINED]
    // 7x series : [UNDEFINED]
    // 8x series : [UNDEFINED]
    // 9x series : [UNDEFINED]
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
