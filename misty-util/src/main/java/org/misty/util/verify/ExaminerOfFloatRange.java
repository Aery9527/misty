package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfFloatRange {

    private final float floor;

    private final float ceiling;

    public ExaminerOfFloatRange(float floor, float ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public float requireIncludeInclude(String term, float arg) throws MistyException {
        return requireIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> float requireIncludeInclude(
            String term, float arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeInclude(arg, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float requireIncludeInclude(
            float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public float requireIncludeExclude(String term, float arg) throws MistyException {
        return requireIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> float requireIncludeExclude(
            String term, float arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeExclude(arg, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float requireIncludeExclude(
            float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public float requireExcludeInclude(String term, float arg) throws MistyException {
        return requireExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> float requireExcludeInclude(
            String term, float arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeInclude(arg, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float requireExcludeInclude(
            float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public float requireExcludeExclude(String term, float arg) throws MistyException {
        return requireExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> float requireExcludeExclude(
            String term, float arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeExclude(arg, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float requireExcludeExclude(
            float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public float refuseIncludeInclude(String term, float arg) throws MistyException {
        return refuseIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> float refuseIncludeInclude(
            String term, float arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeInclude(arg, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float refuseIncludeInclude(
            float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public float refuseIncludeExclude(String term, float arg) throws MistyException {
        return refuseIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> float refuseIncludeExclude(
            String term, float arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeExclude(arg, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float refuseIncludeExclude(
            float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public float refuseExcludeInclude(String term, float arg) throws MistyException {
        return refuseExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> float refuseExcludeInclude(
            String term, float arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeInclude(arg, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float refuseExcludeInclude(
            float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public float refuseExcludeExclude(String term, float arg) throws MistyException {
        return refuseExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> float refuseExcludeExclude(
            String term, float arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeExclude(arg, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float refuseExcludeExclude(
            float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
