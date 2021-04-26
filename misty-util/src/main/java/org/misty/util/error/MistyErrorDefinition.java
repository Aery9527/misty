package org.misty.util.error;

public interface MistyErrorDefinition<ThrowableType extends Exception> {

    String TYPE_CODE_FORMAT = "%s(%s)";

    String DESCRIPTION_FORMAT = TYPE_CODE_FORMAT + "(%s)";

    String getType();

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

    ThrowableType thrown() throws ThrowableType;

    ThrowableType thrown(String msg) throws ThrowableType;

    ThrowableType thrown(Throwable cause) throws ThrowableType;

    ThrowableType thrown(String msg, Throwable cause) throws ThrowableType;
}
