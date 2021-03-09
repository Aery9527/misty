package org.misty.util.verify;

import org.misty.util.error.MistyError;

public class ExaminerOfShortRange {

    private final short floor;

    private final short ceiling;

    public ExaminerOfShortRange(short floor, short ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public short requireIncludeBoth(String term, short arg) {
        if (arg >= this.floor && arg <= this.ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    public short requireExcludeBoth(String term, short arg) {
        if (arg > this.floor && arg < this.ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }



}
