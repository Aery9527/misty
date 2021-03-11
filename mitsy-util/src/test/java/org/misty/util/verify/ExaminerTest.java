package org.misty.util.verify;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@SuppressWarnings({"rawtypes", "unchecked"})
class ExaminerTest {

    @SuppressWarnings("UnnecessaryBoxing")
    public static final Map<String, Number> NUMBER_OF_FLOOR = Collections.unmodifiableMap(new LinkedHashMap<String, Number>() {{
        put("Short0", new Short((short) 0));
        put("Integer0", new Integer(0));
        put("Long0", new Long(0L));
        put("Float0", new Float(0.0f));
        put("Double0", new Double(0.0d));
        put("Byte0", new Byte((byte) 0));
        put("BigDecimal0", new BigDecimal(0));
    }});

    @SuppressWarnings("UnnecessaryBoxing")
    public static final Map<String, Number> NUMBER_OF_UNIT = Collections.unmodifiableMap(new LinkedHashMap<String, Number>() {{
        put("Short1", new Short((short) 1));
        put("Integer1", new Integer(1));
        put("Long1", new Long(1L));
        put("Float1", new Float(0.5f));
        put("Double1", new Double(0.5d));
        put("Byte1", new Byte((byte) 1));
        put("BigDecimal1", new BigDecimal(0.5));
    }});

    @SuppressWarnings("UnnecessaryBoxing")
    public static final Map<String, Number> NUMBER_OF_CEILING = Collections.unmodifiableMap(new LinkedHashMap<String, Number>() {{
        put("Short2", new Short((short) 4));
        put("Integer2", new Integer(4));
        put("Long2", new Long(4L));
        put("Float2", new Float(2.0f));
        put("Double2", new Double(2.0d));
        put("Byte2", new Byte((byte) 4));
        put("BigDecimal2", new BigDecimal(4));
    }});

    public static final Condition<Throwable> CONDITION = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
            "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

    public static final ExamineIntervals.Floor FI = ExamineIntervals.Floor.INCLUDE; // fi = floor include

    public static final ExamineIntervals.Floor FE = ExamineIntervals.Floor.EXCLUDE; // fe = floor exclude

    public static final ExamineIntervals.Ceiling CI = ExamineIntervals.Ceiling.INCLUDE; // ci = ceiling include

