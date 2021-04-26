package org.misty.util.error;

public interface MistyErrorDefinition<ExceptionType extends Exception> {

    class Format {
        public static String TYPE_CODE = "%s(%s)";

        public static String DESCRIPTION = TYPE_CODE + "(%s)";
    }

    String getType();

    String getCode();

    default String getTypeCode() {
        return String.format(Format.TYPE_CODE, getType(), getCode());
    }

    default String getDescription() {
        return String.format(Format.DESCRIPTION, getType(), getCode(), this);
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

    ExceptionType thrown() throws ExceptionType;

    ExceptionType thrown(String msg) throws ExceptionType;

    ExceptionType thrown(Throwable cause) throws ExceptionType;

    ExceptionType thrown(String msg, Throwable cause) throws ExceptionType;
}
