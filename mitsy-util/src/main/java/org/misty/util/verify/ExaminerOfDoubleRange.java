package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfDoubleRange {

    private final double floor;

    private final double ceiling;

    public ExaminerOfDoubleRange(double floor, double ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public double requireIncludeInclude(String term, double arg) {
        return requireIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireIncludeInclude(String term, double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double requireIncludeExclude(String term, double arg) {
        return requireIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireIncludeExclude(String term, double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double requireExcludeInclude(String term, double arg) {
        return requireExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireExcludeInclude(String term, double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double requireExcludeExclude(String term, double arg) {
        return requireExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireExcludeExclude(String term, double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double refuseIncludeInclude(String term, double arg) {
        return refuseIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseIncludeInclude(String term, double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double refuseIncludeExclude(String term, double arg) {
        return refuseIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseIncludeExclude(String term, double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double refuseExcludeInclude(String term, double arg) {
        return refuseExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseExcludeInclude(String term, double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public double refuseExcludeExclude(String term, double arg) {
        return refuseExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseExcludeExclude(String term, double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
