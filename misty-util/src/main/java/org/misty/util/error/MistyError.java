package org.misty.util.error;

public enum MistyError implements MistyErrorDefinition<MistyException> {
    UNKNOWN //
    , ARGUMENT_ERROR //
    , UNSUPPORTED //
    ;

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
