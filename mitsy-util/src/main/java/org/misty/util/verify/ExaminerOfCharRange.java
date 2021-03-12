package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfCharRange {

    private final char floor;

    private final char ceiling;

    public ExaminerOfCharRange(char floor, char ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public char requireIncludeInclude(String term, char arg) {
        return requireIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireIncludeInclude(String term, char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char requireIncludeExclude(String term, char arg) {
        return requireIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireIncludeExclude(String term, char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char requireExcludeInclude(String term, char arg) {
        return requireExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireExcludeInclude(String term, char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char requireExcludeExclude(String term, char arg) {
        return requireExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireExcludeExclude(String term, char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char refuseIncludeInclude(String term, char arg) {
        return refuseIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseIncludeInclude(String term, char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char refuseIncludeExclude(String term, char arg) {
        return refuseIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseIncludeExclude(String term, char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char refuseExcludeInclude(String term, char arg) {
        return refuseExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseExcludeInclude(String term, char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char refuseExcludeExclude(String term, char arg) {
        return refuseExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseExcludeExclude(String term, char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
