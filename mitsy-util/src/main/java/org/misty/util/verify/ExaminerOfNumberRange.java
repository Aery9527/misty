package org.misty.util.verify;

import org.misty.util.error.MistyError;

public class ExaminerOfNumberRange {

    private final double floor;

    private final double ceiling;

    public ExaminerOfNumberRange(Number floor, Number ceiling) {
        Examiner.refuseNullAndEmpty("floor", floor);
        Examiner.refuseNullAndEmpty("ceiling", ceiling);

        this.floor = floor.doubleValue();
        this.ceiling = ceiling.doubleValue();
    }

    public Number requireIncludeInclude(String term, Number arg) {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("arg", arg);

        double d_arg = arg.doubleValue();
        if (d_arg >= this.floor && d_arg <= this.ceiling) {
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
        if (d_arg >= this.floor && d_arg < this.ceiling) {
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
        if (d_arg > this.floor && d_arg <= this.ceiling) {
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
        if (d_arg > this.floor && d_arg < this.ceiling) {
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
        if (d_arg >= this.floor && d_arg <= this.ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg,
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
        if (d_arg >= this.floor && d_arg < this.ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg,
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
        if (d_arg > this.floor && d_arg <= this.ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg,
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
        if (d_arg > this.floor && d_arg < this.ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

}
