package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfNumberRange {

    private static class Pack {
        private final Number number;
        private final double d;

        public Pack(Number number) {
            this.number = number;
            this.d = number.doubleValue();
        }

        @Override
        public String toString() {
            return this.number.toString();
        }

        public Number getNumber() {
            return number;
        }

        public double getDouble() {
            return this.d;
        }
    }

    private final Pack floor;

    private final Pack ceiling;

    public ExaminerOfNumberRange(Number floor, Number ceiling) {
        Examiner.refuseNullAndEmpty("floor", floor);
        Examiner.refuseNullAndEmpty("ceiling", ceiling);

        this.floor = new Pack(floor);
        this.ceiling = new Pack(ceiling);
    }

    public Number requireIncludeInclude(String term, Number arg) {
        return requireIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number requireIncludeInclude(String term, Number arg, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        double d_arg = arg.doubleValue();
        if (!(d_arg >= this.floor.getDouble() && d_arg <= this.ceiling.getDouble())) {
            thrownAction.acceptOrHandle(this.floor.getNumber(), this.ceiling.getNumber());
        }

        return arg;
    }

    public Number requireIncludeExclude(String term, Number arg) {
        return requireIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number requireIncludeExclude(String term, Number arg, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        double d_arg = arg.doubleValue();
        if (!(d_arg >= this.floor.getDouble() && d_arg < this.ceiling.getDouble())) {
            thrownAction.acceptOrHandle(this.floor.getNumber(), this.ceiling.getNumber());
        }

        return arg;
    }

    public Number requireExcludeInclude(String term, Number arg) {
        return requireExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number requireExcludeInclude(String term, Number arg, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        double d_arg = arg.doubleValue();
        if (!(d_arg > this.floor.getDouble() && d_arg <= this.ceiling.getDouble())) {
            thrownAction.acceptOrHandle(this.floor.getNumber(), this.ceiling.getNumber());
        }

        return arg;
    }

    public Number requireExcludeExclude(String term, Number arg) {
        return requireExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number requireExcludeExclude(String term, Number arg, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        double d_arg = arg.doubleValue();
        if (!(d_arg > this.floor.getDouble() && d_arg < this.ceiling.getDouble())) {
            thrownAction.acceptOrHandle(this.floor.getNumber(), this.ceiling.getNumber());
        }

        return arg;
    }

    public Number refuseIncludeInclude(String term, Number arg) {
        return refuseIncludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number refuseIncludeInclude(String term, Number arg, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        double d_arg = arg.doubleValue();
        if (d_arg >= this.floor.getDouble() && d_arg <= this.ceiling.getDouble()) {
            thrownAction.acceptOrHandle(this.floor.getNumber(), this.ceiling.getNumber());
        }

        return arg;
    }

    public Number refuseIncludeExclude(String term, Number arg) {
        return refuseIncludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number refuseIncludeExclude(String term, Number arg, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        double d_arg = arg.doubleValue();
        if (d_arg >= this.floor.getDouble() && d_arg < this.ceiling.getDouble()) {
            thrownAction.acceptOrHandle(this.floor.getNumber(), this.ceiling.getNumber());
        }

        return arg;
    }

    public Number refuseExcludeInclude(String term, Number arg) {
        return refuseExcludeInclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number refuseExcludeInclude(String term, Number arg, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        double d_arg = arg.doubleValue();
        if (d_arg > this.floor.getDouble() && d_arg <= this.ceiling.getDouble()) {
            thrownAction.acceptOrHandle(this.floor.getNumber(), this.ceiling.getNumber());
        }

        return arg;
    }

    public Number refuseExcludeExclude(String term, Number arg) {
        return refuseExcludeExclude(term, arg, (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> Number refuseExcludeExclude(String term, Number arg, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);

        double d_arg = arg.doubleValue();
        if (d_arg > this.floor.getDouble() && d_arg < this.ceiling.getDouble()) {
            thrownAction.acceptOrHandle(this.floor.getNumber(), this.ceiling.getNumber());
        }

        return arg;
    }

}
