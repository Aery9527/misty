package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;
import org.misty.util.fi.FiRunnableThrow1;
import org.misty.util.tool.StringTool;

import java.util.Optional;
import java.util.function.BooleanSupplier;

public class Examiner {

    // requireNullOrEmpty of Object

    static void requireNullOrEmpty(String term, Object arg) throws MistyException {
        requireNullOrEmpty(arg, () -> {
            String description = ExaminerMessage.requireNullOrEmpty(term, StringTool.toString(arg));
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> void requireNullOrEmpty(
            Object arg, FiRunnableThrow1<ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.notNullAndEmpty(arg)) {
            thrownAction.runOrHandle();
        }
    }

    public static <ThrowableType extends Throwable> void requireNullOrEmpty(
            String term, Object optional, FiBiConsumerThrow1<String, Object, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.notNullAndEmpty(optional)) {
            thrownAction.acceptOrHandle(term, optional);
        }
    }

    // requireNullOrEmpty of Optional

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent"})
    static void requireNullOrEmpty(String term, Optional<Object> arg) throws MistyException {
        requireNullOrEmpty(arg, () -> {
            String description = ExaminerMessage.requireNullOrEmpty(term, StringTool.toString(arg.get()));
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <ThrowableType extends Throwable> void requireNullOrEmpty(
            Optional<Object> arg, FiRunnableThrow1<ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.notNullAndEmpty(arg)) {
            thrownAction.runOrHandle();
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <ThrowableType extends Throwable> void requireNullOrEmpty(
            String term, Optional<Object> arg, FiBiConsumerThrow1<String, Optional<Object>, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.notNullAndEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
    }

    // refuseNullAndEmpty of Object

    static <ArgType> ArgType refuseNullAndEmpty(String term, ArgType arg) throws MistyException {
        return refuseNullAndEmpty(arg, () -> {
            String description = ExaminerMessage.refuseNullAndEmpty(term);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    static <ArgType, ThrowableType extends Throwable> ArgType refuseNullAndEmpty(
            ArgType arg, FiRunnableThrow1<ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(arg)) {
            thrownAction.runOrHandle();
        }
        return arg;
    }

    static <ArgType, ThrowableType extends Throwable> ArgType refuseNullAndEmpty(
            String term, ArgType arg, FiBiConsumerThrow1<String, ArgType, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
        return arg;
    }

    // refuseNullAndEmpty of Optional

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    static <ArgType> ArgType refuseNullAndEmpty(String term, Optional<ArgType> arg) throws MistyException {
        return refuseNullAndEmpty(arg, () -> {
            String description = ExaminerMessage.refuseNullAndEmpty(term);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent"})
    static <ArgType, ThrowableType extends Throwable> ArgType refuseNullAndEmpty(
            Optional<ArgType> arg, FiRunnableThrow1<ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(arg)) {
            thrownAction.runOrHandle();
        }
        return arg.get();
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent"})
    static <ArgType, ThrowableType extends Throwable> ArgType refuseNullAndEmpty(
            String term, Optional<ArgType> arg, FiBiConsumerThrow1<String, Optional<ArgType>, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
        return arg.get();
    }

    // requireInRange

    static ExaminerOfShortRange ofRange(short floor, short ceiling) throws MistyException {
        return new ExaminerOfShortRange(floor, ceiling);
    }

    static ExaminerOfIntRange ofRange(int floor, int ceiling) throws MistyException {
        return new ExaminerOfIntRange(floor, ceiling);
    }

    static ExaminerOfLongRange ofRange(long floor, long ceiling) throws MistyException {
        return new ExaminerOfLongRange(floor, ceiling);
    }

    static ExaminerOfFloatRange ofRange(float floor, float ceiling) throws MistyException {
        return new ExaminerOfFloatRange(floor, ceiling);
    }

    static ExaminerOfDoubleRange ofRange(double floor, double ceiling) throws MistyException {
        return new ExaminerOfDoubleRange(floor, ceiling);
    }

    static ExaminerOfCharRange ofRange(char floor, char ceiling) throws MistyException {
        return new ExaminerOfCharRange(floor, ceiling);
    }

    static ExaminerOfByteRange ofRange(byte floor, byte ceiling) throws MistyException {
        return new ExaminerOfByteRange(floor, ceiling);
    }

    static ExaminerOfNumberRange ofRange(Number floor, Number ceiling) throws MistyException {
        return new ExaminerOfNumberRange(floor, ceiling);
    }

    // requireMoreEqual

    static short requireMoreEqual(String term, short arg, short floor) {
        if (arg < floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static int requireMoreEqual(String term, int arg, int floor) {
        if (arg < floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static long requireMoreEqual(String term, long arg, long floor) {
        if (arg < floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static float requireMoreEqual(String term, float arg, float floor) {
        if (arg < floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static double requireMoreEqual(String term, double arg, double floor) {
        if (arg < floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static char requireMoreEqual(String term, char arg, char floor) {
        if (arg < floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static byte requireMoreEqual(String term, byte arg, byte floor) {
        if (arg < floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static Character requireMoreEqual(String term, Character arg, Character floor) {
        if (arg < floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static <ArgType extends Number> ArgType requireMoreEqual(String term, ArgType arg, ArgType floor) {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);

        double d_arg = arg.doubleValue();
        double d_floor = floor.doubleValue();

        if (d_arg < d_floor) {
            String description = ExaminerMessage.requireMoreEqual(term, arg, floor);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }


}
