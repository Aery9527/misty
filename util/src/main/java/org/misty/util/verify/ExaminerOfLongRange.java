package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfLongRange {

    private final long floor;

    private final long ceiling;

    public ExaminerOfLongRange(long floor, long ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public long requireIncludeInclude(String term, long arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long requireIncludeInclude(long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public long requireIncludeExclude(String term, long arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long requireIncludeExclude(long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public long requireExcludeInclude(String term, long arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long requireExcludeInclude(long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public long requireExcludeExclude(String term, long arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long requireExcludeExclude(long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public long refuseIncludeInclude(String term, long arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long refuseIncludeInclude(long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public long refuseIncludeExclude(String term, long arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long refuseIncludeExclude(long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public long refuseExcludeInclude(String term, long arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long refuseExcludeInclude(long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public long refuseExcludeExclude(String term, long arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long refuseExcludeExclude(long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
