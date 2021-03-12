package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfShortRange {

    private final short floor;

    private final short ceiling;

    public ExaminerOfShortRange(short floor, short ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public short requireIncludeInclude(String term, short arg) {
        return requireIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> short requireIncludeInclude(String term, short arg, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public short requireIncludeExclude(String term, short arg) {
        return requireIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> short requireIncludeExclude(String term, short arg, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public short requireExcludeInclude(String term, short arg) {
        return requireExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> short requireExcludeInclude(String term, short arg, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public short requireExcludeExclude(String term, short arg) {
        return requireExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> short requireExcludeExclude(String term, short arg, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public short refuseIncludeInclude(String term, short arg) {
        return refuseIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> short refuseIncludeInclude(String term, short arg, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public short refuseIncludeExclude(String term, short arg) {
        return refuseIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> short refuseIncludeExclude(String term, short arg, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public short refuseExcludeInclude(String term, short arg) {
        return refuseExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> short refuseExcludeInclude(String term, short arg, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public short refuseExcludeExclude(String term, short arg) {
        return refuseExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> short refuseExcludeExclude(String term, short arg, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
