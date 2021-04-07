package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;
import org.misty.util.tool.StringTool;

import java.math.BigDecimal;
import java.util.Optional;

public class Examiner {

    public static final ExaminerOfNumberRange INTEGER_RANGE_EXAMINER = new ExaminerOfNumberRange(Integer.MIN_VALUE, Integer.MAX_VALUE);

    public static final ExaminerOfNumberRange LONG_RANGE_EXAMINER = new ExaminerOfNumberRange(Long.MIN_VALUE, Long.MAX_VALUE);

    // requireNullOrEmpty of Object

    public static void requireNullOrEmpty(String term, Object arg) throws MistyException {
        requireNullOrEmpty(term, arg, (t, r) -> {
            String description = ExaminerMessage.requireNullOrEmpty(term, StringTool.toString(arg));
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ArgType, ThrowableType extends Throwable> void requireNullOrEmpty(
            String term, ArgType arg, FiBiConsumerThrow1<String, ArgType, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.notNullAndEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
    }

    // requireNullOrEmpty of Optional

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent"})
    public static void requireNullOrEmpty(String term, Optional<Object> arg) throws MistyException {
        requireNullOrEmpty(term, arg, (t, a) -> {
            String description = ExaminerMessage.requireNullOrEmpty(term, StringTool.toString(arg.get()));
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <ArgType, ThrowableType extends Throwable> void requireNullOrEmpty(
            String term, Optional<ArgType> arg, FiBiConsumerThrow1<String, Optional<ArgType>, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.notNullAndEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
    }

    // refuseNullAndEmpty of Object

    public static <ArgType> ArgType refuseNullAndEmpty(String term, ArgType arg) throws MistyException {
        return refuseNullAndEmpty(term, arg, (t, a) -> {
            String description = ExaminerMessage.refuseNullAndEmpty(term);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ArgType, ThrowableType extends Throwable> ArgType refuseNullAndEmpty(
            String term, ArgType arg, FiBiConsumerThrow1<String, ArgType, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
        return arg;
    }

    // refuseNullAndEmpty of Optional

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <ArgType> ArgType refuseNullAndEmpty(String term, Optional<ArgType> arg) throws MistyException {
        return refuseNullAndEmpty(term, arg, (t, a) -> {
            String description = ExaminerMessage.refuseNullAndEmpty(term);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent"})
    public static <ArgType, ThrowableType extends Throwable> ArgType refuseNullAndEmpty(
            String term, Optional<ArgType> arg, FiBiConsumerThrow1<String, Optional<ArgType>, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
        return arg.get();
    }

    // requireInRange

    public static ExaminerOfShortRange ofRange(short floor, short ceiling) throws MistyException {
        return new ExaminerOfShortRange(floor, ceiling);
    }

    public static ExaminerOfIntRange ofRange(int floor, int ceiling) throws MistyException {
        return new ExaminerOfIntRange(floor, ceiling);
    }

    public static ExaminerOfLongRange ofRange(long floor, long ceiling) throws MistyException {
        return new ExaminerOfLongRange(floor, ceiling);
    }

    public static ExaminerOfFloatRange ofRange(float floor, float ceiling) throws MistyException {
        return new ExaminerOfFloatRange(floor, ceiling);
    }

    public static ExaminerOfDoubleRange ofRange(double floor, double ceiling) throws MistyException {
        return new ExaminerOfDoubleRange(floor, ceiling);
    }

    public static ExaminerOfCharRange ofRange(char floor, char ceiling) throws MistyException {
        return new ExaminerOfCharRange(floor, ceiling);
    }

    public static ExaminerOfByteRange ofRange(byte floor, byte ceiling) throws MistyException {
        return new ExaminerOfByteRange(floor, ceiling);
    }

    public static ExaminerOfNumberRange ofRange(Number floor, Number ceiling) throws MistyException {
        return new ExaminerOfNumberRange(floor, ceiling);
    }

    // requireMoreInclude

    public static short requireMoreInclude(String term, short arg, short ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreInclude(arg, ceiling, (FiBiConsumerThrow1<Short, Short, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> short requireMoreInclude(short arg, short ceiling, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static int requireMoreInclude(String term, int arg, int ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreInclude(arg, ceiling, (FiBiConsumerThrow1<Integer, Integer, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> int requireMoreInclude(int arg, int ceiling, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static long requireMoreInclude(String term, long arg, long ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreInclude(arg, ceiling, (FiBiConsumerThrow1<Long, Long, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> long requireMoreInclude(long arg, long ceiling, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static float requireMoreInclude(String term, float arg, float ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreInclude(arg, ceiling, (FiBiConsumerThrow1<Float, Float, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> float requireMoreInclude(float arg, float ceiling, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static double requireMoreInclude(String term, double arg, double ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreInclude(arg, ceiling, (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> double requireMoreInclude(double arg, double ceiling, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static char requireMoreInclude(String term, char arg, char ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreInclude(arg, ceiling, (FiBiConsumerThrow1<Character, Character, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> char requireMoreInclude(char arg, char ceiling, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static byte requireMoreInclude(String term, byte arg, byte ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreInclude(arg, ceiling, (FiBiConsumerThrow1<Byte, Byte, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> byte requireMoreInclude(byte arg, byte ceiling, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg >= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static <ArgType extends Number> ArgType requireMoreInclude(String term, ArgType arg, ArgType ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreInclude(arg, ceiling, (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ArgType extends Number, ThrowableType extends Throwable> ArgType requireMoreInclude(
            ArgType arg, ArgType ceiling, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("ceiling", ceiling);

        double d_arg = arg.doubleValue();
        double d_ceiling = ceiling.doubleValue();

        if (!(d_arg >= d_ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }

        return arg;
    }

    // requireMoreExclude

    public static short requireMoreExclude(String term, short arg, short ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreExclude(arg, ceiling, (FiBiConsumerThrow1<Short, Short, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> short requireMoreExclude(short arg, short ceiling, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static int requireMoreExclude(String term, int arg, int ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreExclude(arg, ceiling, (FiBiConsumerThrow1<Integer, Integer, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> int requireMoreExclude(int arg, int ceiling, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static long requireMoreExclude(String term, long arg, long ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreExclude(arg, ceiling, (FiBiConsumerThrow1<Long, Long, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> long requireMoreExclude(long arg, long ceiling, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static float requireMoreExclude(String term, float arg, float ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreExclude(arg, ceiling, (FiBiConsumerThrow1<Float, Float, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> float requireMoreExclude(float arg, float ceiling, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static double requireMoreExclude(String term, double arg, double ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreExclude(arg, ceiling, (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> double requireMoreExclude(double arg, double ceiling, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static char requireMoreExclude(String term, char arg, char ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreExclude(arg, ceiling, (FiBiConsumerThrow1<Character, Character, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> char requireMoreExclude(char arg, char ceiling, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static byte requireMoreExclude(String term, byte arg, byte ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreExclude(arg, ceiling, (FiBiConsumerThrow1<Byte, Byte, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> byte requireMoreExclude(byte arg, byte ceiling, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg > ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static <ArgType extends Number> ArgType requireMoreExclude(String term, ArgType arg, ArgType ceiling) {
        refuseNullAndEmpty("term", term);

        return requireMoreExclude(arg, ceiling, (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ArgType extends Number, ThrowableType extends Throwable> ArgType requireMoreExclude(
            ArgType arg, ArgType ceiling, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("ceiling", ceiling);

        double d_arg = arg.doubleValue();
        double d_ceiling = ceiling.doubleValue();

        if (!(d_arg > d_ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }

        return arg;
    }

    // requireLessInclude

    public static short requireLessInclude(String term, short arg, short ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessInclude(arg, ceiling, (FiBiConsumerThrow1<Short, Short, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> short requireLessInclude(short arg, short ceiling, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg <= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static int requireLessInclude(String term, int arg, int ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessInclude(arg, ceiling, (FiBiConsumerThrow1<Integer, Integer, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> int requireLessInclude(int arg, int ceiling, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg <= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static long requireLessInclude(String term, long arg, long ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessInclude(arg, ceiling, (FiBiConsumerThrow1<Long, Long, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> long requireLessInclude(long arg, long ceiling, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg <= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static float requireLessInclude(String term, float arg, float ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessInclude(arg, ceiling, (FiBiConsumerThrow1<Float, Float, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> float requireLessInclude(float arg, float ceiling, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg <= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static double requireLessInclude(String term, double arg, double ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessInclude(arg, ceiling, (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> double requireLessInclude(double arg, double ceiling, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg <= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static char requireLessInclude(String term, char arg, char ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessInclude(arg, ceiling, (FiBiConsumerThrow1<Character, Character, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> char requireLessInclude(char arg, char ceiling, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg <= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static byte requireLessInclude(String term, byte arg, byte ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessInclude(arg, ceiling, (FiBiConsumerThrow1<Byte, Byte, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> byte requireLessInclude(byte arg, byte ceiling, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg <= ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static <ArgType extends Number> ArgType requireLessInclude(String term, ArgType arg, ArgType ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessInclude(arg, ceiling, (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ArgType extends Number, ThrowableType extends Throwable> ArgType requireLessInclude(
            ArgType arg, ArgType ceiling, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("ceiling", ceiling);

        double d_arg = arg.doubleValue();
        double d_ceiling = ceiling.doubleValue();

        if (!(d_arg <= d_ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }

        return arg;
    }

    // requireLessExclude

    public static short requireLessExclude(String term, short arg, short ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessExclude(arg, ceiling, (FiBiConsumerThrow1<Short, Short, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> short requireLessExclude(short arg, short ceiling, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg < ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static int requireLessExclude(String term, int arg, int ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessExclude(arg, ceiling, (FiBiConsumerThrow1<Integer, Integer, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> int requireLessExclude(int arg, int ceiling, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg < ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static long requireLessExclude(String term, long arg, long ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessExclude(arg, ceiling, (FiBiConsumerThrow1<Long, Long, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> long requireLessExclude(long arg, long ceiling, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg < ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static float requireLessExclude(String term, float arg, float ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessExclude(arg, ceiling, (FiBiConsumerThrow1<Float, Float, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> float requireLessExclude(float arg, float ceiling, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg < ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static double requireLessExclude(String term, double arg, double ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessExclude(arg, ceiling, (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> double requireLessExclude(double arg, double ceiling, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg < ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static char requireLessExclude(String term, char arg, char ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessExclude(arg, ceiling, (FiBiConsumerThrow1<Character, Character, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> char requireLessExclude(char arg, char ceiling, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg < ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static byte requireLessExclude(String term, byte arg, byte ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessExclude(arg, ceiling, (FiBiConsumerThrow1<Byte, Byte, MistyException>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> byte requireLessExclude(byte arg, byte ceiling, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction) throws ThrowableType {
        if (!(arg < ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }
        return arg;
    }

    public static <ArgType extends Number> ArgType requireLessExclude(String term, ArgType arg, ArgType ceiling) {
        refuseNullAndEmpty("term", term);

        return requireLessExclude(arg, ceiling, (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
    }

    public static <ArgType extends Number, ThrowableType extends Throwable> ArgType requireLessExclude(
            ArgType arg, ArgType ceiling, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction) throws ThrowableType {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("ceiling", ceiling);

        double d_arg = arg.doubleValue();
        double d_ceiling = ceiling.doubleValue();

        if (!(d_arg < d_ceiling)) {
            thrownAction.acceptOrHandle(arg, ceiling);
        }

        return arg;
    }

    // require number

    public static BigDecimal requireNumber(String value) {
        refuseNullAndEmpty("value", value);

        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            String description = ExaminerMessage.requireNumber("value", value);
            throw MistyError.ARGUMENT_ERROR.thrown(description, e);
        }
    }

    public static int requireInteger(String value) {
        BigDecimal bigDecimal = requireNumber(value);
        INTEGER_RANGE_EXAMINER.requireIncludeInclude(bigDecimal, (floor, ceiling) -> {
            String description = ExaminerMessage.requireInteger("value", value);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
        return bigDecimal.intValue();
    }

    public static long requireLong(String value) {
        BigDecimal bigDecimal = requireNumber(value);
        LONG_RANGE_EXAMINER.requireIncludeInclude(bigDecimal, (floor, ceiling) -> {
            String description = ExaminerMessage.requireLong("value", value);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        });
        return bigDecimal.longValue();
    }

    public static float requireFloat(String value) {
        BigDecimal bigDecimal = requireNumber(value);
        // XXX 由於浮點數的下限不定所以不曉得怎麼檢查, 就暫時先略過
        return bigDecimal.floatValue();
    }

    public static double requireDouble(String value) {
        BigDecimal bigDecimal = requireNumber(value);
        // XXX 由於浮點數的下限不定所以不曉得怎麼檢查, 就暫時先略過
        return bigDecimal.doubleValue();
    }

}
