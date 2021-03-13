package org.misty.util.verify;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiConsumerThrow1;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@SuppressWarnings({"rawtypes", "unchecked"})
class ExaminerTest {

    public static final Condition<Throwable> CONDITION = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
            "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

    public static final ExamineIntervals.Floor FI = ExamineIntervals.Floor.INCLUDE; // fi = floor include

    public static final ExamineIntervals.Floor FE = ExamineIntervals.Floor.EXCLUDE; // fe = floor exclude

    public static final ExamineIntervals.Ceiling CI = ExamineIntervals.Ceiling.INCLUDE; // ci = ceiling include

    public static final ExamineIntervals.Ceiling CE = ExamineIntervals.Ceiling.EXCLUDE; // ce = ceiling exclude

    public static final FiConsumerThrow1<Object, TestException> THROWN_ACTION = (arg) -> {
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

    @Test
    public void test_requireNullOrEmpty() {
        String term = "kerker";

        Examiner.requireNullOrEmpty((Object) null, THROWN_ACTION);
        Examiner.requireNullOrEmpty(term, (Object) null);
        AtomicReference<Object> arg1 = new AtomicReference<>(new Object());
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(arg1.get(), THROWN_ACTION)).isInstanceOf(TestException.class);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg1.get()))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, arg1.get().toString()));

        Examiner.requireNullOrEmpty(term, (String) null);
        Examiner.requireNullOrEmpty(term, "");
        AtomicReference<String> arg2 = new AtomicReference<>("9527");
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg2.get()))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, arg2.get()));

        Examiner.requireNullOrEmpty(term, (Collection) null);
        Examiner.requireNullOrEmpty(term, Collections.emptyList());
        AtomicReference<Collection> arg3 = new AtomicReference<>(Collections.singletonList(""));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg3.get()))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, arg3.get().toString()));

        Examiner.requireNullOrEmpty(term, (Map) null);
        Examiner.requireNullOrEmpty(term, Collections.emptyMap());
        AtomicReference<Map> arg4 = new AtomicReference<>(Collections.singletonMap("", ""));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg4.get()))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, arg4.get().toString()));

        Examiner.requireNullOrEmpty(term, (String[]) null);
        Examiner.requireNullOrEmpty(term, new String[]{});
        AtomicReference<String[]> arg5 = new AtomicReference<>(new String[]{""});
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg5.get()))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, Arrays.toString(arg5.get())));

        Examiner.requireNullOrEmpty(term, (Optional) null);
        Examiner.requireNullOrEmpty((Optional) null, THROWN_ACTION::acceptOrHandle);
        Examiner.requireNullOrEmpty(term, Optional.empty());
        Examiner.requireNullOrEmpty(term, Optional.of(""));
        Examiner.requireNullOrEmpty(term, Optional.of(Collections.emptyList()));
        Examiner.requireNullOrEmpty(term, Optional.of(Collections.emptyMap()));
        Examiner.requireNullOrEmpty(term, Optional.of(new Object[0]));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(Optional.of(new Object()), THROWN_ACTION::acceptOrHandle)).isInstanceOf(TestException.class);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(arg2.get())))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, arg2.get()));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(arg3.get())))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, arg3.get().toString()));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(arg4.get())))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, arg4.get().toString()));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(arg5.get())))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.requireNullOrEmpty(term, Arrays.toString(arg5.get())));
    }

    @Test
    public void test_refuseNullAndEmpty() {
        String term = "kerker";
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty((Object) null, THROWN_ACTION)).isInstanceOf(TestException.class);
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Object) null))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(new Object(), THROWN_ACTION));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, new Object()));

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (String) null))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, ""))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, "9527")).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Collection) null))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Collections.emptyList()))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Collections.singletonList(""))).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Map) null))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Collections.emptyMap()))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Collections.singletonMap("", ""))).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (String[]) null))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, new String[]{}))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, new String[]{""})).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty((Optional) null, THROWN_ACTION::acceptOrHandle)).isInstanceOf(TestException.class);
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Optional) null))
                .isInstanceOf(MistyException.class).is(CONDITION);
