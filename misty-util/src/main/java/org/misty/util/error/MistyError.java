package org.misty.util.error;

public enum MistyError implements MistyErrorDefinition<MistyException> {
    UNKNOWN //
    , ARGUMENT_ERROR //
    , UNSUPPORTED //
    ;

    private final String code = String.format("%02d", ordinal());

    @Override
    public String getCode() {
        return this.code;
    }

}
