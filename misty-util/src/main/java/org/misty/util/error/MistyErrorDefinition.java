package org.misty.util.error;

public interface MistyErrorDefinition<ThrowableType extends MistyException> {

    String TYPE_CODE_FORMAT = "%s(%s)";

    String DESCRIPTION_FORMAT = TYPE_CODE_FORMAT + ":%s";

    default String getType() {
        return getClass().getSimpleName();
    }

    String getCode();

    default String getTypeCode() {
        return String.format(TYPE_CODE_FORMAT, getType(), getCode());
    }

    default String getDescription() {
        return String.format(DESCRIPTION_FORMAT, getType(), getCode(), toString());
    }

    default boolean isSame(Throwable t) {
        return t instanceof MistyException && isSame((MistyException) t);
    }

    default boolean isSame(MistyException me) {
        MistyErrorDefinition<?> med = me.getErrorDefinition();
        return isSame(med);
    }

    default boolean isSame(MistyErrorDefinition<?> med) {
        return getClass().equals(med.getClass()) &&
                getTypeCode().equals(med.getTypeCode());
    }

    default boolean isSameType(Throwable t) {
        return t instanceof MistyException && isSameType((MistyException) t);
    }

    default boolean isSameType(MistyException me) {
        MistyErrorDefinition<?> med = me.getErrorDefinition();
        return isSameType(med);
    }

    default boolean isSameType(MistyErrorDefinition<?> med) {
        return getClass().equals(med.getClass()) &&
                getType().equals(med.getType());
    }

    default ThrowableType thrown() throws ThrowableType {
        throw new MistyException(this);
    }

    default ThrowableType thrown(String msg) throws ThrowableType {
        throw new MistyException(this, msg);
    }

    default ThrowableType thrown(Throwable cause) throws ThrowableType {
        throw new MistyException(this, cause);
    }

    default ThrowableType thrown(String msg, Throwable cause) throws ThrowableType {
        throw new MistyException(this, msg, cause);
    }
}
