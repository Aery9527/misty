package org.misty.util.verify;

public class ExaminerMessage {

    public static final String REQUIRE_NULL_OR_EMPTY = "check error of \"%s\", the value is %s, require null or empty.";

    public static final String REFUSE_NULL_OR_EMPTY = "check error of \"%s\", refuse null and empty.";

    public static final String REQUIRE_RANGE = "check error of \"%s\", the value is %s, require %s%s, %s%s";

    public static final String REFUSE_RANGE = "check error of \"%s\", the value is %s, refuse (%s, %s)";

    public static final String REQUIRE_MORE_EQUAL = "check error of \"%s\", the value is %s, require more then or equal to %s";

    public static final String REQUIRE_LESS_EQUAL = "check error of \"%s\", the value is %s, require less then or equal to %s";

    public static final String REFUSE_MORE_EQUAL = "check error of \"%s\", the value is %s, refuse more then or equal to %s";

    public static final String REFUSE_LESS_EQUAL = "check error of \"%s\", the value is %s, refuse less then or equal to %s";

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

    public static String requireInRange(String term, Number arg,
                                        ExamineIntervals.Floor floorIntervals, Number floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, Number ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    public static String requireInRange(String term, Object arg,
                                        ExamineIntervals.Floor floorIntervals, Object floor,
                                        ExamineIntervals.Ceiling ceilingIntervals, Object ceiling) {
        return String.format(REQUIRE_RANGE, term, arg, floorIntervals.getSymbol(), floor, ceiling, ceilingIntervals.getSymbol());
    }

    // refuseInRange

    public static String refuseInRange(String term, short arg, short floor, short ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    public static String refuseInRange(String term, int arg, int floor, int ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    public static String refuseInRange(String term, long arg, long floor, long ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    public static String refuseInRange(String term, float arg, float floor, float ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    public static String refuseInRange(String term, double arg, double floor, double ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    public static String refuseInRange(String term, char arg, char floor, char ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    public static String refuseInRange(String term, byte arg, byte floor, byte ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    public static String refuseInRange(String term, Character arg, Character floor, Character ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    public static String refuseInRange(String term, Number arg, Number floor, Number ceiling) {
        return String.format(REFUSE_RANGE, term, arg, floor, ceiling);
    }

    // requireNumberMoreEqual

    public static String requireMoreEqual(String term, short arg, short floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    public static String requireMoreEqual(String term, int arg, int floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    public static String requireMoreEqual(String term, long arg, long floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    public static String requireMoreEqual(String term, float arg, float floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    public static String requireMoreEqual(String term, double arg, double floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    public static String requireMoreEqual(String term, char arg, char floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    public static String requireMoreEqual(String term, byte arg, byte floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    public static String requireMoreEqual(String term, Character arg, Character floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    public static String requireMoreEqual(String term, Number arg, Number floor) {
        return String.format(REQUIRE_MORE_EQUAL, term, arg, floor);
    }

    // requireLessEqual

}
