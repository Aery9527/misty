package org.misty.util.verify;

import org.misty.util.error.MistyError;

public class ExaminerOfCharRange {

    private final char floor;

    private final char ceiling;

    public ExaminerOfCharRange(char floor, char ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public char requireIncludeInclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg <= this.ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public char requireIncludeExclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg < this.ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public char requireExcludeInclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg <= this.ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public char requireExcludeExclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg < this.ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public char refuseIncludeInclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg <= this.ceiling) {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    public char refuseIncludeExclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg >= this.floor && arg < this.ceiling) {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    public char refuseExcludeInclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg <= this.ceiling) {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    public char refuseExcludeExclude(String term, char arg) {
        Examiner.refuseNullAndEmpty("term", term);

        if (arg > this.floor && arg < this.ceiling) {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

}
