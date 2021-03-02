package org.misty.util.verify;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ExaminerMessage {

    public static final String CHECK_ERROR = "check error of \"%s\" that value is %s";
    public static final BiFunction<String, Object, String> CHECK_ERROR_FORMAT = (term, arg) -> String.format(CHECK_ERROR, term, arg);

    public static final String REQUIRE_NULL_OR_EMPTY = "check error of \"%s\" that require null or empty.";
    public static final Function<String, String> REQUIRE_NULL_OR_EMPTY_FORMAT = (term) -> String.format(REQUIRE_NULL_OR_EMPTY, term);

    public static final String REFUSE_NULL_OR_EMPTY = "check error of \"%s\" that refuse null and empty.";
    public static final Function<String, String> REFUSE_NULL_OR_EMPTY_FORMAT = (term) -> String.format(REFUSE_NULL_OR_EMPTY, term);

}
