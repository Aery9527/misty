package org.misty.util.error;

public enum MistyError implements MistyErrorDefinition {
    UNHANDLED,
    ARGUMENT_ERROR;

    private final String type = getClass().getSimpleName();

    private final String code = String.format("%02d", ordinal());

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getCode() {
        return this.code;
    }

}
