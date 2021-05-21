package org.misty.util.reflect.method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.misty.util.fi.FiBiConsumer;
import org.misty.util.fi.FiConsumer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("OptionalGetWithoutIsPresent")
class MethodExtractorTest {

    public static final String PARENT_RETURN_A = "1";
    public static final BigDecimal PARENT_RETURN_B = new BigDecimal("2");

    public static final String TARGET_RETURN_STATIC_A = "STATIC_A";
    public static final String TARGET_RETURN_STATIC_A0 = "STATIC_A0";
    public static final String TARGET_RETURN_INSTANCE_A0 = "INSTANCE_A0";

    public static class TestParent {
        protected String A() {
            return PARENT_RETURN_A;
        }

        private BigDecimal B() {
            return PARENT_RETURN_B;
        }

        private void C(Runnable r) {
            r.run();
        }
    }

    public static class TestTarget extends TestParent {
        public static Function<String, String> static_a_1;
        public static BiFunction<String, Integer, String> static_a_2;
        public static Function3<String, Integer, Float, String> static_a_3;
        public Function<String, String> instance_a_1;
        public BiFunction<String, Integer, String> instance_a_2;
        public Function3<String, Integer, Float, String> instance_a_3;

        public static Runnable static_b_0;
        public static Consumer<String> static_b_1;
        public static BiConsumer<String, Integer> static_b_2;
        public static Consumer3<String, Integer, Float> static_b_3;
        public Runnable instance_b_0;
        public Consumer<String> instance_b_1;
        public BiConsumer<String, Integer> instance_b_2;
        public Consumer3<String, Integer, Float> instance_b_3;

        @Override
        public String A() {
            return TARGET_RETURN_STATIC_A;
        }

        private void thrown1() throws IOException {
            throw new IOException();
        }

        private String thrown2() throws InterruptedException {
            throw new InterruptedException();
        }

        private static String static_a() {
            return TARGET_RETURN_STATIC_A0;
        }

        private static String static_a(String s) {
            return static_a_1.apply(s);
        }

        private static String static_a(String s, int i) {
            return static_a_2.apply(s, i);
        }

        private static String static_a(String s, int i, float l) {
            return static_a_3.apply(s, i, l);
        }

        private String instance_a() {
            return TARGET_RETURN_INSTANCE_A0;
        }

        private String instance_a(String s) {
            return instance_a_1.apply(s);
        }

        private String instance_a(String s, int i) {
            return instance_a_2.apply(s, i);
        }

        private String instance_a(String s, int i, float l) {
            return instance_a_3.apply(s, i, l);
        }

        private static void static_b() {
            static_b_0.run();
        }

        private static void static_b(String s) {
            static_b_1.accept(s);
        }

        private static void static_b(String s, int i) {
            static_b_2.accept(s, i);
        }

        private static void static_b(String s, int i, float f) {
            static_b_3.accept(s, i, f);
        }

        private void instance_b() {
            instance_b_0.run();
        }

        private void instance_b(String s) {
            instance_b_1.accept(s);
        }

        private void instance_b(String s, int i) {
            instance_b_2.accept(s, i);
        }

        private void instance_b(String s, int i, float f) {
            instance_b_3.accept(s, i, f);
        }

    }

    private interface Function3<T1, T2, T3, R> {
        R apply(T1 t1, T2 t2, T3 t3);
    }

    private interface Consumer3<T1, T2, T3> {
        void accept(T1 t1, T2 t2, T3 t3);
    }

    private static final String PARENT_INSTANCE_STRING = "A";
    private static final String PARENT_INSTANCE_BIGDECIMAL = "B";
    private static final String PARENT_INSTANCE_VOID = "C";

    private static final String TARGET_INSTANCE_THROWN1 = "thrown1";
    private static final String TARGET_INSTANCE_THROWN2 = "thrown2";

    private static final String TARGET_STATIC_STRING = "static_a";
    private static final String TARGET_INSTANCE_STRING = "instance_a";

    private static final String TARGET_STATIC_VOID = "static_b";
    private static final String TARGET_INSTANCE_VOID = "instance_b";

