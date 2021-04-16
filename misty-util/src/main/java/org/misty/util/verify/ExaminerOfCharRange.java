package org.misty.util.verify;

import org.misty.util.error.MistyUtilError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfCharRange {

    private final char floor;

    private final char ceiling;

    public ExaminerOfCharRange(char floor, char ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public char requireIncludeInclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireIncludeInclude(char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char requireIncludeExclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireIncludeExclude(char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char requireExcludeInclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireExcludeInclude(char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char requireExcludeExclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireExcludeExclude(char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char refuseIncludeInclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseIncludeInclude(char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char refuseIncludeExclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseIncludeExclude(char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char refuseExcludeInclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseExcludeInclude(char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public char refuseExcludeExclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseExcludeExclude(char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
