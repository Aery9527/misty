package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfCharRange {

    private final char floor;

    private final char ceiling;

    public ExaminerOfCharRange(char floor, char ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public char requireIncludeInclude(String term, char arg) throws MistyException {
        return requireIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> char requireIncludeInclude(
            String term, char arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeInclude(arg, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireIncludeInclude(
            char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public char requireIncludeExclude(String term, char arg) throws MistyException {
        return requireIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> char requireIncludeExclude(
            String term, char arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeExclude(arg, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireIncludeExclude(
            char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public char requireExcludeInclude(String term, char arg) throws MistyException {
        return requireExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> char requireExcludeInclude(
            String term, char arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeInclude(arg, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireExcludeInclude(
            char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public char requireExcludeExclude(String term, char arg) throws MistyException {
        return requireExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> char requireExcludeExclude(
            String term, char arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeExclude(arg, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char requireExcludeExclude(
            char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public char refuseIncludeInclude(String term, char arg) throws MistyException {
        return refuseIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> char refuseIncludeInclude(
            String term, char arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeInclude(arg, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseIncludeInclude(
            char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public char refuseIncludeExclude(String term, char arg) throws MistyException {
        return refuseIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> char refuseIncludeExclude(
            String term, char arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeExclude(arg, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseIncludeExclude(
            char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public char refuseExcludeInclude(String term, char arg) throws MistyException {
        return refuseExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> char refuseExcludeInclude(
            String term, char arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeInclude(arg, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseExcludeInclude(
            char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public char refuseExcludeExclude(String term, char arg) throws MistyException {
        return refuseExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> char refuseExcludeExclude(
            String term, char arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeExclude(arg, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> char refuseExcludeExclude(
            char arg, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