    @BeforeEach
    void setUp() {
        TestTarget.static_a_1 = null;
        TestTarget.static_a_2 = null;
        TestTarget.static_a_3 = null;

        TestTarget.static_b_0 = null;
        TestTarget.static_b_1 = null;
        TestTarget.static_b_2 = null;
        TestTarget.static_b_3 = null;
    }

    @Test
    void overrideMethod() {
        FiConsumer<MethodExtractor> test = (extractor) -> {
            MethodArg0ReturnInvoker<String> invoker = extractor.buildObjectInvoker(PARENT_INSTANCE_STRING, String.class);
            String result = invoker.invoke();
            Assertions.assertThat(result).isEqualTo(TARGET_RETURN_STATIC_A);
        };

        test.acceptOrHandle(new MethodExtractor(new TestTarget()));
        test.acceptOrHandle(new MethodExtractor(TestParent.class, new TestTarget()));
    }

    @Test
    void thrown() throws NoSuchMethodException {
        MethodExtractor extractor = new MethodExtractor(new TestTarget());

        MethodArg0VoidInvoker thrown1Invoker = extractor.buildVoidInvoker(TARGET_INSTANCE_THROWN1);
        Assertions.assertThatThrownBy(thrown1Invoker::invoke).isInstanceOf(IOException.class);

        MethodArg0ReturnInvoker<String> thrown2Invoker = extractor.buildObjectInvoker(TARGET_INSTANCE_THROWN2, String.class);
        Assertions.assertThatThrownBy(thrown2Invoker::invoke).isInstanceOf(InterruptedException.class);
    }

    @Test
    void assignable() throws NoSuchMethodException {
        MethodExtractor extractor = new MethodExtractor(new TestParent());

        MethodArg0ReturnInvoker<BigDecimal> invoker1 = extractor.buildObjectInvoker(PARENT_INSTANCE_BIGDECIMAL, BigDecimal.class);
        Assertions.assertThat(invoker1.invoke()).isEqualTo(PARENT_RETURN_B);

        MethodArg0ReturnInvoker<Number> invoker2 = extractor.buildObjectInvoker(PARENT_INSTANCE_BIGDECIMAL, Number.class);
        Assertions.assertThat(invoker2.invoke()).isEqualTo(PARENT_RETURN_B);
    }

