package org.misty.util.verify;

public class ExaminerMessage {

    public static final String REQUIRE_NULL_OR_EMPTY = "check error of \"%s\", the value is %s, require null or empty.";

    public static final String REFUSE_NULL_OR_EMPTY = "check error of \"%s\", refuse null and empty.";

    public static final String REQUIRE_RANGE = "check error of \"%s\", the value is %s, require %s%s, %s%s";

    public static final String REFUSE_RANGE = "check error of \"%s\", the value is %s, refuse %s%s, %s%s";

    public static final String REQUIRE_MORE_INCLUDE = "check error of \"%s\", the value is %s, require more include %s";

    public static final String REQUIRE_MORE_EXCLUDE = "check error of \"%s\", the value is %s, require more exclude %s";

    public static final String REQUIRE_LESS_INCLUDE = "check error of \"%s\", the value is %s, require less include %s";

    public static final String REQUIRE_LESS_EXCLUDE = "check error of \"%s\", the value is %s, require less exclude %s";

    public static final String REQUIRE_NUMBER = "check error of \"%s\", the value is %s, require number.";

    public static final String REQUIRE_SHORT = "check error of \"%s\", the value is %s, require Short [%s(-2^15) ~ %s(2^15-1)].";

    public static final String REQUIRE_INTEGER = "check error of \"%s\", the value is %s, require Integer [%s(-2^31) ~ %s(2^31-1)].";

    public static final String REQUIRE_LONG = "check error of \"%s\", the value is %s, require Long [%s(-2^63) ~ %s(2^63-1)].";

    public static final String REQUIRE_FLOAT = "check error of \"%s\", the value is %s, require Float.";

    public static final String REQUIRE_DOUBLE = "check error of \"%s\", the value is %s, require Double.";

    public static String requireNullOrEmpty(String term, String arg) {
        return String.format(REQUIRE_NULL_OR_EMPTY, arg, term);
    }

    public static String refuseNullAndEmpty(String term) {
        return String.format(REFUSE_NULL_OR_EMPTY, term);
    }

    // requireInRange

