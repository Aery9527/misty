package org.misty.util.verify;

import org.misty.util.error.MistyError;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class ExaminerOfNumberRange {

    private static abstract class Compares {
        private final Number number;
        private final double d;

        public Compares(Number number) {
            this.number = number;
            this.d = number.doubleValue();
        }

        @Override
        public String toString() {
            return this.number.toString();
        }

        public double getDouble() {
            return this.d;
        }
    }

    private static class Isinteger extends Compares {
        public Isinteger(Number number) {
            super(number);
        }
    }

    private static class Nointeger extends Compares {
        public Nointeger(Number number) {
            super(number);
        }
    }

    private final Compares floor;

    private final Compares ceiling;

    public ExaminerOfNumberRange(Number floor, Number ceiling) {
        Examiner.refuseNullAndEmpty("floor", floor);
        Examiner.refuseNullAndEmpty("ceiling", ceiling);

        Function<Number, Compares> classifier = (n) -> {
            if (n instanceof Short || n instanceof Integer || n instanceof Long || n instanceof Byte ||
                    n instanceof AtomicInteger || n instanceof AtomicLong) {
                return new Isinteger(n);
            } else if (n instanceof Float || n instanceof Double || n instanceof BigDecimal) {
                return new Nointeger(n);
            } else {
                throw MistyError.UNHANDLED.thrown("unhandled number type " + n.getClass());
            }
        };

        this.floor = classifier.apply(floor);
        this.ceiling = classifier.apply(ceiling);
    }

    public Number requireIncludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg >= this.floor.getDouble() && d_arg <= this.ceiling.getDouble()) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public Number requireIncludeExclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg >= this.floor.getDouble() && d_arg < this.ceiling.getDouble()) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public Number requireExcludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg > this.floor.getDouble() && d_arg <= this.ceiling.getDouble()) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public Number requireExcludeExclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg > this.floor.getDouble() && d_arg < this.ceiling.getDouble()) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public Number refuseIncludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg >= this.floor.getDouble() && d_arg <= this.ceiling.getDouble()) {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    public Number refuseIncludeExclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg >= this.floor.getDouble() && d_arg < this.ceiling.getDouble()) {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    public Number refuseExcludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg > this.floor.getDouble() && d_arg <= this.ceiling.getDouble()) {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    public Number refuseExcludeExclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg > this.floor.getDouble() && d_arg < this.ceiling.getDouble()) {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

}