    @Test
    void return$normal() {
        FiBiConsumer<MethodExtractor, MethodStyle> staticTest = (extractor, style) -> {
            MethodArg0ReturnInvoker<String> invoker0 = extractor.buildObjectInvoker(TARGET_STATIC_STRING, String.class);
            Assertions.assertThat(invoker0.invoke()).isEqualTo(TARGET_RETURN_STATIC_A0);
            Assertions.assertThat(invoker0.getMethodStyle()).isEqualTo(style);

            AtomicReference<String> checkPoint1 = new AtomicReference<>();
            AtomicReference<Integer> checkPoint2 = new AtomicReference<>();
            AtomicReference<Float> checkPoint3 = new AtomicReference<>();

            MethodArg1ReturnInvoker<String, String> invoker1 = extractor.buildObjectInvoker(
                    TARGET_STATIC_STRING, String.class, String.class);
            TestTarget.static_a_1 = (s) -> {
                checkPoint1.set(s);
                return "A1";
            };
            Assertions.assertThat(invoker1.invoke("A")).isEqualTo("A1");
            Assertions.assertThat(invoker1.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_STATIC_STRING, String.class));
            Assertions.assertThat(invoker1.getTarget()).isEmpty();
            Assertions.assertThat(invoker1.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("A");

            checkPoint1.set(null);
            MethodArg2ReturnInvoker<String, String, Integer> invoker2 = extractor.buildObjectInvoker(
                    TARGET_STATIC_STRING, String.class, String.class, int.class);
            TestTarget.static_a_2 = (s, i) -> {
                checkPoint1.set(s);
                checkPoint2.set(i);
                return "A2";
            };
            Assertions.assertThat(invoker2.invoke("B", 9527)).isEqualTo("A2");
            Assertions.assertThat(invoker2.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_STATIC_STRING, String.class, int.class));
            Assertions.assertThat(invoker2.getTarget()).isEmpty();
            Assertions.assertThat(invoker2.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("B");
            Assertions.assertThat(checkPoint2.get()).isEqualTo(9527);

            checkPoint1.set(null);
            checkPoint2.set(null);
            MethodObjectInvoker<String> invoker3 = extractor.buildObjectInvoker(
                    TARGET_STATIC_STRING, String.class, String.class, int.class, float.class);
            TestTarget.static_a_3 = (s, i, f) -> {
                checkPoint1.set(s);
                checkPoint2.set(i);
                checkPoint3.set(f);
                return "A3";
            };
            Assertions.assertThat(invoker3.invoke("C", 9527, 55.66f)).isEqualTo("A3");
            Assertions.assertThat(invoker3.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_STATIC_STRING, String.class, int.class, float.class));
            Assertions.assertThat(invoker3.getTarget()).isEmpty();
            Assertions.assertThat(invoker3.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("C");
            Assertions.assertThat(checkPoint2.get()).isEqualTo(9527);
            Assertions.assertThat(checkPoint3.get()).isEqualTo(55.66f);
        };

        FiBiConsumer<MethodExtractor, MethodStyle> instanceTest = (extractor, style) -> {
            MethodArg0ReturnInvoker<String> invoker0 = extractor.buildObjectInvoker(TARGET_INSTANCE_STRING, String.class);
            Assertions.assertThat(invoker0.invoke()).isEqualTo(TARGET_RETURN_INSTANCE_A0);
            Assertions.assertThat(invoker0.getMethodStyle()).isEqualTo(style);

            AtomicReference<String> checkPoint1 = new AtomicReference<>();
            AtomicReference<Integer> checkPoint2 = new AtomicReference<>();
            AtomicReference<Float> checkPoint3 = new AtomicReference<>();

            MethodArg1ReturnInvoker<String, String> invoker1 = extractor.buildObjectInvoker(
                    TARGET_INSTANCE_STRING, String.class, String.class);
            ((TestTarget) invoker1.getTarget().get()).instance_a_1 = (s) -> {
                checkPoint1.set(s);
                return "a1";
            };
            Assertions.assertThat(invoker1.invoke("a")).isEqualTo("a1");
            Assertions.assertThat(invoker1.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_INSTANCE_STRING, String.class));
            Assertions.assertThat(invoker1.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker1.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("a");

            checkPoint1.set(null);
            MethodArg2ReturnInvoker<String, String, Integer> invoker2 = extractor.buildObjectInvoker(
                    TARGET_INSTANCE_STRING, String.class, String.class, int.class);
            ((TestTarget) invoker1.getTarget().get()).instance_a_2 = (s, i) -> {
                checkPoint1.set(s);
                checkPoint2.set(i);
                return "a2";
            };
            Assertions.assertThat(invoker2.invoke("b", 9527)).isEqualTo("a2");
            Assertions.assertThat(invoker2.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_INSTANCE_STRING, String.class, int.class));
            Assertions.assertThat(invoker2.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker2.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("b");
            Assertions.assertThat(checkPoint2.get()).isEqualTo(9527);

            checkPoint1.set(null);
            checkPoint2.set(null);
            MethodObjectInvoker<String> invoker3 = extractor.buildObjectInvoker(
                    TARGET_INSTANCE_STRING, String.class, String.class, int.class, float.class);
            ((TestTarget) invoker1.getTarget().get()).instance_a_3 = (s, i, f) -> {
                checkPoint1.set(s);
                checkPoint2.set(i);
                checkPoint3.set(f);
                return "a3";
            };
            Assertions.assertThat(invoker3.invoke("c", 9527, 55.66f)).isEqualTo("a3");
            Assertions.assertThat(invoker3.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_INSTANCE_STRING, String.class, int.class, float.class));
            Assertions.assertThat(invoker3.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker3.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("c");
            Assertions.assertThat(checkPoint2.get()).isEqualTo(9527);
            Assertions.assertThat(checkPoint3.get()).isEqualTo(55.66f);
        };

        FiBiConsumer<MethodExtractor, MethodStyle> ancestorTest = (extractor, style) -> {
            MethodArg0ReturnInvoker<BigDecimal> invoker0 = extractor.buildObjectInvoker(PARENT_INSTANCE_BIGDECIMAL, BigDecimal.class);
            Assertions.assertThat(invoker0.invoke()).isEqualTo(PARENT_RETURN_B);
            Assertions.assertThat(invoker0.getMethod()).isEqualTo(TestParent.class.getDeclaredMethod(PARENT_INSTANCE_BIGDECIMAL));
            Assertions.assertThat(invoker0.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker0.getMethodStyle()).isEqualTo(style);
        };

        MethodExtractor staticExtractor = new MethodExtractor(TestTarget.class);
        staticTest.acceptOrHandle(staticExtractor, MethodStyle.STATIC);

        MethodExtractor instanceExtractor = new MethodExtractor(new TestTarget());
        staticTest.acceptOrHandle(instanceExtractor, MethodStyle.STATIC); // test instanceExtractor can invoke static method
        instanceTest.acceptOrHandle(instanceExtractor, MethodStyle.INSTANCE);

        MethodExtractor ancestorExtractor = new MethodExtractor(TestParent.class, new TestTarget());
        ancestorTest.acceptOrHandle(ancestorExtractor, MethodStyle.ANCESTOR);
    }

