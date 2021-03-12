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
        return requireIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int requireIncludeInclude(String term, int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int requireIncludeExclude(String term, int arg) {
        return requireIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int requireIncludeExclude(String term, int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int requireExcludeInclude(String term, int arg) {
        return requireExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int requireExcludeInclude(String term, int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int requireExcludeExclude(String term, int arg) {
        return requireExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int requireExcludeExclude(String term, int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int refuseIncludeInclude(String term, int arg) {
        return refuseIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int refuseIncludeInclude(String term, int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int refuseIncludeExclude(String term, int arg) {
        return refuseIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int refuseIncludeExclude(String term, int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int refuseExcludeInclude(String term, int arg) {
        return refuseExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int refuseExcludeInclude(String term, int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public int refuseExcludeExclude(String term, int arg) {
        return refuseExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> int refuseExcludeExclude(String term, int arg, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
