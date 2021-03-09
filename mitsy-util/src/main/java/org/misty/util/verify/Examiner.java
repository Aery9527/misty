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


    /**
     * @see #requireInRange(String, Number, Number, Number, RangeIntervals)
     */
    static short requireInRange(String term, short arg, short floor, short ceiling, RangeIntervals intervals) throws MistyException {
        BooleanSupplier floorChecker = intervals.isFloorInclude() ? () -> arg >= floor : () -> arg > floor;
        BooleanSupplier ceilingChecker = intervals.isCeilingInclude() ? () -> arg <= ceiling : () -> arg < ceiling;

        if (floorChecker.getAsBoolean() && ceilingChecker.getAsBoolean()) {
            return arg;
        } else {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number, RangeIntervals)
     */
    static int requireInRange(String term, int arg, int floor, int ceiling, RangeIntervals intervals) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number, RangeIntervals)
     */
    static long requireInRange(String term, long arg, long floor, long ceiling, RangeIntervals intervals) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number, RangeIntervals)
     */
    static float requireInRange(String term, float arg, float floor, float ceiling, RangeIntervals intervals) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number, RangeIntervals)
     */
    static double requireInRange(String term, double arg, double floor, double ceiling, RangeIntervals intervals) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number, RangeIntervals)
     */
    static char requireInRange(String term, char arg, char floor, char ceiling, RangeIntervals intervals) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number, RangeIntervals)
     */
    static byte requireInRange(String term, byte arg, byte floor, byte ceiling, RangeIntervals intervals) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number, RangeIntervals)
     */
    static Character requireInRange(String term, Character arg, Character floor, Character ceiling, RangeIntervals intervals) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * check number in range [floor, ceiling], if not throw exception.
     * ex: [10, 20] mean include 10 and 20.
     *
     * @param term      if throw exception, the term is the keyword to identify situation
     * @param arg       tested number
     * @param floor     lowest number
     * @param ceiling   highest number
     * @param <ArgType> is number type
     * @return the number of input
     * @throws MistyException when number not in range [floor, ceiling]
     */
    static <ArgType extends Number> ArgType requireInRange(String term, ArgType arg, ArgType floor, ArgType ceiling, RangeIntervals intervals) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        double d_arg = arg.doubleValue();
        double d_floor = floor.doubleValue();
        double d_ceiling = ceiling.doubleValue();

        if (d_arg < d_floor || d_arg > d_ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling, intervals);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    // refuseInRange

    /**
     * @see #refuseInRange(String, Number, Number, Number)
     */
    static short refuseInRange(String term, short arg, short floor, short ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * @see #refuseInRange(String, Number, Number, Number)
     */
    static int refuseInRange(String term, int arg, int floor, int ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * @see #refuseInRange(String, Number, Number, Number)
     */
    static long refuseInRange(String term, long arg, long floor, long ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * @see #refuseInRange(String, Number, Number, Number)
     */
    static float refuseInRange(String term, float arg, float floor, float ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * @see #refuseInRange(String, Number, Number, Number)
     */
    static double refuseInRange(String term, double arg, double floor, double ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * @see #refuseInRange(String, Number, Number, Number)
     */
    static char refuseInRange(String term, char arg, char floor, char ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * @see #refuseInRange(String, Number, Number, Number)
     */
    static byte refuseInRange(String term, byte arg, byte floor, byte ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * @see #refuseInRange(String, Number, Number, Number)
     */
    static Character refuseInRange(String term, Character arg, Character floor, Character ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    /**
     * check number not in range (floor, ceiling), if not throw exception.
     * ex: (10, 20) mean exclude 10 and 20.
     *
     * @param term      if throw exception, the term is the keyword to identify situation
     * @param arg       tested number
     * @param floor     lowest number
     * @param ceiling   highest number
     * @param <ArgType> is number type
     * @return the number of input
     * @throws MistyException when number in range (floor, ceiling)
     */
    static <ArgType extends Number> ArgType refuseInRange(String term, ArgType arg, ArgType floor, ArgType ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        double d_arg = arg.doubleValue();
        double d_floor = floor.doubleValue();
        double d_ceiling = ceiling.doubleValue();

        if (d_arg < d_floor || d_arg > d_ceiling) {
            return arg;
        } else {
            String description = ExaminerMessage.refuseInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
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
