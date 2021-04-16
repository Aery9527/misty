package org.misty.util.verify;

import org.misty.util.error.MistyUtilError;
import org.misty.util.fi.FiBiConsumerThrow1;

import java.math.BigDecimal;

public class ExaminerOfNumberRange {

    private final BigDecimal floor;

    private final BigDecimal ceiling;

    public ExaminerOfNumberRange(Number floor, Number ceiling) {
        Examiner.refuseNullAndEmpty("floor", floor);
        Examiner.refuseNullAndEmpty("ceiling", ceiling);

        this.floor = floor instanceof BigDecimal ? (BigDecimal) floor : new BigDecimal(floor.toString());
        this.ceiling = ceiling instanceof BigDecimal ? (BigDecimal) ceiling : new BigDecimal(ceiling.toString());
    }

    public Number requireIncludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number requireIncludeInclude(Number arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (!(floorOffset >= 0 && ceilingOffset <= 0)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public Number requireIncludeExclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number requireIncludeExclude(Number arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (!(floorOffset >= 0 && ceilingOffset < 0)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public Number requireExcludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number requireExcludeInclude(Number arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (!(floorOffset > 0 && ceilingOffset <= 0)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public Number requireExcludeExclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return requireExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number requireExcludeExclude(Number arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (!(floorOffset > 0 && ceilingOffset < 0)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public Number refuseIncludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number refuseIncludeInclude(Number arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (floorOffset >= 0 && ceilingOffset <= 0) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public Number refuseIncludeExclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseIncludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number refuseIncludeExclude(Number arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (floorOffset >= 0 && ceilingOffset < 0) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public Number refuseExcludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeInclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number refuseExcludeInclude(Number arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (floorOffset > 0 && ceilingOffset <= 0) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    public Number refuseExcludeExclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);

        return refuseExcludeExclude(arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyUtilError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number refuseExcludeExclude(Number arg, FiBiConsumerThrow1<BigDecimal, BigDecimal, ThrowableType> thrownAction) throws ThrowableType {
        BigDecimal target = new BigDecimal(arg.toString());

        int floorOffset = target.subtract(this.floor).compareTo(BigDecimal.ZERO);
        int ceilingOffset = target.subtract(this.ceiling).compareTo(BigDecimal.ZERO);

        if (floorOffset > 0 && ceilingOffset < 0) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