    @Test
    void return$error() {
        Runnable staticTest = () -> {
            MethodExtractor extractor = new MethodExtractor(TestTarget.class);

            Assertions.assertThatThrownBy(() -> extractor.buildObjectInvoker("kerker", String.class))
                    .isInstanceOf(NoSuchMethodException.class);

            Assertions.assertThatThrownBy(() -> extractor.buildObjectInvoker(TARGET_INSTANCE_STRING, int.class))
                    .isInstanceOf(NoSuchMethodException.class)
                    .hasMessage(String.format(MethodExtractor.Message.ERROR_RETURN_TYPE, TARGET_INSTANCE_STRING, String.class.getName(), int.class.getName()));

            Assertions.assertThatThrownBy(() -> extractor.buildObjectInvoker(TARGET_INSTANCE_STRING, String.class))
                    .isInstanceOf(NoSuchMethodException.class)
                    .hasMessage(String.format(MethodExtractor.Message.OPERATING_INSTANCE_WITHOUT_TARGET, TARGET_INSTANCE_STRING));
        };

        Runnable instanceTest = () -> {
            MethodExtractor extractor = new MethodExtractor(new TestTarget());

            Assertions.assertThatThrownBy(() -> extractor.buildObjectInvoker("kerker", String.class))
                    .isInstanceOf(NoSuchMethodException.class);

            Assertions.assertThatThrownBy(() -> extractor.buildObjectInvoker(TARGET_INSTANCE_STRING, int.class))
                    .isInstanceOf(NoSuchMethodException.class)
                    .hasMessage(String.format(MethodExtractor.Message.ERROR_RETURN_TYPE, TARGET_INSTANCE_STRING, String.class.getName(), int.class.getName()));
        };

        Runnable ancestorTest = () -> {
            MethodExtractor extractor = new MethodExtractor(TestParent.class, new TestTarget());

            Assertions.assertThatThrownBy(() -> extractor.buildObjectInvoker(TARGET_INSTANCE_STRING, int.class))
                    .isInstanceOf(NoSuchMethodException.class)
                    .hasMessage(TestParent.class.getName() + "." + TARGET_INSTANCE_STRING + "()");
        };

        staticTest.run();
        instanceTest.run();
        ancestorTest.run();
    }

