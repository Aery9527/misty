package org.misty.util.verify;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.misty.util.error.MistyError;
import org.misty.util.error.MistyErrorDefinition;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@SuppressWarnings({"rawtypes", "unchecked"})
class ExaminerTest {

    public static final Condition<Throwable> CONDITION = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
            "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

    public static final ExamineIntervals.Floor FI = ExamineIntervals.Floor.INCLUDE; // fi = floor include

    public static final ExamineIntervals.Floor FE = ExamineIntervals.Floor.EXCLUDE; // fe = floor exclude

    public static final ExamineIntervals.Ceiling CI = ExamineIntervals.Ceiling.INCLUDE; // ci = ceiling include

    public static final ExamineIntervals.Ceiling CE = ExamineIntervals.Ceiling.EXCLUDE; // ce = ceiling exclude

    public static final FiBiConsumerThrow1<String, Object, TestException> THROWN_ACTION = (term, arg) -> {
        throw new TestException();
    };

    public static class RangeTester<ArgType> {
        private final String term;
        private final ArgType floor;
        private final ArgType ceiling;

        public RangeTester(String term, ArgType floor, ArgType ceiling) {
            this.term = term;
            this.floor = floor;
            this.ceiling = ceiling;
        }

        public void test(ArgType arg, BiFunction<String, ArgType, ArgType> action,
                         ExamineIntervals.Floor floorIntervals, ExamineIntervals.Ceiling ceilingIntervals,
                         RangeType rangeType, boolean expectedOk) {
            if (expectedOk) {
                Assertions.assertThat(action.apply(this.term, arg)).isEqualTo(arg);
            } else {
                String msg;
                if (RangeType.REQUIRE.equals(rangeType)) {
                    msg = ExaminerMessage.requireInRange(this.term, arg,
                            floorIntervals, this.floor,
                            ceilingIntervals, this.ceiling);
                } else {
                    msg = ExaminerMessage.refuseInRange(this.term, arg,
                            floorIntervals, this.floor,
                            ceilingIntervals, this.ceiling);
                }

                Assertions.assertThatThrownBy(() -> action.apply(this.term, arg))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(msg);
            }
        }
    }

    public static class TestException extends RuntimeException {
    }

    public enum RangeType {
        REQUIRE, REFUSE
    }

    public static void assert_MistyError_ARGUMENT_ERROR(ThrowableAssert.ThrowingCallable shouldRaiseThrowable, String msg) {
        Assertions.assertThatThrownBy(shouldRaiseThrowable)
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(msg);
    }