//                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term)); // XXX 不知道為什麼這邊操作會錯?
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.empty()))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of("")))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(Collections.emptyList())))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(Collections.emptyMap())))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(new Object[0])))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(Optional.of(new Object()), THROWN_ACTION::acceptOrHandle));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of("123"))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(Collections.singleton("")))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(Collections.singletonMap("", "")))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(new Object[]{1, ""}))).isNotEmpty();
    }

    // requireInRange

    @Test
    public void test_ofRange_short() {
        short testFloor = -2;
        short testCeiling = 2;
        short rangeFloor = -1;
        short rangeCeiling = 1;
        short unit = 1;
        RangeTester<Short> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);

        ExaminerOfShortRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
        for (short i = testFloor; i <= testCeiling; i += unit) {
            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
        }
    }

    @Test
    public void test_ofRange_int() {
        int testFloor = -2;
        int testCeiling = 2;
        int rangeFloor = -1;
        int rangeCeiling = 1;
        int unit = 1;
        RangeTester<Integer> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);

        ExaminerOfIntRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
        for (int i = testFloor; i <= testCeiling; i += unit) {
            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
        }
    }

    @Test
    public void test_ofRange_long() {
        long testFloor = -2;
        long testCeiling = 2;
        long rangeFloor = -1;
        long rangeCeiling = 1;
        long unit = 1;
        RangeTester<Long> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);

        ExaminerOfLongRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
        for (long i = testFloor; i <= testCeiling; i += unit) {
            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
        }
    }

    @Test
    public void test_ofRange_float() {
        float testFloor = -1.0f;
        float testCeiling = 1f;
        float rangeFloor = -0.5f;
        float rangeCeiling = 0.5f;
        float unit = 0.5f;
        RangeTester<Float> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);

        ExaminerOfFloatRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
        for (float i = testFloor; i <= testCeiling; i += unit) {
            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
        }
    }

    @Test
    public void test_ofRange_double() {
        double testFloor = -1.0d;
        double testCeiling = 1d;
        double rangeFloor = -0.5d;
        double rangeCeiling = 0.5d;
        double unit = 0.5d;
        RangeTester<Double> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);

        ExaminerOfDoubleRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
        for (double i = testFloor; i <= testCeiling; i += unit) {
            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
        }
    }

    @Test
    public void test_ofRange_char() {
        char testFloor = 'a';
        char testCeiling = 'e';
        char rangeFloor = 'b';
        char rangeCeiling = 'd';
        char unit = 1;
        RangeTester<Character> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);

        ExaminerOfCharRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
        for (char i = testFloor; i <= testCeiling; i += unit) {
            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
        }
    }

    @Test
    public void test_ofRange_byte() {
        byte testFloor = -2;
        byte testCeiling = 2;
        byte rangeFloor = -1;
        byte rangeCeiling = 1;
        byte unit = 1;
        RangeTester<Byte> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);

        ExaminerOfByteRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
        for (byte i = testFloor; i <= testCeiling; i += unit) {
            boolean isIncludeInclude = i >= rangeFloor && i <= rangeCeiling;
            boolean isIncludeExclude = i >= rangeFloor && i < rangeCeiling;
            boolean isExcludeInclude = i > rangeFloor && i <= rangeCeiling;
            boolean isExcludeExclude = i > rangeFloor && i < rangeCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
        }
    }

    @Test
    public void test_ofRange_Number() {
        BigDecimal testFloor = new BigDecimal("-1");
        BigDecimal testCeiling = new BigDecimal("1");
        BigDecimal rangeFloor = new BigDecimal("-0.5");
        BigDecimal rangeCeiling = new BigDecimal("0.5");
        BigDecimal unit = new BigDecimal("0.5");
        RangeTester<Number> tester = new RangeTester<>("kerker", rangeFloor, rangeCeiling);

        ExaminerOfNumberRange examiner = Examiner.ofRange(rangeFloor, rangeCeiling);
        for (BigDecimal i = testFloor; i.compareTo(testCeiling) <= 0; i = i.add(unit)) {
            boolean isIncludeInclude = i.compareTo(rangeFloor) >= 0 && i.compareTo(rangeCeiling) <= 0;
            boolean isIncludeExclude = i.compareTo(rangeFloor) >= 0 && i.compareTo(rangeCeiling) < 0;
            boolean isExcludeInclude = i.compareTo(rangeFloor) > 0 && i.compareTo(rangeCeiling) <= 0;
            boolean isExcludeExclude = i.compareTo(rangeFloor) > 0 && i.compareTo(rangeCeiling) < 0;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, isIncludeInclude);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isIncludeExclude);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isExcludeInclude);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isExcludeExclude);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, !isIncludeInclude);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isIncludeExclude);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isExcludeInclude);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isExcludeExclude);
        }
    }

    // requireMoreInclude

    @Test
    public void test_requireMoreInclude_short() {
        String term = "kerker";
        short floor = -1;
        short ceiling = 1;
        short unit = 1;
        short bound = 0;

        for (short i = floor; i <= ceiling; i += unit) {
            short arg = i;
            if (arg >= bound) {
                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreInclude_int() {
        String term = "kerker";
        int floor = -1;
        int ceiling = 1;
        int unit = 1;
        int bound = 0;

        for (int i = floor; i <= ceiling; i += unit) {
            int arg = i;
            if (arg >= bound) {
                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreInclude_long() {
        String term = "kerker";
        long floor = -1;
        long ceiling = 1;
        long unit = 1;
        long bound = 0;

        for (long i = floor; i <= ceiling; i += unit) {
            long arg = i;
            if (arg >= bound) {
                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreInclude_float() {
        String term = "kerker";
        float floor = -0.5f;
        float ceiling = 0.5f;
        float unit = 0.5f;
        float bound = 0.0f;

        for (float i = floor; i <= ceiling; i += unit) {
            float arg = i;
            if (arg >= bound) {
                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreInclude_double() {
        String term = "kerker";
        double floor = -0.5f;
        double ceiling = 0.5f;
        double unit = 0.5f;
        double bound = 0.0f;

        for (double i = floor; i <= ceiling; i += unit) {
            double arg = i;
            if (arg >= bound) {
                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreInclude_char() {
        String term = "kerker";
        char floor = 'a';
        char ceiling = 'c';
        char unit = 1;
        char bound = 'b';

        for (char i = floor; i <= ceiling; i += unit) {
            char arg = i;
            if (arg >= bound) {
                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreInclude_byte() {
        String term = "kerker";
        byte floor = -1;
        byte ceiling = 1;
        byte unit = 1;
        byte bound = 0;

        for (byte i = floor; i <= ceiling; i += unit) {
            byte arg = i;
            if (arg >= bound) {
                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreInclude_Number() {
        String term = "kerker";
        BigDecimal floor = new BigDecimal("-0.5");
        BigDecimal ceiling = new BigDecimal("0.5");
        BigDecimal unit = new BigDecimal("0.5");
        BigDecimal bound = new BigDecimal("0.0");

        for (BigDecimal i = floor; i.compareTo(ceiling) <= 0; i = i.add(unit)) {
            BigDecimal arg = i;
            if (arg.compareTo(bound) >= 0) {
                Assertions.assertThat(Examiner.requireMoreInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreInclude(term, arg, bound));
            }
        }
    }

    // requireMoreExclude

    @Test
    public void test_requireMoreExclude_short() {
        String term = "kerker";
        short floor = -1;
        short ceiling = 1;
        short unit = 1;
        short bound = 0;

        for (short i = floor; i <= ceiling; i += unit) {
            short arg = i;
            if (arg > bound) {
                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreExclude_int() {
        String term = "kerker";
        int floor = -1;
        int ceiling = 1;
        int unit = 1;
        int bound = 0;

        for (int i = floor; i <= ceiling; i += unit) {
            int arg = i;
            if (arg > bound) {
                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreExclude_long() {
        String term = "kerker";
        long floor = -1;
        long ceiling = 1;
        long unit = 1;
        long bound = 0;

        for (long i = floor; i <= ceiling; i += unit) {
            long arg = i;
            if (arg > bound) {
                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreExclude_float() {
        String term = "kerker";
        float floor = -0.5f;
        float ceiling = 0.5f;
        float unit = 0.5f;
        float bound = 0.0f;

        for (float i = floor; i <= ceiling; i += unit) {
            float arg = i;
            if (arg > bound) {
                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreExclude_double() {
        String term = "kerker";
        double floor = -0.5f;
        double ceiling = 0.5f;
        double unit = 0.5f;
        double bound = 0.0f;

        for (double i = floor; i <= ceiling; i += unit) {
            double arg = i;
            if (arg > bound) {
                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreExclude_char() {
        String term = "kerker";
        char floor = 'a';
        char ceiling = 'c';
        char unit = 1;
        char bound = 'b';

        for (char i = floor; i <= ceiling; i += unit) {
            char arg = i;
            if (arg > bound) {
                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreExclude_byte() {
        String term = "kerker";
        byte floor = -1;
        byte ceiling = 1;
        byte unit = 1;
        byte bound = 0;

        for (byte i = floor; i <= ceiling; i += unit) {
            byte arg = i;
            if (arg > bound) {
                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireMoreExclude_Number() {
        String term = "kerker";
        BigDecimal floor = new BigDecimal("-0.5");
        BigDecimal ceiling = new BigDecimal("0.5");
        BigDecimal unit = new BigDecimal("0.5");
        BigDecimal bound = new BigDecimal("0.0");

        for (BigDecimal i = floor; i.compareTo(ceiling) <= 0; i = i.add(unit)) {
            BigDecimal arg = i;
            if (arg.compareTo(bound) > 0) {
                Assertions.assertThat(Examiner.requireMoreExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireMoreExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireMoreExclude(term, arg, bound));
            }
        }
    }

    // requireLessInclude

    @Test
    public void test_requireLessInclude_short() {
        String term = "kerker";
        short floor = -1;
        short ceiling = 1;
        short unit = 1;
        short bound = 0;

        for (short i = floor; i <= ceiling; i += unit) {
            short arg = i;
            if (arg <= bound) {
                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessInclude_int() {
        String term = "kerker";
        int floor = -1;
        int ceiling = 1;
        int unit = 1;
        int bound = 0;

        for (int i = floor; i <= ceiling; i += unit) {
            int arg = i;
            if (arg <= bound) {
                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessInclude_long() {
        String term = "kerker";
        long floor = -1;
        long ceiling = 1;
        long unit = 1;
        long bound = 0;

        for (long i = floor; i <= ceiling; i += unit) {
            long arg = i;
            if (arg <= bound) {
                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessInclude_float() {
        String term = "kerker";
        float floor = -0.5f;
        float ceiling = 0.5f;
        float unit = 0.5f;
        float bound = 0.0f;

        for (float i = floor; i <= ceiling; i += unit) {
            float arg = i;
            if (arg <= bound) {
                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessInclude_double() {
        String term = "kerker";
        double floor = -0.5f;
        double ceiling = 0.5f;
        double unit = 0.5f;
        double bound = 0.0f;

        for (double i = floor; i <= ceiling; i += unit) {
            double arg = i;
            if (arg <= bound) {
                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessInclude_char() {
        String term = "kerker";
        char floor = 'a';
        char ceiling = 'c';
        char unit = 1;
        char bound = 'b';

        for (char i = floor; i <= ceiling; i += unit) {
            char arg = i;
            if (arg <= bound) {
                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessInclude_byte() {
        String term = "kerker";
        byte floor = -1;
        byte ceiling = 1;
        byte unit = 1;
        byte bound = 0;

        for (byte i = floor; i <= ceiling; i += unit) {
            byte arg = i;
            if (arg <= bound) {
                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessInclude_Number() {
        String term = "kerker";
        BigDecimal floor = new BigDecimal("-0.5");
        BigDecimal ceiling = new BigDecimal("0.5");
        BigDecimal unit = new BigDecimal("0.5");
        BigDecimal bound = new BigDecimal("0.0");

        for (BigDecimal i = floor; i.compareTo(ceiling) <= 0; i = i.add(unit)) {
            BigDecimal arg = i;
            if (arg.compareTo(bound) <= 0) {
                Assertions.assertThat(Examiner.requireLessInclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessInclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessInclude(term, arg, bound));
            }
        }
    }

    // requireLessExclude

    @Test
    public void test_requireLessExclude_short() {
        String term = "kerker";
        short floor = -1;
        short ceiling = 1;
        short unit = 1;
        short bound = 0;

        for (short i = floor; i <= ceiling; i += unit) {
            short arg = i;
            if (arg < bound) {
                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessExclude_int() {
        String term = "kerker";
        int floor = -1;
        int ceiling = 1;
        int unit = 1;
        int bound = 0;

        for (int i = floor; i <= ceiling; i += unit) {
            int arg = i;
            if (arg < bound) {
                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessExclude_long() {
        String term = "kerker";
        long floor = -1;
        long ceiling = 1;
        long unit = 1;
        long bound = 0;

        for (long i = floor; i <= ceiling; i += unit) {
            long arg = i;
            if (arg < bound) {
                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessExclude_float() {
        String term = "kerker";
        float floor = -0.5f;
        float ceiling = 0.5f;
        float unit = 0.5f;
        float bound = 0.0f;

        for (float i = floor; i <= ceiling; i += unit) {
            float arg = i;
            if (arg < bound) {
                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessExclude_double() {
        String term = "kerker";
        double floor = -0.5f;
        double ceiling = 0.5f;
        double unit = 0.5f;
        double bound = 0.0f;

        for (double i = floor; i <= ceiling; i += unit) {
            double arg = i;
            if (arg < bound) {
                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessExclude_char() {
        String term = "kerker";
        char floor = 'a';
        char ceiling = 'c';
        char unit = 1;
        char bound = 'b';

        for (char i = floor; i <= ceiling; i += unit) {
            char arg = i;
            if (arg < bound) {
                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessExclude_byte() {
        String term = "kerker";
        byte floor = -1;
        byte ceiling = 1;
        byte unit = 1;
        byte bound = 0;

        for (byte i = floor; i <= ceiling; i += unit) {
            byte arg = i;
            if (arg < bound) {
                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
            }
        }
    }

    @Test
    public void test_requireLessExclude_Number() {
        String term = "kerker";
        BigDecimal floor = new BigDecimal("-0.5");
        BigDecimal ceiling = new BigDecimal("0.5");
        BigDecimal unit = new BigDecimal("0.5");
        BigDecimal bound = new BigDecimal("0.0");

        for (BigDecimal i = floor; i.compareTo(ceiling) <= 0; i = i.add(unit)) {
            BigDecimal arg = i;
            if (arg.compareTo(bound) < 0) {
                Assertions.assertThat(Examiner.requireLessExclude(term, arg, bound)).isEqualTo(arg);
            } else {
                Assertions.assertThatThrownBy(() -> Examiner.requireLessExclude(term, arg, bound))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ExaminerMessage.requireLessExclude(term, arg, bound));
            }
        }
    }

}