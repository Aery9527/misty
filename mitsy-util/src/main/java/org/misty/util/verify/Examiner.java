package org.misty.util.verify;

import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;

import java.util.Arrays;
import java.util.Optional;

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

    //requireInRange

    /**
     * @see #requireInRange(String, Number, Number, Number)
     */
    static short requireInRange(String term, short arg, short floor, short ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number)
     */
    static int requireInRange(String term, int arg, int floor, int ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number)
     */
    static long requireInRange(String term, long arg, long floor, long ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number)
     */
    static float requireInRange(String term, float arg, float floor, float ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number)
     */
    static double requireInRange(String term, double arg, double floor, double ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number)
     */
    static char requireInRange(String term, char arg, char floor, char ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number)
     */
    static byte requireInRange(String term, byte arg, byte floor, byte ceiling) throws MistyException {
        if (arg < floor || arg > ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
            throw MistyError.ARGUMENT_ERROR.thrown(description);
        } else {
            return arg;
        }
    }

    /**
     * @see #requireInRange(String, Number, Number, Number)
     */
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
    static <ArgType extends Number> ArgType requireInRange(String term, ArgType arg, ArgType floor, ArgType ceiling) throws MistyException {
        refuseNullAndEmpty("arg", arg);
        refuseNullAndEmpty("floor", floor);
        refuseNullAndEmpty("ceiling", ceiling);

        double d_arg = arg.doubleValue();
        double d_floor = floor.doubleValue();
        double d_ceiling = ceiling.doubleValue();

        if (d_arg < d_floor || d_arg > d_ceiling) {
            String description = ExaminerMessage.requireInRange(term, arg, floor, ceiling);
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

}
