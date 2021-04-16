package org.misty.util.verify;

import org.misty.util.error.MistyUtilError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfFloatRange {

    private final float floor;

    private final float ceiling;

    public ExaminerOfFloatRange(float floor, float ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public float requireIncludeInclude(String term, float arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float requireIncludeInclude(float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public float requireIncludeExclude(String term, float arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float requireIncludeExclude(float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public float requireExcludeInclude(String term, float arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float requireExcludeInclude(float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public float requireExcludeExclude(String term, float arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float requireExcludeExclude(float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public float refuseIncludeInclude(String term, float arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float refuseIncludeInclude(float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public float refuseIncludeExclude(String term, float arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float refuseIncludeExclude(float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public float refuseExcludeInclude(String term, float arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float refuseExcludeInclude(float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public float refuseExcludeExclude(String term, float arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> float refuseExcludeExclude(float arg, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