    @Test
    public void requireNullOrEmpty_Object() {
        String term = "kerker";

        String errorMsg = ExaminerMessage.refuseNullAndEmpty("term");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(null, null), errorMsg);
        errorMsg = ExaminerMessage.refuseNullAndEmpty("errorDefinition");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, null, (MistyErrorDefinition) null), errorMsg);
        errorMsg = ExaminerMessage.refuseNullAndEmpty("thrownAction");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, null, (FiBiConsumerThrow1) null), errorMsg);

        Examiner.requireNullOrEmpty(term, (Object) null);
        Examiner.requireNullOrEmpty(term, (Object) null, MistyError.ARGUMENT_ERROR);
        Examiner.requireNullOrEmpty(term, (Object) null, THROWN_ACTION);

        Object o = new Object();
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, o.toString());
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, o), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, o, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, o, THROWN_ACTION))
                .isInstanceOf(TestException.class);

        String s = "9527";
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, s);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, s), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, s, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, s, THROWN_ACTION))
                .isInstanceOf(TestException.class);

        Collection<String> c = Collections.singletonList("");
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, c.toString());
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, c), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, c, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, c, THROWN_ACTION))
                .isInstanceOf(TestException.class);

        Map<String, String> m = Collections.singletonMap("", "");
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, m.toString());
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, m), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, m, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, m, THROWN_ACTION))
                .isInstanceOf(TestException.class);

        String[] a = new String[]{""};
        errorMsg = Arrays.toString(a);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, a), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, a, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, a, THROWN_ACTION))
                .isInstanceOf(TestException.class);
    }

    @Test
    public void requireNullOrEmpty_Optional() {
        String term = "kerker";

        String errorMsg = ExaminerMessage.refuseNullAndEmpty("term");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(null, (Optional) null), errorMsg);
        errorMsg = ExaminerMessage.refuseNullAndEmpty("errorDefinition");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, (Optional) null, (MistyErrorDefinition) null), errorMsg);
        errorMsg = ExaminerMessage.refuseNullAndEmpty("thrownAction");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, (Optional) null, (FiBiConsumerThrow1) null), errorMsg);

        Examiner.requireNullOrEmpty(term, (Optional) null);
        Examiner.requireNullOrEmpty(term, (Optional) null, MistyError.ARGUMENT_ERROR);
        Examiner.requireNullOrEmpty(term, (Optional) null, THROWN_ACTION::acceptOrHandle);

        Optional<Object> o = Optional.of(new Object());
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, o.get().toString());
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, o), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, o, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, o, THROWN_ACTION::acceptOrHandle))
                .isInstanceOf(TestException.class);

        Optional<String> s = Optional.of("9527");
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, s.get());
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, s), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, s, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, s, THROWN_ACTION::acceptOrHandle))
                .isInstanceOf(TestException.class);

        Optional<Collection<String>> c = Optional.of(Collections.singletonList(""));
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, c.get().toString());
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, c), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, c, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, c, THROWN_ACTION::acceptOrHandle))
                .isInstanceOf(TestException.class);

        Optional<Map<String, String>> m = Optional.of(Collections.singletonMap("", ""));
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, m.get().toString());
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, m), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, m, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, m, THROWN_ACTION::acceptOrHandle))
                .isInstanceOf(TestException.class);

        Optional<String[]> a = Optional.of(new String[]{""});
        errorMsg = ExaminerMessage.requireNullOrEmpty(term, Arrays.toString(a.get()));
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, a), errorMsg);
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.requireNullOrEmpty(term, a, MistyError.ARGUMENT_ERROR), errorMsg);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, a, THROWN_ACTION::acceptOrHandle))
                .isInstanceOf(TestException.class);
    }

    @Test
    public void refuseNullAndEmpty_Object() {
        String term = "kerker";

        String errorMsg = ExaminerMessage.refuseNullAndEmpty("term");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(null, (Object) null), errorMsg);
        errorMsg = ExaminerMessage.refuseNullAndEmpty("errorDefinition");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(term, (Object) null, (MistyErrorDefinition) null), errorMsg);
        errorMsg = ExaminerMessage.refuseNullAndEmpty("thrownAction");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(term, (Object) null, (FiBiConsumerThrow1) null), errorMsg);

        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(term, (Object) null)
                , ExaminerMessage.refuseNullAndEmpty(term));
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(term, (Object) null, MistyError.ARGUMENT_ERROR)
                , ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Object) null, THROWN_ACTION))
                .isInstanceOf(TestException.class);

        Object o = new Object();
        Examiner.refuseNullAndEmpty(term, o);
        Examiner.refuseNullAndEmpty(term, o, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, o, THROWN_ACTION);

        String s = "9527";
        Examiner.refuseNullAndEmpty(term, s);
        Examiner.refuseNullAndEmpty(term, s, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, s, THROWN_ACTION);

        Collection<String> c = Collections.singletonList("");
        Examiner.refuseNullAndEmpty(term, c);
        Examiner.refuseNullAndEmpty(term, c, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, c, THROWN_ACTION);

        Map<String, String> m = Collections.singletonMap("", "");
        Examiner.refuseNullAndEmpty(term, m);
        Examiner.refuseNullAndEmpty(term, m, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, m, THROWN_ACTION);

        String[] a = new String[]{""};
        Examiner.refuseNullAndEmpty(term, a);
        Examiner.refuseNullAndEmpty(term, a, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, a, THROWN_ACTION);
    }

    @Test
    public void refuseNullAndEmpty_Optional() {
        String term = "kerker";

        String errorMsg = ExaminerMessage.refuseNullAndEmpty("term");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(null, (Optional) null), errorMsg);
        errorMsg = ExaminerMessage.refuseNullAndEmpty("errorDefinition");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(term, (Optional) null, (MistyErrorDefinition) null), errorMsg);
        errorMsg = ExaminerMessage.refuseNullAndEmpty("thrownAction");
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(term, (Optional) null, (FiBiConsumerThrow1) null), errorMsg);

        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(term, (Optional) null)
                , ExaminerMessage.refuseNullAndEmpty(term));
        assert_MistyError_ARGUMENT_ERROR(() -> Examiner.refuseNullAndEmpty(term, (Optional) null, MistyError.ARGUMENT_ERROR)
                , ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Optional) null, THROWN_ACTION))
                .isInstanceOf(TestException.class);

        Optional<Object> o = Optional.of(new Object());
        Examiner.refuseNullAndEmpty(term, o);
        Examiner.refuseNullAndEmpty(term, o, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, o, THROWN_ACTION);

        Optional<String> s = Optional.of("9527");
        Examiner.refuseNullAndEmpty(term, s);
        Examiner.refuseNullAndEmpty(term, s, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, s, THROWN_ACTION);

        Optional<Collection<String>> c = Optional.of(Collections.singletonList(""));
        Examiner.refuseNullAndEmpty(term, c);
        Examiner.refuseNullAndEmpty(term, c, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, c, THROWN_ACTION);

        Optional<Map<String, String>> m = Optional.of(Collections.singletonMap("", ""));
        Examiner.refuseNullAndEmpty(term, m);
        Examiner.refuseNullAndEmpty(term, m, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, m, THROWN_ACTION);

        Optional<String[]> a = Optional.of(new String[]{""});
        Examiner.refuseNullAndEmpty(term, a);
        Examiner.refuseNullAndEmpty(term, a, MistyError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty(term, a, THROWN_ACTION);
    }

    // requireInRange