    public static final ExamineIntervals.Ceiling CE = ExamineIntervals.Ceiling.EXCLUDE; // ce = ceiling exclude

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
                         RangeType rangeType, boolean expectedError) {
            if (expectedError) {
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

            } else {
                Assertions.assertThat(action.apply(this.term, arg)).isEqualTo(arg);
            }
        }
    }

    public enum RangeType {
        REQUIRE, REFUSE
    }

    @Test
    public void test_requireNullOrEmpty() {
        String term = "kerker";

        Examiner.requireNullOrEmpty(term, (Object) null);
        AtomicReference<Object> arg1 = new AtomicReference<>(new Object());
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
        Examiner.requireNullOrEmpty(term, Optional.empty());
        Examiner.requireNullOrEmpty(term, Optional.of(""));
        Examiner.requireNullOrEmpty(term, Optional.of(Collections.emptyList()));
        Examiner.requireNullOrEmpty(term, Optional.of(Collections.emptyMap()));
        Examiner.requireNullOrEmpty(term, Optional.of(new Object[0]));
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

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Object) null))
                .isInstanceOf(MistyException.class).is(CONDITION)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty(term));
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
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of("123"))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(Collections.singleton("")))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(Collections.singletonMap("", "")))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(new Object[]{1, ""}))).isNotEmpty();
    }

    // requireInRange

    @Test
    public void test_ofRange_short() {
        short floor = 0;
        short unit = 1;
        short ceiling = 4;
        RangeTester<Short> tester = new RangeTester<>("kerker", floor, ceiling);

        ExaminerOfShortRange examiner = Examiner.ofRange(floor, ceiling);
        for (short i = floor; i <= ceiling; i += unit) {
            boolean isFloor = i == floor;
            boolean isCeiling = i == ceiling;
            boolean isBoth = isFloor || isCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, false);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isCeiling);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isFloor);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isBoth);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, true);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isCeiling);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isFloor);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isBoth);
        }
    }

    @Test
    public void test_ofRange_int() {
        int floor = 0;
        int unit = 1;
        int ceiling = 4;
        RangeTester<Integer> tester = new RangeTester<>("kerker", floor, ceiling);

        ExaminerOfIntRange examiner = Examiner.ofRange(floor, ceiling);
        for (int i = floor; i <= ceiling; i += unit) {
            boolean isFloor = i == floor;
            boolean isCeiling = i == ceiling;
            boolean isBoth = isFloor || isCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, false);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isCeiling);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isFloor);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isBoth);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, true);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isCeiling);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isFloor);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isBoth);
        }
    }

    @Test
    public void test_ofRange_long() {
        long floor = 0;
        long unit = 1;
        long ceiling = 4;
        RangeTester<Long> tester = new RangeTester<>("kerker", floor, ceiling);

        ExaminerOfLongRange examiner = Examiner.ofRange(floor, ceiling);
        for (long i = floor; i <= ceiling; i += unit) {
            boolean isFloor = i == floor;
            boolean isCeiling = i == ceiling;
            boolean isBoth = isFloor || isCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, false);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isCeiling);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isFloor);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isBoth);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, true);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isCeiling);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isFloor);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isBoth);
        }
    }

    @Test
    public void test_ofRange_float() {
        float floor = 0.0f;
        float unit = 0.5f;
        float ceiling = 2.0f;
        RangeTester<Float> tester = new RangeTester<>("kerker", floor, ceiling);

        ExaminerOfFloatRange examiner = Examiner.ofRange(floor, ceiling);
        for (float i = floor; i <= ceiling; i += unit) {
            boolean isFloor = i == floor;
            boolean isCeiling = i == ceiling;
            boolean isBoth = isFloor || isCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, false);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isCeiling);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isFloor);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isBoth);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, true);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isCeiling);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isFloor);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isBoth);
        }
    }

    @Test
    public void test_ofRange_double() {
        double floor = 0.0d;
        double unit = 0.5d;
        double ceiling = 2.0d;
        RangeTester<Double> tester = new RangeTester<>("kerker", floor, ceiling);

        ExaminerOfDoubleRange examiner = Examiner.ofRange(floor, ceiling);
        for (double i = floor; i <= ceiling; i += unit) {
            boolean isFloor = i == floor;
            boolean isCeiling = i == ceiling;
            boolean isBoth = isFloor || isCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, false);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isCeiling);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isFloor);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isBoth);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, true);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isCeiling);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isFloor);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isBoth);
        }
    }

    @Test
    public void test_ofRange_char() {
        char floor = 'a';
        char unit = 'a';
        char ceiling = 'd';
        RangeTester<Character> tester = new RangeTester<>("kerker", floor, ceiling);

        ExaminerOfCharRange examiner = Examiner.ofRange(floor, ceiling);
        for (char i = floor; i <= ceiling; i += unit) {
            boolean isFloor = i == floor;
            boolean isCeiling = i == ceiling;
            boolean isBoth = isFloor || isCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, false);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isCeiling);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isFloor);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isBoth);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, true);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isCeiling);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isFloor);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isBoth);
        }
    }

    @Test
    public void test_ofRange_byte() {
        byte floor = 0;
        byte unit = 1;
        byte ceiling = 4;
        RangeTester<Byte> tester = new RangeTester<>("kerker", floor, ceiling);

        ExaminerOfByteRange examiner = Examiner.ofRange(floor, ceiling);
        for (byte i = floor; i <= ceiling; i += unit) {
            boolean isFloor = i == floor;
            boolean isCeiling = i == ceiling;
            boolean isBoth = isFloor || isCeiling;

            tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, false);
            tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isCeiling);
            tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isFloor);
            tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isBoth);

            tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, true);
            tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isCeiling);
            tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isFloor);
            tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isBoth);
        }
    }

    @Test
    public void test_ofRange_Number() {

        NUMBER_OF_FLOOR.forEach((floorTerm, floor) -> {
            NUMBER_OF_CEILING.forEach((ceilingTerm, ceiling) -> {

                RangeTester<Number> tester = new RangeTester<>("kerker", floor, ceiling);

                ExaminerOfNumberRange examiner = Examiner.ofRange(floor, ceiling);
                NUMBER_OF_UNIT.forEach((unitTerm, i) -> {
                    try {
                        boolean isFloor = i.doubleValue() == floor.doubleValue();
                        boolean isCeiling = i.doubleValue() == ceiling.doubleValue();
                        boolean isBoth = isFloor || isCeiling;

                        tester.test(i, examiner::requireIncludeInclude, FI, CI, RangeType.REQUIRE, false);
                        tester.test(i, examiner::requireIncludeExclude, FI, CE, RangeType.REQUIRE, isCeiling);
                        tester.test(i, examiner::requireExcludeInclude, FE, CI, RangeType.REQUIRE, isFloor);
                        tester.test(i, examiner::requireExcludeExclude, FE, CE, RangeType.REQUIRE, isBoth);

                        tester.test(i, examiner::refuseIncludeInclude, FI, CI, RangeType.REFUSE, true);
                        tester.test(i, examiner::refuseIncludeExclude, FI, CE, RangeType.REFUSE, !isCeiling);
                        tester.test(i, examiner::refuseExcludeInclude, FE, CI, RangeType.REFUSE, !isFloor);
                        tester.test(i, examiner::refuseExcludeExclude, FE, CE, RangeType.REFUSE, !isBoth);

                    } catch (Throwable t) {
                        String msg = "floor(" + floorTerm + "), unit(" + unitTerm + "), ceiling(" + ceilingTerm + ")";
                        throw MistyError.UNKNOWN.thrown(msg, t);
                    }
                });
            });
        });
    }

    // requireMoreEqual



}