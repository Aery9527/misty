package org.misty.util.verify;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ExaminerMessage {

    public static final String REQUIRE_NULL_OR_EMPTY = "check error of \"%s\", the value is %s, require null or empty.";

    public static final String REFUSE_NULL_OR_EMPTY = "check error of \"%s\", refuse null and empty.";

    public static final String REQUIRE_IN_RANGE = "check error of \"%s\", the value is %s, require in %s ~ %s";

    public static String requireNullOrEmpty(String term, String arg) {
        return String.format(REQUIRE_NULL_OR_EMPTY, arg, term);
    }

    public static String refuseNullAndEmpty(String term) {
        return String.format(REFUSE_NULL_OR_EMPTY, term);
    }

    public static String requireInRange(String term, short arg, short floor, short ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

    public static String requireInRange(String term, int arg, int floor, int ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

    public static String requireInRange(String term, long arg, long floor, long ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

    public static String requireInRange(String term, float arg, float floor, float ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

    public static String requireInRange(String term, double arg, double floor, double ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

    public static String requireInRange(String term, char arg, char floor, char ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

    public static String requireInRange(String term, byte arg, byte floor, byte ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

    public static String requireInRange(String term, Character arg, Character floor, Character ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

    public static String requireInRange(String term, Number arg, Number floor, Number ceiling) {
        return String.format(REQUIRE_IN_RANGE, term, arg, floor, ceiling);
    }

}