//    @Test
//    public void test_ofRange_short() {
//        short testFloor = -2;
//        short testCeiling = 2;
//        short rangeFloor = -1;
//        short rangeCeiling = 1;
//        short unit = 1;
//        RangeTester<Short> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);
//
//        ExaminerOfShortRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
//        for (short i = testFloor; i <= testCeiling; i += unit) {
//            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
//            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
//            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
//            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;
//
//            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
//            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
//            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
//            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);
//
//            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
//            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
//            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
//            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
//        }
//    }
//
//    @Test
//    public void test_ofRange_int() {
//        int testFloor = -2;
//        int testCeiling = 2;
//        int rangeFloor = -1;
//        int rangeCeiling = 1;
//        int unit = 1;
//        RangeTester<Integer> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);
//
//        ExaminerOfIntRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
//        for (int i = testFloor; i <= testCeiling; i += unit) {
//            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
//            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
//            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
//            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;
//
//            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
//            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
//            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
//            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);
//
//            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
//            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
//            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
//            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
//        }
//    }
//
//    @Test
//    public void test_ofRange_long() {
//        long testFloor = -2;
//        long testCeiling = 2;
//        long rangeFloor = -1;
//        long rangeCeiling = 1;
//        long unit = 1;
//        RangeTester<Long> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);
//
//        ExaminerOfLongRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
//        for (long i = testFloor; i <= testCeiling; i += unit) {
//            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
//            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
//            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
//            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;
//
//            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
//            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
//            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
//            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);
//
//            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
//            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
//            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
//            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
//        }
//    }
//
//    @Test
//    public void test_ofRange_float() {
//        float testFloor = -1.0f;
//        float testCeiling = 1f;
//        float rangeFloor = -0.5f;
//        float rangeCeiling = 0.5f;
//        float unit = 0.5f;
//        RangeTester<Float> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);
//
//        ExaminerOfFloatRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
//        for (float i = testFloor; i <= testCeiling; i += unit) {
//            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
//            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
//            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
//            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;
//
//            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
//            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
//            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
//            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);
//
//            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
//            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
//            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
//            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
//        }
//    }
//
//    @Test
//    public void test_ofRange_double() {
//        double testFloor = -1.0d;
//        double testCeiling = 1d;
//        double rangeFloor = -0.5d;
//        double rangeCeiling = 0.5d;
//        double unit = 0.5d;
//        RangeTester<Double> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);
//
//        ExaminerOfDoubleRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
//        for (double i = testFloor; i <= testCeiling; i += unit) {
//            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
//            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
//            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
//            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;
//
//            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
//            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
//            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
//            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);
//
//            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
//            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
//            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
//            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
//        }
//    }
//
//    @Test
//    public void test_ofRange_char() {
//        char testFloor = 'a';
//        char testCeiling = 'e';
//        char rangeFloor = 'b';
//        char rangeCeiling = 'd';
//        char unit = 1;
//        RangeTester<Character> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);
//
//        ExaminerOfCharRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
//        for (char i = testFloor; i <= testCeiling; i += unit) {
//            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
//            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
//            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
//            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;
//
//            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
//            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
//            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
//            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);
//
//            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
//            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
//            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
//            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
//        }
//    }
//
//    @Test
//    public void test_ofRange_byte() {
//        byte testFloor = -2;
//        byte testCeiling = 2;
//        byte rangeFloor = -1;
//        byte rangeCeiling = 1;
//        byte unit = 1;
//        RangeTester<Byte> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);
//
//        ExaminerOfByteRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
//        for (byte i = testFloor; i <= testCeiling; i += unit) {
//            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
//            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
//            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
//            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;
//
//            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
//            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
//            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
//            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);
//
//            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
//            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
//            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
//            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
//        }
//    }
//
//    @Test
//    public void test_ofRange_Number() {
//        BigDecimal testFloor = new BigDecimal("-1");
//        BigDecimal testCeiling = new BigDecimal("1");
//        BigDecimal rangeFloor = new BigDecimal("-0.5");
//        BigDecimal rangeCeiling = new BigDecimal("0.5");
//        BigDecimal unit = new BigDecimal("0.5");
//        RangeTester<Number> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);
//
//        ExaminerOfNumberRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
//        for (BigDecimal i = testFloor; i.compareTo(testCeiling) <= 0; i = i.add(unit)) {
//            boolean isIncludeInclude = i.compareTo(rangeFloor) >= 0 && i.compareTo(rangeCeiling) <= 0;
//            boolean isIncludeExclude = i.compareTo(rangeFloor) >= 0 && i.compareTo(rangeCeiling) < 0;
//            boolean isExcludeInclude = i.compareTo(rangeFloor) > 0 && i.compareTo(rangeCeiling) <= 0;
//            boolean isExcludeExclude = i.compareTo(rangeFloor) > 0 && i.compareTo(rangeCeiling) < 0;
//
//            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
//            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
//            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
//            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);
//
//            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
//            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
//            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
//            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
//        }
//    }
//
//    // requireMoreInclude
//
//    @Test
//    public void test_requireMoreInclude_short() {
//        String term = "kerker";
//        short floor = -1;
//        short ceiling = 1;
//        short unit = 1;
//        short bound = 0;
//
//        for (short i = floor; i <= ceiling; i += unit) {
//            short arg = i;
//            if (arg >= bound) {
//                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreInclude_int() {
//        String term = "kerker";
//        int floor = -1;
//        int ceiling = 1;
//        int unit = 1;
//        int bound = 0;
//
//        for (int i = floor; i <= ceiling; i += unit) {
//            int arg = i;
//            if (arg >= bound) {
//                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreInclude_long() {
//        String term = "kerker";
//        long floor = -1;
//        long ceiling = 1;
//        long unit = 1;
//        long bound = 0;
//
//        for (long i = floor; i <= ceiling; i += unit) {
//            long arg = i;
//            if (arg >= bound) {
//                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreInclude_float() {
//        String term = "kerker";
//        float floor = -0.5f;
//        float ceiling = 0.5f;
//        float unit = 0.5f;
//        float bound = 0.0f;
//
//        for (float i = floor; i <= ceiling; i += unit) {
//            float arg = i;
//            if (arg >= bound) {
//                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreInclude_double() {
//        String term = "kerker";
//        double floor = -0.5f;
//        double ceiling = 0.5f;
//        double unit = 0.5f;
//        double bound = 0.0f;
//
//        for (double i = floor; i <= ceiling; i += unit) {
//            double arg = i;
//            if (arg >= bound) {
//                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreInclude_char() {
//        String term = "kerker";
//        char floor = 'a';
//        char ceiling = 'c';
//        char unit = 1;
//        char bound = 'b';
//
//        for (char i = floor; i <= ceiling; i += unit) {
//            char arg = i;
//            if (arg >= bound) {
//                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreInclude_byte() {
//        String term = "kerker";
//        byte floor = -1;
//        byte ceiling = 1;
//        byte unit = 1;
//        byte bound = 0;
//
//        for (byte i = floor; i <= ceiling; i += unit) {
//            byte arg = i;
//            if (arg >= bound) {
//                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreInclude_Number() {
//        String term = "kerker";
//        BigDecimal floor = new BigDecimal("-0.5");
//        BigDecimal ceiling = new BigDecimal("0.5");
//        BigDecimal unit = new BigDecimal("0.5");
//        BigDecimal bound = new BigDecimal("0.0");
//
//        for (BigDecimal i = floor; i.compareTo(ceiling) <= 0; i = i.add(unit)) {
//            BigDecimal arg = i;
//            if (arg.compareTo(bound) >= 0) {
//                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
//            }
//        }
//    }
//
//    // requireMoreExclude
//
//    @Test
//    public void test_requireMoreExclude_short() {
//        String term = "kerker";
//        short floor = -1;
//        short ceiling = 1;
//        short unit = 1;
//        short bound = 0;
//
//        for (short i = floor; i <= ceiling; i += unit) {
//            short arg = i;
//            if (arg > bound) {
//                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreExclude_int() {
//        String term = "kerker";
//        int floor = -1;
//        int ceiling = 1;
//        int unit = 1;
//        int bound = 0;
//
//        for (int i = floor; i <= ceiling; i += unit) {
//            int arg = i;
//            if (arg > bound) {
//                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreExclude_long() {
//        String term = "kerker";
//        long floor = -1;
//        long ceiling = 1;
//        long unit = 1;
//        long bound = 0;
//
//        for (long i = floor; i <= ceiling; i += unit) {
//            long arg = i;
//            if (arg > bound) {
//                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreExclude_float() {
//        String term = "kerker";
//        float floor = -0.5f;
//        float ceiling = 0.5f;
//        float unit = 0.5f;
//        float bound = 0.0f;
//
//        for (float i = floor; i <= ceiling; i += unit) {
//            float arg = i;
//            if (arg > bound) {
//                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreExclude_double() {
//        String term = "kerker";
//        double floor = -0.5f;
//        double ceiling = 0.5f;
//        double unit = 0.5f;
//        double bound = 0.0f;
//
//        for (double i = floor; i <= ceiling; i += unit) {
//            double arg = i;
//            if (arg > bound) {
//                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreExclude_char() {
//        String term = "kerker";
//        char floor = 'a';
//        char ceiling = 'c';
//        char unit = 1;
//        char bound = 'b';
//
//        for (char i = floor; i <= ceiling; i += unit) {
//            char arg = i;
//            if (arg > bound) {
//                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreExclude_byte() {
//        String term = "kerker";
//        byte floor = -1;
//        byte ceiling = 1;
//        byte unit = 1;
//        byte bound = 0;
//
//        for (byte i = floor; i <= ceiling; i += unit) {
//            byte arg = i;
//            if (arg > bound) {
//                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireMoreExclude_Number() {
//        String term = "kerker";
//        BigDecimal floor = new BigDecimal("-0.5");
//        BigDecimal ceiling = new BigDecimal("0.5");
//        BigDecimal unit = new BigDecimal("0.5");
//        BigDecimal bound = new BigDecimal("0.0");
//
//        for (BigDecimal i = floor; i.compareTo(ceiling) <= 0; i = i.add(unit)) {
//            BigDecimal arg = i;
//            if (arg.compareTo(bound) > 0) {
//                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
//            }
//        }
//    }
//
//    // requireLessInclude
//
//    @Test
//    public void test_requireLessInclude_short() {
//        String term = "kerker";
//        short floor = -1;
//        short ceiling = 1;
//        short unit = 1;
//        short bound = 0;
//
//        for (short i = floor; i <= ceiling; i += unit) {
//            short arg = i;
//            if (arg <= bound) {
//                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessInclude_int() {
//        String term = "kerker";
//        int floor = -1;
//        int ceiling = 1;
//        int unit = 1;
//        int bound = 0;
//
//        for (int i = floor; i <= ceiling; i += unit) {
//            int arg = i;
//            if (arg <= bound) {
//                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessInclude_long() {
//        String term = "kerker";
//        long floor = -1;
//        long ceiling = 1;
//        long unit = 1;
//        long bound = 0;
//
//        for (long i = floor; i <= ceiling; i += unit) {
//            long arg = i;
//            if (arg <= bound) {
//                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessInclude_float() {
//        String term = "kerker";
//        float floor = -0.5f;
//        float ceiling = 0.5f;
//        float unit = 0.5f;
//        float bound = 0.0f;
//
//        for (float i = floor; i <= ceiling; i += unit) {
//            float arg = i;
//            if (arg <= bound) {
//                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessInclude_double() {
//        String term = "kerker";
//        double floor = -0.5f;
//        double ceiling = 0.5f;
//        double unit = 0.5f;
//        double bound = 0.0f;
//
//        for (double i = floor; i <= ceiling; i += unit) {
//            double arg = i;
//            if (arg <= bound) {
//                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessInclude_char() {
//        String term = "kerker";
//        char floor = 'a';
//        char ceiling = 'c';
//        char unit = 1;
//        char bound = 'b';
//
//        for (char i = floor; i <= ceiling; i += unit) {
//            char arg = i;
//            if (arg <= bound) {
//                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessInclude_byte() {
//        String term = "kerker";
//        byte floor = -1;
//        byte ceiling = 1;
//        byte unit = 1;
//        byte bound = 0;
//
//        for (byte i = floor; i <= ceiling; i += unit) {
//            byte arg = i;
//            if (arg <= bound) {
//                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessInclude_Number() {
//        String term = "kerker";
//        BigDecimal floor = new BigDecimal("-0.5");
//        BigDecimal ceiling = new BigDecimal("0.5");
//        BigDecimal unit = new BigDecimal("0.5");
//        BigDecimal bound = new BigDecimal("0.0");
//
//        for (BigDecimal i = floor; i.compareTo(ceiling) <= 0; i = i.add(unit)) {
//            BigDecimal arg = i;
//            if (arg.compareTo(bound) <= 0) {
//                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
//            }
//        }
//    }
//
//    // requireLessExclude
//
//    @Test
//    public void test_requireLessExclude_short() {
//        String term = "kerker";
//        short floor = -1;
//        short ceiling = 1;
//        short unit = 1;
//        short bound = 0;
//
//        for (short i = floor; i <= ceiling; i += unit) {
//            short arg = i;
//            if (arg < bound) {
//                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessExclude_int() {
//        String term = "kerker";
//        int floor = -1;
//        int ceiling = 1;
//        int unit = 1;
//        int bound = 0;
//
//        for (int i = floor; i <= ceiling; i += unit) {
//            int arg = i;
//            if (arg < bound) {
//                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessExclude_long() {
//        String term = "kerker";
//        long floor = -1;
//        long ceiling = 1;
//        long unit = 1;
//        long bound = 0;
//
//        for (long i = floor; i <= ceiling; i += unit) {
//            long arg = i;
//            if (arg < bound) {
//                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessExclude_float() {
//        String term = "kerker";
//        float floor = -0.5f;
//        float ceiling = 0.5f;
//        float unit = 0.5f;
//        float bound = 0.0f;
//
//        for (float i = floor; i <= ceiling; i += unit) {
//            float arg = i;
//            if (arg < bound) {
//                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessExclude_double() {
//        String term = "kerker";
//        double floor = -0.5f;
//        double ceiling = 0.5f;
//        double unit = 0.5f;
//        double bound = 0.0f;
//
//        for (double i = floor; i <= ceiling; i += unit) {
//            double arg = i;
//            if (arg < bound) {
//                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessExclude_char() {
//        String term = "kerker";
//        char floor = 'a';
//        char ceiling = 'c';
//        char unit = 1;
//        char bound = 'b';
//
//        for (char i = floor; i <= ceiling; i += unit) {
//            char arg = i;
//            if (arg < bound) {
//                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessExclude_byte() {
//        String term = "kerker";
//        byte floor = -1;
//        byte ceiling = 1;
//        byte unit = 1;
//        byte bound = 0;
//
//        for (byte i = floor; i <= ceiling; i += unit) {
//            byte arg = i;
//            if (arg < bound) {
//                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @Test
//    public void test_requireLessExclude_Number() {
//        String term = "kerker";
//        BigDecimal floor = new BigDecimal("-0.5");
//        BigDecimal ceiling = new BigDecimal("0.5");
//        BigDecimal unit = new BigDecimal("0.5");
//        BigDecimal bound = new BigDecimal("0.0");
//
//        for (BigDecimal i = floor; i.compareTo(ceiling) <= 0; i = i.add(unit)) {
//            BigDecimal arg = i;
//            if (arg.compareTo(bound) < 0) {
//                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
//            } else {
//                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
//                        .isInstanceOf(MistyException.class)
//                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
//            }
//        }
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"-1", "0", "1", "1.1"})
//    public void requireNumber$normal(String value) {
//        Assertions.assertThat(Examiner.requireNumber(value)).isEqualTo(value);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"a", "1.1.1"})
//    public void requireNumber$error(String value) {
//        Assertions.assertThatThrownBy(() -> Examiner.requireNumber(value))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.requireNumber("value", value));
//    }
//
//    @ParameterizedTest
//    @NullAndEmptySource
//    public void requireNumber$error$NullAndEmpty(String value) {
//        Assertions.assertThatThrownBy(() -> Examiner.requireNumber(value))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));
//    }
//
//    @ParameterizedTest
//    @ValueSource(shorts = {Short.MIN_VALUE, 0, Short.MAX_VALUE})
//    public void requireShort$normal(short value) {
//        Assertions.assertThat(Examiner.requireShort(value + "")).isEqualTo(value);
//    }
//
//    @ParameterizedTest
//    @ValueSource(shorts = {Short.MIN_VALUE, Short.MAX_VALUE})
//    public void requireShort$Error(short value) {
//        String s = (value > 0 ? ((int) value + 1) : ((int) value - 1)) + "";
//        Assertions.assertThatThrownBy(() -> Examiner.requireShort(s))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.requireShort("value", s));
//    }
//
//    @ParameterizedTest
//    @NullAndEmptySource
//    public void requireShort$error$NullAndEmpty(String value) {
//        Assertions.assertThatThrownBy(() -> Examiner.requireShort(value))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE})
//    public void requireInteger$normal(int value) {
//        Assertions.assertThat(Examiner.requireInteger(value + "")).isEqualTo(value);
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE})
//    public void requireInteger$Error(int value) {
//        String s = (value > 0 ? ((long) value + 1) : ((long) value - 1)) + "";
//        Assertions.assertThatThrownBy(() -> Examiner.requireInteger(s))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.requireInteger("value", s));
//    }
//
//    @ParameterizedTest
//    @NullAndEmptySource
//    public void requireInteger$error$NullAndEmpty(String value) {
//        Assertions.assertThatThrownBy(() -> Examiner.requireInteger(value))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));
//    }
//
//    @ParameterizedTest
//    @ValueSource(longs = {Long.MIN_VALUE, Long.MAX_VALUE})
//    public void requireLong$normal(long value) {
//        Assertions.assertThat(Examiner.requireLong(value + "")).isEqualTo(value);
//    }
//
//    @ParameterizedTest
//    @ValueSource(longs = {Long.MIN_VALUE, Long.MAX_VALUE})
//    public void requireLong$Error(long value) {
//        BigDecimal one = new BigDecimal(1);
//        BigDecimal bigDecimal = value > 0 ? new BigDecimal(value).add(one) : new BigDecimal(value).subtract(one);
//        Assertions.assertThatThrownBy(() -> Examiner.requireLong(bigDecimal.toString()))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.requireLong("value", bigDecimal.toString()));
//    }
//
//    @ParameterizedTest
//    @NullAndEmptySource
//    public void requireLong$error$NullAndEmpty(String value) {
//        Assertions.assertThatThrownBy(() -> Examiner.requireLong(value))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));
//    }
//
//    @ParameterizedTest
//    @ValueSource(floats = {Float.MIN_VALUE, Float.MAX_VALUE})
//    public void requireFloat$normal(float value) {
//        Assertions.assertThat(Examiner.requireFloat(value + "")).isEqualTo(value);
//    }
//
//    @ParameterizedTest
//    @ValueSource(floats = {Float.MIN_VALUE, Float.MAX_VALUE})
//    public void requireFloat$Error(float value) {
//        // XXX 暫時沒有上下限檢查
//    }
//
//    @ParameterizedTest
//    @NullAndEmptySource
//    public void requireFloat$error$NullAndEmpty(String value) {
//        Assertions.assertThatThrownBy(() -> Examiner.requireFloat(value))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));
//    }
//
//    @ParameterizedTest
//    @ValueSource(doubles = {Double.MIN_VALUE, Double.MAX_VALUE})
//    public void requireDouble$normal(double value) {
//        Assertions.assertThat(Examiner.requireDouble(value + "")).isEqualTo(value);
//    }
//
//    @ParameterizedTest
//    @ValueSource(doubles = {Double.MIN_VALUE, Double.MAX_VALUE})
//    public void requireDouble$Error(double value) {
//        // XXX 暫時沒有上下限檢查
//    }
//
//    @ParameterizedTest
//    @NullAndEmptySource
//    public void requireDouble$error$NullAndEmpty(String value) {
//        Assertions.assertThatThrownBy(() -> Examiner.requireDouble(value))
//                .isInstanceOf(MistyException.class)
//                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));
//    }

}
