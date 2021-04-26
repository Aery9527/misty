package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfLongRange {

    private final long floor;

    private final long ceiling;

    public ExaminerOfLongRange(long floor, long ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public long requireIncludeInclude(String term, long arg) throws MistyException {
        return requireIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> long requireIncludeInclude(
            String term, long arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeInclude(arg, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long requireIncludeInclude(
            long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public long requireIncludeExclude(String term, long arg) throws MistyException {
        return requireIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> long requireIncludeExclude(
            String term, long arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeExclude(arg, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long requireIncludeExclude(
            long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public long requireExcludeInclude(String term, long arg) throws MistyException {
        return requireExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> long requireExcludeInclude(
            String term, long arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeInclude(arg, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long requireExcludeInclude(
            long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public long requireExcludeExclude(String term, long arg) throws MistyException {
        return requireExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> long requireExcludeExclude(
            String term, long arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeExclude(arg, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long requireExcludeExclude(
            long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public long refuseIncludeInclude(String term, long arg) throws MistyException {
        return refuseIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> long refuseIncludeInclude(
            String term, long arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeInclude(arg, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long refuseIncludeInclude(
            long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public long refuseIncludeExclude(String term, long arg) throws MistyException {
        return refuseIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> long refuseIncludeExclude(
            String term, long arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeExclude(arg, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long refuseIncludeExclude(
            long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public long refuseExcludeInclude(String term, long arg) throws MistyException {
        return refuseExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> long refuseExcludeInclude(
            String term, long arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeInclude(arg, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long refuseExcludeInclude(
            long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public long refuseExcludeExclude(String term, long arg) throws MistyException {
        return refuseExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> long refuseExcludeExclude(
            String term, long arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeExclude(arg, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> long refuseExcludeExclude(
            long arg, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
