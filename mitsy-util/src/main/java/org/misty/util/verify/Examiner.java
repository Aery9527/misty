package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Examiner {

    static void requireNullOrEmpty(String term, Object arg) throws MistyException {
        if (Judge.notNullAndEmpty(arg)) {
            String argString = arg instanceof Object[] ? Arrays.toString((Object[]) arg) : arg.toString();
            String description = ExaminerMessage.requireNullOrEmpty(term, argString);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    static void requireNullOrEmpty(String term, Optional<Object> optional) throws MistyException {
        if (Judge.notNullAndEmpty(optional)) {
            @SuppressWarnings("OptionalGetWithoutIsPresent") Object arg = optional.get();
            String argString = arg instanceof Object[] ? Arrays.toString((Object[]) arg) : arg.toString();
            String description = ExaminerMessage.requireNullOrEmpty(term, argString);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        }
    }

    static <ArgType> ArgType refuseNullAndEmpty(String term, ArgType arg) throws MistyException {
        if (Judge.isNullOrEmpty(arg)) {
            String description = ExaminerMessage.refuseNullAndEmpty(term);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent"})
    static <ArgType> ArgType refuseNullAndEmpty(String term, Optional<ArgType> arg) throws MistyException {
        if (Judge.isNullOrEmpty(arg)) {
            String description = ExaminerMessage.refuseNullAndEmpty(term);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg.get();
        }
    }

    static short requireInRange(String term, short arg, short floor, short ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static int requireInRange(String term, int arg, int floor, int ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static long requireInRange(String term, long arg, long floor, long ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static float requireInRange(String term, float arg, float floor, float ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static double requireInRange(String term, double arg, double floor, double ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static char requireInRange(String term, char arg, char floor, char ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static byte requireInRange(String term, byte arg, byte floor, byte ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static Character requireInRange(String term, Character arg, Character floor, Character ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static Short requireInRange(String term, Short arg, Short floor, Short ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static Integer requireInRange(String term, Integer arg, Integer floor, Integer ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static Long requireInRange(String term, Long arg, Long floor, Long ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static Float requireInRange(String term, Float arg, Float floor, Float ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static Double requireInRange(String term, Double arg, Double floor, Double ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static Byte requireInRange(String term, Byte arg, Byte floor, Byte ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    static BigDecimal requireInRange(String term, BigDecimal arg, Number floor, Number ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        // TODO
        return arg;
    }


    static <ArgType extends Number> ArgType requireInRange(String term, ArgType arg, ArgType floor, ArgType ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        Runnable error = () -> {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        };

        if (arg instanceof Short && ((Short) arg < (Short) floor || (Short) arg > (Short) ceiling)) {
            error.run();
        } else if (arg instanceof Integer && ((Integer) arg < (Integer) floor || (Integer) arg > (Integer) ceiling)) {
            error.run();
        } else if (arg instanceof Long && ((Long) arg < (Long) floor || (Long) arg > (Long) ceiling)) {
            error.run();
        } else if (arg instanceof Float && ((Float) arg < (Float) floor || (Float) arg > (Float) ceiling)) {
            error.run();
        } else if (arg instanceof Double && ((Double) arg < (Double) floor || (Double) arg > (Double) ceiling)) {
            error.run();
        } else if (arg instanceof Byte && ((Byte) arg < (Byte) floor || (Byte) arg > (Byte) ceiling)) {
            error.run();
        } else if (arg instanceof BigDecimal) {
            // XXX
            error.run();
        } else if (arg instanceof AtomicInteger) {
            // XXX
            error.run();
        } else if (arg instanceof AtomicLong) {
            // XXX
            error.run();
        } else if (arg instanceof BigInteger) {
            // XXX
            error.run();
        }

        return arg;
    }


}
