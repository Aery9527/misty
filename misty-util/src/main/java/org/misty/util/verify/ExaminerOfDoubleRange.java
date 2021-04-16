package org.misty.util.verify;

import org.misty.util.error.MistyUtilError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfDoubleRange {

    private final double floor;

    private final double ceiling;

    public ExaminerOfDoubleRange(double floor, double ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public double requireIncludeInclude(String term, double arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireIncludeInclude(double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double requireIncludeExclude(String term, double arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireIncludeExclude(double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double requireExcludeInclude(String term, double arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireExcludeInclude(double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double requireExcludeExclude(String term, double arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireExcludeExclude(double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double refuseIncludeInclude(String term, double arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseIncludeInclude(double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double refuseIncludeExclude(String term, double arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseIncludeExclude(double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double refuseExcludeInclude(String term, double arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseExcludeInclude(double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double refuseExcludeExclude(String term, double arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseExcludeExclude(double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
