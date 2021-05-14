package org.misty.util.reflect.method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.util.fi.FiBiConsumer;
import org.misty.util.fi.FiConsumer;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.function.BiConsumer;

class MethodExtractorTest {

    public static final String PARENT_RETURN_A = "1";
    public static final BigDecimal PARENT_RETURN_B = new BigDecimal("2");

    public static final String TARGET_RETURN_STATIC_A = "STATIC_A";
    public static final String TARGET_RETURN_STATIC_A0 = "STATIC_A0";
    public static final String TARGET_RETURN_STATIC_A1 = "STATIC_A1";
    public static final String TARGET_RETURN_STATIC_A2 = "STATIC_A2";
    public static final String TARGET_RETURN_STATIC_A3 = "STATIC_A3";
    public static final String TARGET_RETURN_STATIC_A4 = "STATIC_A4";
    public static final String TARGET_RETURN_INSTANCE_A0 = "INSTANCE_A0";
    public static final String TARGET_RETURN_INSTANCE_A1 = "INSTANCE_A1";
    public static final String TARGET_RETURN_INSTANCE_A2 = "INSTANCE_A2";
    public static final String TARGET_RETURN_INSTANCE_A3 = "INSTANCE_A3";
    public static final String TARGET_RETURN_INSTANCE_A4 = "INSTANCE_A4";

    public static class TestParent {
        protected String A() {
            return PARENT_RETURN_A;
        }

        protected BigDecimal B() {
            return PARENT_RETURN_B;
        }

        private void C() {
        }
    }

    public static class TestTarget extends TestParent {
        @Override
        public String A() {
            return TARGET_RETURN_STATIC_A;
        }

        public void thrown1() throws IOException {
            throw new IOException();
        }

        public String thrown2() throws InterruptedException {
            throw new InterruptedException();
        }

        private static String static_a() {
            return TARGET_RETURN_STATIC_A0;
        }

        private static String static_a(String s) {
            return TARGET_RETURN_STATIC_A1;
        }

        private static String static_a(String s, int i) {
            return TARGET_RETURN_STATIC_A2;
        }

        private static String static_a(String s, int i, long l) {
            return TARGET_RETURN_STATIC_A3;
        }

        private static String static_a(String s, int i, long l, float f) {
            return TARGET_RETURN_STATIC_A4;
        }

        private String instance_a() {
            return TARGET_RETURN_INSTANCE_A0;
        }

        private String instance_a(String s) {
            return TARGET_RETURN_INSTANCE_A1;
        }

        private String instance_a(String s, int i) {
            return TARGET_RETURN_INSTANCE_A2;
        }

        private String instance_a(String s, int i, long l) {
            return TARGET_RETURN_INSTANCE_A3;
        }

        private String instance_a(String s, int i, long l, float f) {
            return TARGET_RETURN_INSTANCE_A4;
        }

    }

    private static final String PARENT_INSTANCE_STRING = "A";
    private static final String PARENT_INSTANCE_BIGDECIMAL = "B";
    private static final String PARENT_INSTANCE_BYTE = "C";
    private static final String PARENT_INSTANCE_CHAR = "D";
    private static final String PARENT_INSTANCE_DOUBLE = "E";
    private static final String PARENT_INSTANCE_FLOAT = "F";
    private static final String PARENT_INSTANCE_INT = "G";
    private static final String PARENT_INSTANCE_LONG = "H";
    private static final String PARENT_INSTANCE_SHORT = "I";
    private static final String PARENT_INSTANCE_VOID = "J";

    private static final String TARGET_INSTANCE_THROWN1 = "thrown1";
    private static final String TARGET_INSTANCE_THROWN2 = "thrown2";

    private static final String TARGET_STATIC_STRING = "static_a";
    private static final String TARGET_INSTANCE_STRING = "instance_a";

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
    void assignable$normal() throws NoSuchMethodException {
        MethodExtractor extractor = new MethodExtractor(new TestParent());

        MethodArg0ReturnInvoker<BigDecimal> invoker1 = extractor.buildObjectInvoker(PARENT_INSTANCE_BIGDECIMAL, BigDecimal.class);
        Assertions.assertThat(invoker1.invoke()).isEqualTo(PARENT_RETURN_B);

        MethodArg0ReturnInvoker<Number> invoker2 = extractor.buildObjectInvoker(PARENT_INSTANCE_BIGDECIMAL, Number.class);
        Assertions.assertThat(invoker2.invoke()).isEqualTo(PARENT_RETURN_B);
    }

    @Test
    void return_String$normal() {
        FiBiConsumer<MethodExtractor, MethodStyle> staticTest = (extractor, style) -> {
            MethodArg0ReturnInvoker<String> invoker0 = extractor.buildObjectInvoker(TARGET_STATIC_STRING, String.class);
            Assertions.assertThat(invoker0.invoke()).isEqualTo(TARGET_RETURN_STATIC_A0);

            try (MockedStatic<TestTarget> mockedStatic = Mockito.mockStatic(TestTarget.class)) {
                mockedStatic.when(() -> {
                    TestTarget.static_a(Mockito.anyString());
                }).thenReturn("wtf");
            }

            MethodArg1ReturnInvoker<String, String> invoker1 = extractor.buildObjectInvoker(
                    TARGET_STATIC_STRING, String.class, String.class);
            Assertions.assertThat(TestTarget.static_a("kerker")).isEqualTo("wtf");

        };

        staticTest.acceptOrHandle(new MethodExtractor(TestTarget.class), MethodStyle.STATIC);


    }

}
