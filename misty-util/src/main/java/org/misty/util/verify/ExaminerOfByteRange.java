package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;

public class ExaminerOfByteRange {

    private final byte floor;

    private final byte ceiling;

    public ExaminerOfByteRange(byte floor, byte ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public byte requireIncludeInclude(String term, byte arg) throws MistyException {
        return requireIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> byte requireIncludeInclude(
            String term, byte arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeInclude(arg, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> byte requireIncludeInclude(
            byte arg, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public byte requireIncludeExclude(String term, byte arg) throws MistyException {
        return requireIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> byte requireIncludeExclude(
            String term, byte arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireIncludeExclude(arg, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> byte requireIncludeExclude(
            byte arg, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public byte requireExcludeInclude(String term, byte arg) throws MistyException {
        return requireExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> byte requireExcludeInclude(
            String term, byte arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeInclude(arg, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> byte requireExcludeInclude(
            byte arg, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg <= this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }
        return arg;
    }

    //

    public byte requireExcludeExclude(String term, byte arg) throws MistyException {
        return requireExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> byte requireExcludeExclude(
            String term, byte arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireExcludeExclude(arg, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.requireInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> byte requireExcludeExclude(
            byte arg, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > this.floor && arg < this.ceiling)) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public byte refuseIncludeInclude(String term, byte arg) throws MistyException {
        return refuseIncludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> byte refuseIncludeInclude(
            String term, byte arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeInclude(arg, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> byte refuseIncludeInclude(
            byte arg, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public byte refuseIncludeExclude(String term, byte arg) throws MistyException {
        return refuseIncludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> byte refuseIncludeExclude(
            String term, byte arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseIncludeExclude(arg, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.INCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> byte refuseIncludeExclude(
            byte arg, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg >= this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public byte refuseExcludeInclude(String term, byte arg) throws MistyException {
        return refuseExcludeInclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> byte refuseExcludeInclude(
            String term, byte arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeInclude(arg, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.INCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> byte refuseExcludeInclude(
            byte arg, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg <= this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

    //

    public byte refuseExcludeExclude(String term, byte arg) throws MistyException {
        return refuseExcludeExclude(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("DuplicatedCode")
    public <ThrowableType extends Exception> byte refuseExcludeExclude(
            String term, byte arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("term", term);
        Examiner.refuseNullAndEmpty("errorDefinition", errorDefinition);

        return refuseExcludeExclude(arg, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (floor, ceiling) -> {
            String description = ExaminerMessage.refuseInRange(term, arg,
                    ExamineIntervals.Floor.EXCLUDE, this.floor,
                    ExamineIntervals.Ceiling.EXCLUDE, this.ceiling);
            throw errorDefinition.thrown(description);
        });
    }

    public <ThrowableType extends Throwable> byte refuseExcludeExclude(
            byte arg, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        Examiner.refuseNullAndEmpty("thrownAction", thrownAction);

        if (arg > this.floor && arg < this.ceiling) {
            thrownAction.acceptOrHandle(this.floor, this.ceiling);
        }

        return arg;
    }

}
