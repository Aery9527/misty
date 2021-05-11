package org.misty.util.reflect.method;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class MethodExtractorTest {

    public static final String RETURN_A = "A";
    public static final byte RETURN_B = 120;
    public static final char RETURN_C = 130;
    public static final double RETURN_D = 140;
    public static final float RETURN_E = 150;
    public static final int RETURN_F = 160;
    public static final long RETURN_G = 170;
    public static final short RETURN_H = 180;

    public static final String RETURN_static_a0 = "static_a0";
    public static final String RETURN_static_a1 = "static_a1";
    public static final String RETURN_static_a2 = "static_a2";
    public static final String RETURN_static_a3 = "static_a3";
    public static final String RETURN_static_a4 = "static_a4";
    public static final String RETURN_instance_a0 = "instance_a0";
    public static final String RETURN_instance_a1 = "instance_a1";
    public static final String RETURN_instance_a2 = "instance_a2";
    public static final String RETURN_instance_a3 = "instance_a3";
    public static final String RETURN_instance_a4 = "instance_a4";

    public static class TestTargetParent {
        protected String A() {
            return RETURN_A;
        }

        private byte B() {
            return RETURN_B;
        }

        private char C() {
            return RETURN_C;
        }

        private double D() {
            return RETURN_D;
        }

        private float E() {
            return RETURN_E;
        }

        private int F() {
            return RETURN_F;
        }

        private long G() {
            return RETURN_G;
        }

        private short H() {
            return RETURN_H;
        }

        private void I() {
        }
    }

    public static class TestTarget extends TestTargetParent {
        @Override
        public String A() {
            return getClass().getSimpleName();
        }

        public void thrown() throws IOException {
            throw new IOException();
        }

        private static String static_a() {
            return RETURN_static_a0;
        }

        private static String static_a(String s) {
            return RETURN_static_a1;
        }

        private static String static_a(String s, int i) {
            return RETURN_static_a2;
        }

        private static String static_a(String s, int i, long l) {
            return RETURN_static_a3;
        }

        private static String static_a(String s, int i, long l, float f) {
            return RETURN_static_a4;
        }

        private String instance_a() {
            return RETURN_instance_a0;
        }

        private String instance_a(String s) {
            return RETURN_instance_a1;
        }

        private String instance_a(String s, int i) {
            return RETURN_instance_a2;
        }

        private String instance_a(String s, int i, long l) {
            return RETURN_instance_a3;
        }

        private String instance_a(String s, int i, long l, float f) {
            return RETURN_instance_a4;
        }

    }

    private static final String INSTANCE_STRING = "A";
    private static final String INSTANCE_BYTE = "B";
    private static final String INSTANCE_CHAR = "C";
    private static final String INSTANCE_DOUBLE = "D";
    private static final String INSTANCE_FLOAT = "E";
    private static final String INSTANCE_INT = "F";
    private static final String INSTANCE_LONG = "G";
    private static final String INSTANCE_SHORT = "H";
    private static final String INSTANCE_VOID = "I";



    @Test
    void overrideMethod() throws NoSuchMethodException {
//        MethodExtractor extractor = new MethodExtractor(TestTarget.class);
//        MethodArg0ReturnInvoker<String> a = extractor.buildObjectInvoker("kerker", String.class);
//        MethodArg1ReturnInvoker<String, Integer> b = extractor.buildObjectInvoker("kerker", String.class, int.class);
//        MethodArg2ReturnInvoker<String, Integer, Integer> c = extractor.buildObjectInvoker("kerker", String.class, int.class, int.class);
//        MethodObjectInvoker<String> d = extractor.buildObjectInvoker("kerker", String.class, int.class, int.class, int.class);

//        MethodExtractor extractor = new MethodExtractor(TestTargetParent.class, new TestTarget());
        MethodExtractor extractor = new MethodExtractor(new TestTarget());

        MethodArg0ReturnInvoker<String> invoker = extractor.buildObjectInvoker("A", String.class);
        String result = invoker.invoke();

        System.out.println(result);


    }


}