    public static String requireInRange(String term, short arg,
                                        ExamineIntervals.Floor floorIntervals, short floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, short ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String requireInRange(String term, int arg,
                                        ExamineIntervals.Floor floorIntervals, int floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, int ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String requireInRange(String term, long arg,
                                        ExamineIntervals.Floor floorIntervals, long floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, long ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String requireInRange(String term, float arg,
                                        ExamineIntervals.Floor floorIntervals, float floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, float ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String requireInRange(String term, double arg,
                                        ExamineIntervals.Floor floorIntervals, double floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, double ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String requireInRange(String term, char arg,
                                        ExamineIntervals.Floor floorIntervals, char floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, char ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String requireInRange(String term, byte arg,
                                        ExamineIntervals.Floor floorIntervals, byte floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, byte ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String requireInRange(String term, Object arg,
                                        ExamineIntervals.Floor floorIntervals, Object floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, Object ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    // refuseInRange

    public static String refuseInRange(String term, short arg,
                                       ExamineIntervals.Floor floorIntervals, short floor,
                                       ExamineIntervals.Ceiling ceilingIntervals, short ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String refuseInRange(String term, int arg,
                                       ExamineIntervals.Floor floorIntervals, int floor,
                                       ExamineIntervals.Ceiling ceilingIntervals, int ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String refuseInRange(String term, long arg,
                                       ExamineIntervals.Floor floorIntervals, long floor,
                                       ExamineIntervals.Ceiling ceilingIntervals, long ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String refuseInRange(String term, float arg,
                                       ExamineIntervals.Floor floorIntervals, float floor,
                                       ExamineIntervals.Ceiling ceilingIntervals, float ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String refuseInRange(String term, double arg,
                                       ExamineIntervals.Floor floorIntervals, double floor,
                                       ExamineIntervals.Ceiling ceilingIntervals, double ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String refuseInRange(String term, char arg,
                                       ExamineIntervals.Floor floorIntervals, char floor,
                                       ExamineIntervals.Ceiling ceilingIntervals, char ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String refuseInRange(String term, byte arg,
                                       ExamineIntervals.Floor floorIntervals, byte floor,
                                       ExamineIntervals.Ceiling ceilingIntervals, byte ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String refuseInRange(String term, Object arg,
                                       ExamineIntervals.Floor floorIntervals, Object floor,
                                       ExamineIntervals.Ceiling ceilingIntervals, Object ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    // requireMoreInclude

    public static String requireMoreInclude(String term, short arg, short border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    public static String requireMoreInclude(String term, int arg, int border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    public static String requireMoreInclude(String term, long arg, long border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    public static String requireMoreInclude(String term, float arg, float border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    public static String requireMoreInclude(String term, double arg, double border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    public static String requireMoreInclude(String term, char arg, char border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    public static String requireMoreInclude(String term, byte arg, byte border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    public static String requireMoreInclude(String term, Number arg, Number border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    // requireMoreExclude

    public static String requireMoreExclude(String term, short arg, short border) {
        return String.format(REQUIRE_MORE_INCLUDE, term, arg, border);
    }

    public static String requireMoreExclude(String term, int arg, int border) {
        return String.format(REQUIRE_MORE_EXCLUDE, term, arg, border);
    }

    public static String requireMoreExclude(String term, long arg, long border) {
        return String.format(REQUIRE_MORE_EXCLUDE, term, arg, border);
    }

    public static String requireMoreExclude(String term, float arg, float border) {
        return String.format(REQUIRE_MORE_EXCLUDE, term, arg, border);
    }

    public static String requireMoreExclude(String term, double arg, double border) {
        return String.format(REQUIRE_MORE_EXCLUDE, term, arg, border);
    }

    public static String requireMoreExclude(String term, char arg, char border) {
        return String.format(REQUIRE_MORE_EXCLUDE, term, arg, border);
    }

    public static String requireMoreExclude(String term, byte arg, byte border) {
        return String.format(REQUIRE_MORE_EXCLUDE, term, arg, border);
    }

    public static String requireMoreExclude(String term, Number arg, Number border) {
        return String.format(REQUIRE_MORE_EXCLUDE, term, arg, border);
    }

    // requireLessInclude

    public static String requireLessInclude(String term, short arg, short border) {
        return String.format(REQUIRE_LESS_INCLUDE, term, arg, border);
    }

    public static String requireLessInclude(String term, int arg, int border) {
        return String.format(REQUIRE_LESS_INCLUDE, term, arg, border);
    }

    public static String requireLessInclude(String term, long arg, long border) {
        return String.format(REQUIRE_LESS_INCLUDE, term, arg, border);
    }

    public static String requireLessInclude(String term, float arg, float border) {
        return String.format(REQUIRE_LESS_INCLUDE, term, arg, border);
    }

    public static String requireLessInclude(String term, double arg, double border) {
        return String.format(REQUIRE_LESS_INCLUDE, term, arg, border);
    }

    public static String requireLessInclude(String term, char arg, char border) {
        return String.format(REQUIRE_LESS_INCLUDE, term, arg, border);
    }

    public static String requireLessInclude(String term, byte arg, byte border) {
        return String.format(REQUIRE_LESS_INCLUDE, term, arg, border);
    }

    public static String requireLessInclude(String term, Number arg, Number border) {
        return String.format(REQUIRE_LESS_INCLUDE, term, arg, border);
    }

    // requireLessExclude

    public static String requireLessExclude(String term, short arg, short border) {
        return String.format(REQUIRE_LESS_EXCLUDE, term, arg, border);
    }

    public static String requireLessExclude(String term, int arg, int border) {
        return String.format(REQUIRE_LESS_EXCLUDE, term, arg, border);
    }

    public static String requireLessExclude(String term, long arg, long border) {
        return String.format(REQUIRE_LESS_EXCLUDE, term, arg, border);
    }

    public static String requireLessExclude(String term, float arg, float border) {
        return String.format(REQUIRE_LESS_EXCLUDE, term, arg, border);
    }

    public static String requireLessExclude(String term, double arg, double border) {
        return String.format(REQUIRE_LESS_EXCLUDE, term, arg, border);
    }

    public static String requireLessExclude(String term, char arg, char border) {
        return String.format(REQUIRE_LESS_EXCLUDE, term, arg, border);
    }

    public static String requireLessExclude(String term, byte arg, byte border) {
        return String.format(REQUIRE_LESS_EXCLUDE, term, arg, border);
    }

    public static String requireLessExclude(String term, Number arg, Number border) {
        return String.format(REQUIRE_LESS_EXCLUDE, term, arg, border);
    }

    //

    public static String requireNumber(String term, String value) {
        return String.format(REQUIRE_NUMBER, term, value);
    }

    public static String requireShort(String term, String value) {
        return String.format(REQUIRE_SHORT, term, value, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    public static String requireInteger(String term, String value) {
        return String.format(REQUIRE_INTEGER, term, value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static String requireLong(String term, String value) {
        return String.format(REQUIRE_LONG, term, value, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static String requireFloat(String term, String value) {
        return String.format(REQUIRE_FLOAT, term, value);
    }

    public static String requireDouble(String term, String value) {
        return String.format(REQUIRE_DOUBLE, term, value);
    }

}
