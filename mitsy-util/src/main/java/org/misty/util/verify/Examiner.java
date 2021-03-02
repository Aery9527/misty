package org.misty.util.verify;

import org.misty.util.error.MistyError;

import java.util.Optional;

@FunctionalInterface
public interface Examiner<ArgType> {

    static <ArgType> ArgType check(String term, ArgType arg, Examiner<ArgType> examiner) {
        if (examiner.check(arg)) {
            return arg;
        } else {
            String description = ExaminerMessage.CHECK_ERROR_FORMAT.apply(term, arg);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    static void requireNullOrEmpty(String term, Object arg) {
        if (!Judge.isNullOrEmpty(arg)) {
            String description = ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    static void requireNullOrEmpty(String term, Optional<Object> arg) {
        if (!Judge.isNullOrEmpty(arg)) {
            String description = ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    static <ArgType> ArgType refuseNullAndEmpty(String term, ArgType arg) {
        if (Judge.notNullAndEmpty(arg)) {
            return arg;
        }

        String description = ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term);
        throw MistyError.ARGUMENT_ERROR.thrown(description);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    static <ArgType> ArgType refuseNullAndEmpty(String term, Optional<ArgType> arg) {
        if (Judge.notNullAndEmpty(arg)) {
            return arg.get();
        }

        String description = ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term);
        throw MistyError.ARGUMENT_ERROR.thrown(description);
    }

    boolean check(ArgType arg);

}
