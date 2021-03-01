package org.misty.util.error;

public interface MistyErrorDefinition {

    String TYPE_CODE_FORMAT = "%s(%s)";

    String DESCRIPTION_FORMAT = TYPE_CODE_FORMAT + "(%s)";

    String getType();

    String getCode();

    default String getDescription() {
        return String.format(DESCRIPTION_FORMAT, getType(), getCode(), toString());
    }

    default String getTypeCode() {
        return String.format(TYPE_CODE_FORMAT, getType(), getCode());
    }

    default boolean isSame(Throwable t) {
        return t instanceof MistyException && isSame((MistyException) t);
    }

    default boolean isSame(MistyException me) {
        MistyErrorDefinition med = me.getErrorDefinition();
        return isSame(med);
    }

    default boolean isSame(MistyErrorDefinition med) {
        return getClass().equals(med.getClass()) &&
                getDescription().equals(med.getDescription());
    }

    default boolean isSameType(Throwable t) {
        return t instanceof MistyException && isSameType((MistyException) t);
    }

    default boolean isSameType(MistyException me) {
        MistyErrorDefinition med = me.getErrorDefinition();
        return isSameType(med);
    }

    default boolean isSameType(MistyErrorDefinition med) {
        return getClass().equals(med.getClass()) &&
                getType().equals(med.getType());
    }

    default MistyException thrown() throws MistyException {
        throw new MistyException(this);
    }

    default MistyException thrown(String msg) throws MistyException {
        throw new MistyException(this, msg);
    }

    default MistyException thrown(Throwable cause) throws MistyException {
        throw new MistyException(this, cause);
    }

    default MistyException thrown(String msg, Throwable cause) throws MistyException {
        throw new MistyException(this, msg, cause);
    }
}
