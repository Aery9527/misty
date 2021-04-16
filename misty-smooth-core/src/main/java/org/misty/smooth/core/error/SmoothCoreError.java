package org.misty.smooth.core.error;

import org.misty.util.error.MistyErrorDefinition;

public enum SmoothCoreError implements MistyErrorDefinition {
    // class 0,
    UNKNOWN("00")
    // class 1, about


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
}
