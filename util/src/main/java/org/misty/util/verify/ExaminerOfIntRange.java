package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfIntRange {

    private final int floor;

    private final int ceiling;

    public ExaminerOfIntRange(int floor, int ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public int requireIncludeInclude(String term, int arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int requireIncludeInclude(int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int requireIncludeExclude(String term, int arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int requireIncludeExclude(int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int requireExcludeInclude(String term, int arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int requireExcludeInclude(int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int requireExcludeExclude(String term, int arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int requireExcludeExclude(int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int refuseIncludeInclude(String term, int arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int refuseIncludeInclude(int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int refuseIncludeExclude(String term, int arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int refuseIncludeExclude(int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int refuseExcludeInclude(String term, int arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int refuseExcludeInclude(int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int refuseExcludeExclude(String term, int arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int refuseExcludeExclude(int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
