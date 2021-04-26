package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfDoubleRange {

    private final double floor;

    private final double ceiling;

    public ExaminerOfDoubleRange(double floor, double ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public double requireIncludeInclude(String term, double arg) throws MistyException {
        return requireIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> double requireIncludeInclude(
            String term, double arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeInclude(arg, (FiBiConsumerThrow1<Double, Double, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireIncludeInclude(
            double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public double requireIncludeExclude(String term, double arg) throws MistyException {
        return requireIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> double requireIncludeExclude(
            String term, double arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeExclude(arg, (FiBiConsumerThrow1<Double, Double, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireIncludeExclude(
            double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public double requireExcludeInclude(String term, double arg) throws MistyException {
        return requireExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> double requireExcludeInclude(
            String term, double arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeInclude(arg, (FiBiConsumerThrow1<Double, Double, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireExcludeInclude(
            double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public double requireExcludeExclude(String term, double arg) throws MistyException {
        return requireExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> double requireExcludeExclude(
            String term, double arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeExclude(arg, (FiBiConsumerThrow1<Double, Double, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double requireExcludeExclude(
            double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public double refuseIncludeInclude(String term, double arg) throws MistyException {
        return refuseIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> double refuseIncludeInclude(
            String term, double arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeInclude(arg, (FiBiConsumerThrow1<Double, Double, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseIncludeInclude(
            double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public double refuseIncludeExclude(String term, double arg) throws MistyException {
        return refuseIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> double refuseIncludeExclude(
            String term, double arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeExclude(arg, (FiBiConsumerThrow1<Double, Double, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseIncludeExclude(
            double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public double refuseExcludeInclude(String term, double arg) throws MistyException {
        return refuseExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> double refuseExcludeInclude(
            String term, double arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeInclude(arg, (FiBiConsumerThrow1<Double, Double, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseExcludeInclude(
            double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public double refuseExcludeExclude(String term, double arg) throws MistyException {
        return refuseExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> double refuseExcludeExclude(
            String term, double arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeExclude(arg, (FiBiConsumerThrow1<Double, Double, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> double refuseExcludeExclude(
            double arg, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