    @Test
    void void$normal() {
        FiBiConsumer<MethodExtractor, MethodStyle> staticTest = (extractor, style) -> {
            AtomicBoolean checkPoint0 = new AtomicBoolean(false);
            MethodArg0VoidInvoker invoker0 = extractor.buildVoidInvoker(TARGET_STATIC_VOID);
            TestTarget.static_b_0 = () -> checkPoint0.set(true);
            invoker0.invoke();
            Assertions.assertThat(invoker0.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_STATIC_VOID));
            Assertions.assertThat(invoker0.getTarget()).isEmpty();
            Assertions.assertThat(invoker0.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint0.get()).isTrue();

            AtomicReference<String> checkPoint1 = new AtomicReference<>();
            AtomicReference<Integer> checkPoint2 = new AtomicReference<>();
            AtomicReference<Float> checkPoint3 = new AtomicReference<>();

            MethodArg1VoidInvoker<String> invoker1 = extractor.buildVoidInvoker(TARGET_STATIC_VOID, String.class);
            TestTarget.static_b_1 = checkPoint1::set;
            invoker1.invoke("A");
            Assertions.assertThat(invoker1.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_STATIC_VOID, String.class));
            Assertions.assertThat(invoker1.getTarget()).isEmpty();
            Assertions.assertThat(invoker1.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("A");

            checkPoint1.set(null);
            MethodArg2VoidInvoker<String, Integer> invoker2 = extractor.buildVoidInvoker(TARGET_STATIC_VOID, String.class, int.class);
            TestTarget.static_b_2 = (s, i) -> {
                checkPoint1.set(s);
                checkPoint2.set(i);
            };
            invoker2.invoke("B", 9527);
            Assertions.assertThat(invoker2.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_STATIC_VOID, String.class, int.class));
            Assertions.assertThat(invoker2.getTarget()).isEmpty();
            Assertions.assertThat(invoker2.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("B");
            Assertions.assertThat(checkPoint2.get()).isEqualTo(9527);

            checkPoint1.set(null);
            checkPoint2.set(null);
            MethodVoidInvoker invoker3 = extractor.buildVoidInvoker(TARGET_STATIC_VOID, String.class, int.class, float.class);
            TestTarget.static_b_3 = (s, i, f) -> {
                checkPoint1.set(s);
                checkPoint2.set(i);
                checkPoint3.set(f);
            };
            invoker3.invoke("C", 9527, 55.66f);
            Assertions.assertThat(invoker3.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_STATIC_VOID, String.class, int.class, float.class));
            Assertions.assertThat(invoker3.getTarget()).isEmpty();
            Assertions.assertThat(invoker3.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("C");
            Assertions.assertThat(checkPoint2.get()).isEqualTo(9527);
            Assertions.assertThat(checkPoint3.get()).isEqualTo(55.66f);
        };

        FiBiConsumer<MethodExtractor, MethodStyle> instanceTest = (extractor, style) -> {
            AtomicBoolean checkPoint0 = new AtomicBoolean(false);
            MethodArg0VoidInvoker invoker0 = extractor.buildVoidInvoker(TARGET_INSTANCE_VOID);

            ((TestTarget) invoker0.getTarget().get()).instance_b_0 = () -> checkPoint0.set(true);
            invoker0.invoke();
            Assertions.assertThat(invoker0.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_INSTANCE_VOID));
            Assertions.assertThat(invoker0.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker0.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint0.get()).isTrue();

            AtomicReference<String> checkPoint1 = new AtomicReference<>();
            AtomicReference<Integer> checkPoint2 = new AtomicReference<>();
            AtomicReference<Float> checkPoint3 = new AtomicReference<>();

