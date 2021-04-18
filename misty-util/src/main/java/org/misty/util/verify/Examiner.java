package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;
import org.misty.util.fi.FiConsumerThrow1;
import org.misty.util.tool.StringTool;

import java.math.BigDecimal;
import java.util.Optional;

public class Examiner {

    public static final ExaminerOfNumberRange SHORT_RANGE_EXAMINER = new ExaminerOfNumberRange(Short.MIN_VALUE, Short.MAX_VALUE);

    public static final ExaminerOfNumberRange INTEGER_RANGE_EXAMINER = new ExaminerOfNumberRange(Integer.MIN_VALUE, Integer.MAX_VALUE);

    public static final ExaminerOfNumberRange LONG_RANGE_EXAMINER = new ExaminerOfNumberRange(Long.MIN_VALUE, Long.MAX_VALUE);

    public static BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else {
            return new BigDecimal(number.toString());
        }
    }

    // requireNullOrEmpty of Object

    public static void requireNullOrEmpty(String term, Object arg) throws MistyException {
        requireNullOrEmpty(term, arg, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> void requireNullOrEmpty(
            String term, Object arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(errorDefinition)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("errorDefinition"));
        }

        requireNullOrEmpty(term, arg, (t, r) -> {
            String description = ExaminerMessage.requireNullOrEmpty(term, StringTool.toString(arg));
            errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings("DuplicatedCode")
    public static <ArgType, ThrowableType extends Throwable> void requireNullOrEmpty(
            String term, ArgType arg, FiBiConsumerThrow1<String, ArgType, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(term)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("term"));
        }
        if (Judge.isNullOrEmpty(thrownAction)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("thrownAction"));
        }

        if (Judge.notNullAndEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
    }

    // requireNullOrEmpty of Optional

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <ArgType> void requireNullOrEmpty(String term, Optional<ArgType> arg) throws MistyException {
        requireNullOrEmpty(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent"})
    public static <ArgType, ThrowableType extends Exception> void requireNullOrEmpty(
            String term, Optional<ArgType> arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(errorDefinition)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("errorDefinition"));
        }

        requireNullOrEmpty(term, arg, (t, a) -> {
            String description = ExaminerMessage.requireNullOrEmpty(term, StringTool.toString(arg.get()));
            errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "DuplicatedCode"})
    public static <ArgType, ThrowableType extends Throwable> void requireNullOrEmpty(
            String term, Optional<ArgType> arg, FiBiConsumerThrow1<String, Optional<ArgType>, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(term)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("term"));
        }
        if (Judge.isNullOrEmpty(thrownAction)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("thrownAction"));
        }

        if (Judge.notNullAndEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
    }

    // refuseNullAndEmpty of Object

    public static <ArgType> ArgType refuseNullAndEmpty(String term, ArgType arg) throws MistyException {
        return refuseNullAndEmpty(term, arg, MistyError.ARGUMENT_ERROR);
    }

    public static <ArgType, ThrowableType extends Exception> ArgType refuseNullAndEmpty(
            String term, ArgType arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(errorDefinition)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("errorDefinition"));
        }

        return refuseNullAndEmpty(term, arg, (t, a) -> {
            String description = ExaminerMessage.refuseNullAndEmpty(term);
            errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings("DuplicatedCode")
    public static <ArgType, ThrowableType extends Throwable> ArgType refuseNullAndEmpty(
            String term, ArgType arg, FiBiConsumerThrow1<String, ArgType, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(term)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("term"));
        }
        if (Judge.isNullOrEmpty(thrownAction)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("thrownAction"));
        }

        if (Judge.isNullOrEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
        return arg;
    }

    // refuseNullAndEmpty of Optional

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <ArgType> ArgType refuseNullAndEmpty(String term, Optional<ArgType> arg) throws MistyException {
        return refuseNullAndEmpty(term, arg, MistyError.ARGUMENT_ERROR);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <ArgType, ThrowableType extends Exception> ArgType refuseNullAndEmpty(
            String term, Optional<ArgType> arg, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(errorDefinition)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("errorDefinition"));
        }

        return refuseNullAndEmpty(term, arg, (t, a) -> {
            String description = ExaminerMessage.refuseNullAndEmpty(term);
            errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent", "DuplicatedCode"})
    public static <ArgType, ThrowableType extends Throwable> ArgType refuseNullAndEmpty(
            String term, Optional<ArgType> arg, FiBiConsumerThrow1<String, Optional<ArgType>, ThrowableType> thrownAction
    ) throws ThrowableType {
        if (Judge.isNullOrEmpty(term)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("term"));
        }
        if (Judge.isNullOrEmpty(thrownAction)) {
            MistyError.ARGUMENT_ERROR.thrown(ExaminerMessage.refuseNullAndEmpty("thrownAction"));
        }

        if (Judge.isNullOrEmpty(arg)) {
            thrownAction.acceptOrHandle(term, arg);
        }
        return arg.get();
    }

    // requireInRange

    public static ExaminerOfShortRange ofRange(short floor, short ceiling) {
        return new ExaminerOfShortRange(floor, ceiling);
    }

    public static ExaminerOfIntRange ofRange(int floor, int ceiling) {
        return new ExaminerOfIntRange(floor, ceiling);
    }

    public static ExaminerOfLongRange ofRange(long floor, long ceiling) {
        return new ExaminerOfLongRange(floor, ceiling);
    }

    public static ExaminerOfFloatRange ofRange(float floor, float ceiling) {
        return new ExaminerOfFloatRange(floor, ceiling);
    }

    public static ExaminerOfDoubleRange ofRange(double floor, double ceiling) {
        return new ExaminerOfDoubleRange(floor, ceiling);
    }

    public static ExaminerOfCharRange ofRange(char floor, char ceiling) {
        return new ExaminerOfCharRange(floor, ceiling);
    }

    public static ExaminerOfByteRange ofRange(byte floor, byte ceiling) {
        return new ExaminerOfByteRange(floor, ceiling);
    }

    public static ExaminerOfNumberRange ofRange(Number floor, Number ceiling) {
        return new ExaminerOfNumberRange(floor, ceiling);
    }

    // requireMoreInclude

    public static short requireMoreInclude(String term, short arg, short border) throws MistyException {
        return requireMoreInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> short requireMoreInclude(
            String term, short arg, short border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreInclude(arg, border, (FiBiConsumerThrow1<Short, Short, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> short requireMoreInclude(
            short arg, short border, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static int requireMoreInclude(String term, int arg, int border) throws MistyException {
        return requireMoreInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> int requireMoreInclude(
            String term, int arg, int border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreInclude(arg, border, (FiBiConsumerThrow1<Integer, Integer, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> int requireMoreInclude(
            int arg, int border, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static long requireMoreInclude(String term, long arg, long border) throws MistyException {
        return requireMoreInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> long requireMoreInclude(
            String term, long arg, long border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreInclude(arg, border, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> long requireMoreInclude(
            long arg, long border, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static float requireMoreInclude(String term, float arg, float border) throws MistyException {
        return requireMoreInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> float requireMoreInclude(
            String term, float arg, float border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreInclude(arg, border, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> float requireMoreInclude(
            float arg, float border, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static double requireMoreInclude(String term, double arg, double border) throws MistyException {
        return requireMoreInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> double requireMoreInclude(
            String term, double arg, double border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreInclude(arg, border, (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> double requireMoreInclude(
            double arg, double border, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static char requireMoreInclude(String term, char arg, char border) throws MistyException {
        return requireMoreInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> char requireMoreInclude(
            String term, char arg, char border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreInclude(arg, border, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> char requireMoreInclude(
            char arg, char border, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static byte requireMoreInclude(String term, byte arg, byte border) throws MistyException {
        return requireMoreInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> byte requireMoreInclude(
            String term, byte arg, byte border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreInclude(arg, border, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> byte requireMoreInclude(
            byte arg, byte border, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg >= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static <ArgType extends Number> ArgType requireMoreInclude(String term, ArgType arg, ArgType border) throws MistyException {
        return requireMoreInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ArgType extends Number, ThrowableType extends Exception> ArgType requireMoreInclude(
            String term, ArgType arg, ArgType border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreInclude(arg, border, (a, c) -> {
            String description = ExaminerMessage.requireMoreInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ArgType extends Number, ThrowableType extends Throwable> ArgType requireMoreInclude(
            ArgType arg, ArgType border, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("border", border);
        refuseNullAndEmpty("thrownAction", thrownAction);

        BigDecimal argBD = toBigDecimal(arg);
        BigDecimal borderBD = toBigDecimal(border);

        int offset = argBD.subtract(borderBD).compareTo(BigDecimal.ZERO);
        if (offset < 0) {
            thrownAction.acceptOrHandle(arg, border);
        }

        return arg;
    }

    // requireMoreExclude

    public static short requireMoreExclude(String term, short arg, short border) throws MistyException {
        return requireMoreExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> short requireMoreExclude(
            String term, short arg, short border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreExclude(arg, border, (FiBiConsumerThrow1<Short, Short, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> short requireMoreExclude(
            short arg, short border, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static int requireMoreExclude(String term, int arg, int border) throws MistyException {
        return requireMoreExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> int requireMoreExclude(
            String term, int arg, int border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreExclude(arg, border, (FiBiConsumerThrow1<Integer, Integer, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> int requireMoreExclude(
            int arg, int border, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static long requireMoreExclude(String term, long arg, long border) throws MistyException {
        return requireMoreExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> long requireMoreExclude(
            String term, long arg, long border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreExclude(arg, border, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> long requireMoreExclude(
            long arg, long border, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static float requireMoreExclude(String term, float arg, float border) throws MistyException {
        return requireMoreExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> float requireMoreExclude(
            String term, float arg, float border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreExclude(arg, border, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> float requireMoreExclude(
            float arg, float border, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static double requireMoreExclude(String term, double arg, double border) throws MistyException {
        return requireMoreExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> double requireMoreExclude(
            String term, double arg, double border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreExclude(arg, border, (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> double requireMoreExclude(
            double arg, double border, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static char requireMoreExclude(String term, char arg, char border) throws MistyException {
        return requireMoreExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> char requireMoreExclude(
            String term, char arg, char border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreExclude(arg, border, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> char requireMoreExclude(
            char arg, char border, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static byte requireMoreExclude(String term, byte arg, byte border) throws MistyException {
        return requireMoreExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> byte requireMoreExclude(
            String term, byte arg, byte border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreExclude(arg, border, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> byte requireMoreExclude(
            byte arg, byte border, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg > border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static <ArgType extends Number> ArgType requireMoreExclude(String term, ArgType arg, ArgType border) throws MistyException {
        return requireMoreExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ArgType extends Number, ThrowableType extends Exception> ArgType requireMoreExclude(
            String term, ArgType arg, ArgType border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireMoreExclude(arg, border, (a, c) -> {
            String description = ExaminerMessage.requireMoreExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ArgType extends Number, ThrowableType extends Throwable> ArgType requireMoreExclude(
            ArgType arg, ArgType border, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("border", border);
        refuseNullAndEmpty("thrownAction", thrownAction);

        BigDecimal argBD = toBigDecimal(arg);
        BigDecimal borderBD = toBigDecimal(border);

        int offset = argBD.subtract(borderBD).compareTo(BigDecimal.ZERO);
        if (offset <= 0) {
            thrownAction.acceptOrHandle(arg, border);
        }

        return arg;
    }

    // requireLessInclude

    public static short requireLessInclude(String term, short arg, short border) throws MistyException {
        return requireLessInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> short requireLessInclude(
            String term, short arg, short border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessInclude(arg, border, (FiBiConsumerThrow1<Short, Short, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> short requireLessInclude(
            short arg, short border, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg <= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static int requireLessInclude(String term, int arg, int border) throws MistyException {
        return requireLessInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> int requireLessInclude(
            String term, int arg, int border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessInclude(arg, border, (FiBiConsumerThrow1<Integer, Integer, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> int requireLessInclude(
            int arg, int border, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg <= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static long requireLessInclude(String term, long arg, long border) throws MistyException {
        return requireLessInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> long requireLessInclude(
            String term, long arg, long border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessInclude(arg, border, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> long requireLessInclude(
            long arg, long border, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg <= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static float requireLessInclude(String term, float arg, float border) throws MistyException {
        return requireLessInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> float requireLessInclude(
            String term, float arg, float border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessInclude(arg, border, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> float requireLessInclude(
            float arg, float border, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg <= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static double requireLessInclude(String term, double arg, double border) throws MistyException {
        return requireLessInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> double requireLessInclude(
            String term, double arg, double border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessInclude(arg, border, (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> double requireLessInclude(
            double arg, double border, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg <= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static char requireLessInclude(String term, char arg, char border) throws MistyException {
        return requireLessInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> char requireLessInclude(
            String term, char arg, char border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessInclude(arg, border, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> char requireLessInclude(
            char arg, char border, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg <= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static byte requireLessInclude(String term, byte arg, byte border) throws MistyException {
        return requireLessInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> byte requireLessInclude(
            String term, byte arg, byte border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessInclude(arg, border, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> byte requireLessInclude(
            byte arg, byte border, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg <= border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static <ArgType extends Number> ArgType requireLessInclude(String term, ArgType arg, ArgType border) throws MistyException {
        return requireLessInclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ArgType extends Number, ThrowableType extends Exception> ArgType requireLessInclude(
            String term, ArgType arg, ArgType border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessInclude(arg, border, (a, c) -> {
            String description = ExaminerMessage.requireLessInclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ArgType extends Number, ThrowableType extends Throwable> ArgType requireLessInclude(
            ArgType arg, ArgType border, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("border", border);
        refuseNullAndEmpty("thrownAction", thrownAction);

        BigDecimal argBD = toBigDecimal(arg);
        BigDecimal borderBD = toBigDecimal(border);

        int offset = argBD.subtract(borderBD).compareTo(BigDecimal.ZERO);
        if (offset > 0) {
            thrownAction.acceptOrHandle(arg, border);
        }

        return arg;
    }

    // requireLessExclude

    public static short requireLessExclude(String term, short arg, short border) throws MistyException {
        return requireLessExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> short requireLessExclude(
            String term, short arg, short border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessExclude(arg, border, (FiBiConsumerThrow1<Short, Short, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> short requireLessExclude(
            short arg, short border, FiBiConsumerThrow1<Short, Short, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg < border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static int requireLessExclude(String term, int arg, int border) throws MistyException {
        return requireLessExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> int requireLessExclude(
            String term, int arg, int border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessExclude(arg, border, (FiBiConsumerThrow1<Integer, Integer, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> int requireLessExclude(
            int arg, int border, FiBiConsumerThrow1<Integer, Integer, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg < border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static long requireLessExclude(String term, long arg, long border) throws MistyException {
        return requireLessExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> long requireLessExclude(
            String term, long arg, long border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessExclude(arg, border, (FiBiConsumerThrow1<Long, Long, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> long requireLessExclude(
            long arg, long border, FiBiConsumerThrow1<Long, Long, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg < border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static float requireLessExclude(String term, float arg, float border) throws MistyException {
        return requireLessExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> float requireLessExclude(
            String term, float arg, float border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessExclude(arg, border, (FiBiConsumerThrow1<Float, Float, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> float requireLessExclude(
            float arg, float border, FiBiConsumerThrow1<Float, Float, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg < border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static double requireLessExclude(String term, double arg, double border) throws MistyException {
        return requireLessExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> double requireLessExclude(
            String term, double arg, double border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessExclude(arg, border, (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> double requireLessExclude(
            double arg, double border, FiBiConsumerThrow1<Double, Double, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg < border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static char requireLessExclude(String term, char arg, char border) throws MistyException {
        return requireLessExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> char requireLessExclude(
            String term, char arg, char border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessExclude(arg, border, (FiBiConsumerThrow1<Character, Character, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> char requireLessExclude(
            char arg, char border, FiBiConsumerThrow1<Character, Character, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg < border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static byte requireLessExclude(String term, byte arg, byte border) throws MistyException {
        return requireLessExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> byte requireLessExclude(
            String term, byte arg, byte border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessExclude(arg, border, (FiBiConsumerThrow1<Byte, Byte, ThrowableType>) (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> byte requireLessExclude(
            byte arg, byte border, FiBiConsumerThrow1<Byte, Byte, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("thrownAction", thrownAction);

        if (!(arg < border)) {
            thrownAction.acceptOrHandle(arg, border);
        }
        return arg;
    }

    public static <ArgType extends Number> ArgType requireLessExclude(String term, ArgType arg, ArgType border) throws MistyException {
        return requireLessExclude(term, arg, border, MistyError.ARGUMENT_ERROR);
    }

    public static <ArgType extends Number, ThrowableType extends Exception> ArgType requireLessExclude(
            String term, ArgType arg, ArgType border, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("term", term);
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLessExclude(arg, border, (a, c) -> {
            String description = ExaminerMessage.requireLessExclude(term, arg, border);
            errorDefinition.thrown(description);
        });
    }

    public static <ArgType extends Number, ThrowableType extends Throwable> ArgType requireLessExclude(
            ArgType arg, ArgType border, FiBiConsumerThrow1<Number, Number, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("border", border);
        refuseNullAndEmpty("thrownAction", thrownAction);

        BigDecimal argBD = toBigDecimal(arg);
        BigDecimal borderBD = toBigDecimal(border);

        int offset = argBD.subtract(borderBD).compareTo(BigDecimal.ZERO);
        if (offset >= 0) {
            thrownAction.acceptOrHandle(arg, border);
        }

        return arg;
    }

    // require number

    public static BigDecimal requireNumber(String value) throws MistyException {
        return requireNumber(value, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> BigDecimal requireNumber(
            String value, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireNumber(value, (v) -> {
            String description = ExaminerMessage.requireNumber("value", value);
            throw errorDefinition.thrown(description);
        });
    }

    public static <ThrowableType extends Throwable> BigDecimal requireNumber(
            String value, FiConsumerThrow1<String, ThrowableType> thrownAction
    ) throws ThrowableType {
        refuseNullAndEmpty("value", value);
        refuseNullAndEmpty("thrownAction", thrownAction);

        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            thrownAction.acceptOrHandle(value);
            return null;
        }
    }

    public static short requireShort(String value) throws MistyException {
        return requireShort(value, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> short requireShort(
            String value, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireShort(value, (v) -> {
            String description = ExaminerMessage.requireShort("value", value);
            throw errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings("ConstantConditions")
    public static <ThrowableType extends Exception> short requireShort(
            String value, FiConsumerThrow1<String, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal bigDecimal = requireNumber(value, thrownAction);
        SHORT_RANGE_EXAMINER.requireIncludeInclude(bigDecimal, (floor, ceiling) -> thrownAction.acceptOrHandle(value));
        return bigDecimal.shortValue();
    }

    public static int requireInteger(String value) throws MistyException {
        return requireInteger(value, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> int requireInteger(
            String value, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireInteger(value, (v) -> {
            String description = ExaminerMessage.requireInteger("value", value);
            throw errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings("ConstantConditions")
    public static <ThrowableType extends Exception> int requireInteger(
            String value, FiConsumerThrow1<String, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal bigDecimal = requireNumber(value, thrownAction);
        INTEGER_RANGE_EXAMINER.requireIncludeInclude(bigDecimal, (floor, ceiling) -> thrownAction.acceptOrHandle(value));
        return bigDecimal.intValue();
    }

    public static long requireLong(String value) throws MistyException {
        return requireLong(value, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> long requireLong(
            String value, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireLong(value, (v) -> {
            String description = ExaminerMessage.requireLong("value", value);
            throw errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings("ConstantConditions")
    public static <ThrowableType extends Exception> long requireLong(
            String value, FiConsumerThrow1<String, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal bigDecimal = requireNumber(value, thrownAction);
        LONG_RANGE_EXAMINER.requireIncludeInclude(bigDecimal, (floor, ceiling) -> thrownAction.acceptOrHandle(value));
        return bigDecimal.intValue();
    }

    public static float requireFloat(String value) throws MistyException {
        return requireFloat(value, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> float requireFloat(
            String value, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireFloat(value, (v) -> {
            String description = ExaminerMessage.requireFloat("value", value);
            throw errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings("ConstantConditions")
    public static <ThrowableType extends Throwable> float requireFloat(
            String value, FiConsumerThrow1<String, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal bigDecimal = requireNumber(value, thrownAction);
        // XXX 由於浮點數的下限不定所以不曉得怎麼檢查, 就暫時先略過
        return bigDecimal.floatValue();
    }

    public static double requireDouble(String value) throws MistyException {
        return requireDouble(value, MistyError.ARGUMENT_ERROR);
    }

    public static <ThrowableType extends Exception> double requireDouble(
            String value, MistyErrorDefinition<ThrowableType> errorDefinition
    ) throws ThrowableType {
        refuseNullAndEmpty("errorDefinition", errorDefinition);

        return requireDouble(value, (v) -> {
            String description = ExaminerMessage.requireDouble("value", value);
            throw errorDefinition.thrown(description);
        });
    }

    @SuppressWarnings("ConstantConditions")
    public static <ThrowableType extends Throwable> double requireDouble(
            String value, FiConsumerThrow1<String, ThrowableType> thrownAction
    ) throws ThrowableType {
        BigDecimal bigDecimal = requireNumber(value, thrownAction);
        // XXX 由於浮點數的下限不定所以不曉得怎麼檢查, 就暫時先略過
        return bigDecimal.doubleValue();
    }

}
