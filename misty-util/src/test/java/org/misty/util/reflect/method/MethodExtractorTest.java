package org.misty.util.reflect.method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.util.fi.FiConsumer;

import java.io.IOException;

class MethodExtractorTest {

    public static final String PARENT_RETURN_A = "A";
    public static final byte PARENT_RETURN_B = 120;
    public static final char PARENT_RETURN_C = 130;
    public static final double PARENT_RETURN_D = 140;
    public static final float PARENT_RETURN_E = 150;
    public static final int PARENT_RETURN_F = 160;
    public static final long PARENT_RETURN_G = 170;
    public static final short PARENT_RETURN_H = 180;

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

        private byte B() {
            return PARENT_RETURN_B;
        }

        private char C() {
            return PARENT_RETURN_C;
        }

        private double D() {
            return PARENT_RETURN_D;
        }

        private float E() {
            return PARENT_RETURN_E;
        }

        private int F() {
            return PARENT_RETURN_F;
        }

        private long G() {
            return PARENT_RETURN_G;
        }

        private short H() {
            return PARENT_RETURN_H;
        }

        private void I() {
        }
    }

    public static class TestTarget extends TestParent {
        @Override
        public String A() {
            return getClass().getSimpleName();
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
    private static final String PARENT_INSTANCE_BYTE = "B";
    private static final String PARENT_INSTANCE_CHAR = "C";
    private static final String PARENT_INSTANCE_DOUBLE = "D";
    private static final String PARENT_INSTANCE_FLOAT = "E";
    private static final String PARENT_INSTANCE_INT = "F";
    private static final String PARENT_INSTANCE_LONG = "G";
    private static final String PARENT_INSTANCE_SHORT = "H";
    private static final String PARENT_INSTANCE_VOID = "I";

    private static final String TARGET_INSTANCE_THROWN1 = "thrown1";
    private static final String TARGET_INSTANCE_THROWN2 = "thrown2";

    private static final String TARGET_STATIC_STRING = "static_a";
    private static final String TARGET_INSTANCE_STRING = "instance_a";

    @Test
    void overrideMethod() {
        FiConsumer<MethodExtractor> test = (extractor) -> {
            MethodArg0ReturnInvoker<String> invoker = extractor.buildObjectInvoker(PARENT_INSTANCE_STRING, String.class);
            String result = invoker.invoke();
            Assertions.assertThat(result).isEqualTo(TestTarget.class.getSimpleName());
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

}
