package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.fi.FiBiConsumerThrow1;

import java.math.BigDecimal;

public class ExaminerOfNumberRange {

    private final BigDecimal floor;

    private final BigDecimal ceiling;

    public ExaminerOfNumberRange(Number floor, Number ceiling) {
        Examiner.refuseNullAndEmpty("floor", floor);
        Examiner.refuseNullAndEmpty("ceiling", ceiling);

        this.floor = Examiner.toBigDecimal(floor);
        this.ceiling = Examiner.toBigDecimal(ceiling);
    }

    public <TargetType extends Number> TargetType requireIncludeInclude(String term, TargetType arg) {
        return requireIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <TargetType extends Number, ThrowableType extends Exception> TargetType requireIncludeInclude(
            String term, TargetType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeInclude(arg, (FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            errorDefinition.thrown(description);
        });
    }

    public <TargetType extends Number, ThrowableType extends Throwable> TargetType requireIncludeInclude(
            TargetType arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (!(floorOffset >= 0 && ceilingOffset <= 0)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public <TargetType extends Number> TargetType requireIncludeExclude(String term, TargetType arg) {
        return requireIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <TargetType extends Number, ThrowableType extends Exception> TargetType requireIncludeExclude(
            String term, TargetType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeExclude(arg, (FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <TargetType extends Number, ThrowableType extends Throwable> TargetType requireIncludeExclude(
            TargetType arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (!(floorOffset >= 0 && ceilingOffset < 0)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public <TargetType extends Number> TargetType requireExcludeInclude(String term, TargetType arg) {
        return requireExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <TargetType extends Number, ThrowableType extends Exception> TargetType requireExcludeInclude(
            String term, TargetType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeInclude(arg, (FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <TargetType extends Number, ThrowableType extends Throwable> TargetType requireExcludeInclude(
            TargetType arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (!(floorOffset > 0 && ceilingOffset <= 0)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public <TargetType extends Number> TargetType requireExcludeExclude(String term, TargetType arg) {
        return requireExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <TargetType extends Number, ThrowableType extends Exception> TargetType requireExcludeExclude(
            String term, TargetType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeExclude(arg, (FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <TargetType extends Number, ThrowableType extends Throwable> TargetType requireExcludeExclude(
            TargetType arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (!(floorOffset > 0 && ceilingOffset < 0)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public <TargetType extends Number> TargetType refuseIncludeInclude(String term, TargetType arg) {
        return refuseIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <TargetType extends Number, ThrowableType extends Exception> TargetType refuseIncludeInclude(
            String term, TargetType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeInclude(arg, (FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <TargetType extends Number, ThrowableType extends Throwable> TargetType refuseIncludeInclude(
            TargetType arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (floorOffset >= 0 && ceilingOffset <= 0) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public <TargetType extends Number> TargetType refuseIncludeExclude(String term, TargetType arg) {
        return refuseIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <TargetType extends Number, ThrowableType extends Exception> TargetType refuseIncludeExclude(
            String term, TargetType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeExclude(arg, (FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <TargetType extends Number, ThrowableType extends Throwable> TargetType refuseIncludeExclude(
            TargetType arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (floorOffset >= 0 && ceilingOffset < 0) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public <TargetType extends Number> TargetType refuseExcludeInclude(String term, TargetType arg) {
        return refuseExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <TargetType extends Number, ThrowableType extends Exception> TargetType refuseExcludeInclude(
            String term, TargetType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeInclude(arg, (FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <TargetType extends Number, ThrowableType extends Throwable> TargetType refuseExcludeInclude(
            TargetType arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (floorOffset > 0 && ceilingOffset <= 0) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public <TargetType extends Number> TargetType refuseExcludeExclude(String term, TargetType arg) {
        return refuseExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <TargetType extends Number, ThrowableType extends Exception> TargetType refuseExcludeExclude(
            String term, TargetType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeExclude(arg, (FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <TargetType extends Number, ThrowableType extends Throwable> TargetType refuseExcludeExclude(
            TargetType arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (floorOffset > 0 && ceilingOffset < 0) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