            MethodArg1VoidInvoker<String> invoker1 = extractor.buildVoidInvoker(TARGET_INSTANCE_VOID, String.class);
            ((TestTarget) invoker0.getTarget().get()).instance_b_1 = checkPoint1::set;
            invoker1.invoke("A");
            Assertions.assertThat(invoker1.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_INSTANCE_VOID, String.class));
            Assertions.assertThat(invoker1.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker1.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("A");

            checkPoint1.set(null);
            MethodArg2VoidInvoker<String, Integer> invoker2 = extractor.buildVoidInvoker(TARGET_INSTANCE_VOID, String.class, int.class);
            ((TestTarget) invoker0.getTarget().get()).instance_b_2 = (s, i) -> {
                checkPoint1.set(s);
                checkPoint2.set(i);
            };
            invoker2.invoke("B", 9527);
            Assertions.assertThat(invoker2.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_INSTANCE_VOID, String.class, int.class));
            Assertions.assertThat(invoker2.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker2.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("B");
            Assertions.assertThat(checkPoint2.get()).isEqualTo(9527);

            checkPoint1.set(null);
            checkPoint2.set(null);
            MethodVoidInvoker invoker3 = extractor.buildVoidInvoker(TARGET_INSTANCE_VOID, String.class, int.class, float.class);
            ((TestTarget) invoker0.getTarget().get()).instance_b_3 = (s, i, f) -> {
                checkPoint1.set(s);
                checkPoint2.set(i);
                checkPoint3.set(f);
            };
            invoker3.invoke("C", 9527, 55.66f);
            Assertions.assertThat(invoker3.getMethod()).isEqualTo(TestTarget.class.getDeclaredMethod(TARGET_INSTANCE_VOID, String.class, int.class, float.class));
            Assertions.assertThat(invoker3.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker3.getMethodStyle()).isEqualTo(style);
            Assertions.assertThat(checkPoint1.get()).isEqualTo("C");
            Assertions.assertThat(checkPoint2.get()).isEqualTo(9527);
            Assertions.assertThat(checkPoint3.get()).isEqualTo(55.66f);
        };

        FiBiConsumer<MethodExtractor, MethodStyle> ancestorTest = (extractor, style) -> {
            AtomicBoolean checkPoint = new AtomicBoolean(false);
            Runnable runnable = () -> checkPoint.set(true);
            MethodArg1VoidInvoker<Runnable> invoker1 = extractor.buildVoidInvoker(PARENT_INSTANCE_VOID, Runnable.class);
            invoker1.invoke(runnable);
            Assertions.assertThat(checkPoint.get()).isTrue();
            Assertions.assertThat(invoker1.getMethod()).isEqualTo(TestParent.class.getDeclaredMethod(PARENT_INSTANCE_VOID, Runnable.class));
            Assertions.assertThat(invoker1.getTarget()).isNotEmpty();
            Assertions.assertThat(invoker1.getMethodStyle()).isEqualTo(style);
        };

        MethodExtractor staticExtractor = new MethodExtractor(TestTarget.class);
        staticTest.acceptOrHandle(staticExtractor, MethodStyle.STATIC);

        MethodExtractor instanceExtractor = new MethodExtractor(new TestTarget());
        staticTest.acceptOrHandle(instanceExtractor, MethodStyle.STATIC); // test instanceExtractor can invoke static method
        instanceTest.acceptOrHandle(instanceExtractor, MethodStyle.INSTANCE);

        MethodExtractor ancestorExtractor = new MethodExtractor(TestParent.class, new TestTarget());
        ancestorTest.acceptOrHandle(ancestorExtractor, MethodStyle.ANCESTOR);
    }

    @Test
    void void$error() {
        Runnable staticTest = () -> {
            MethodExtractor extractor = new MethodExtractor(TestTarget.class);

            Assertions.assertThatThrownBy(() -> extractor.buildVoidInvoker("kerker", String.class))
                    .isInstanceOf(NoSuchMethodException.class);

            Assertions.assertThatThrownBy(() -> extractor.buildVoidInvoker(TARGET_INSTANCE_VOID, String.class))
                    .isInstanceOf(NoSuchMethodException.class)
                    .hasMessage(String.format(MethodExtractor.Message.OPERATING_INSTANCE_WITHOUT_TARGET, TARGET_INSTANCE_VOID));
        };

        Runnable instanceTest = () -> {
            MethodExtractor extractor = new MethodExtractor(new TestTarget());

            Assertions.assertThatThrownBy(() -> extractor.buildVoidInvoker("kerker", String.class))
                    .isInstanceOf(NoSuchMethodException.class);
        };

        staticTest.run();
        instanceTest.run();
    }

}
