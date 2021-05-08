package org.misty.util.reflect.field;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.misty.util.fi.FiBiFunction;
import org.misty.util.fi.FiConsumer;
import org.misty.util.fi.FiFunction;

import java.math.BigDecimal;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class FieldExtractorTest {

    static final String DEFAULT_A1 = "A";
    static final String DEFAULT_A2 = "B";
    static final byte DEFAULT_B1 = 121;
    static final byte DEFAULT_B2 = 122;
    static final char DEFAULT_C1 = 131;
    static final char DEFAULT_C2 = 132;
    static final double DEFAULT_D1 = 141;
    static final double DEFAULT_D2 = 142;
    static final float DEFAULT_E1 = 151;
    static final float DEFAULT_E2 = 152;
    static final int DEFAULT_F1 = 161;
    static final int DEFAULT_F2 = 162;
    static final long DEFAULT_G1 = 171;
    static final long DEFAULT_G2 = 172;
    static final short DEFAULT_H1 = 181;
    static final short DEFAULT_H2 = 182;

    static final String DEFAULT_a1 = "a";
    static final BigDecimal DEFAULT_a2 = new BigDecimal("1");
    static final String DEFAULT_a3 = "b";
    static final BigDecimal DEFAULT_a4 = new BigDecimal("2");
    static final String DEFAULT_a5 = "c";
    static final BigDecimal DEFAULT_a6 = new BigDecimal("3");
    static final String DEFAULT_a7 = "d";
    static final BigDecimal DEFAULT_a8 = new BigDecimal("4");

    static final byte DEFAULT_b0 = 20;
    static final byte DEFAULT_b1 = 21;
    static final byte DEFAULT_b2 = 22;
    static final byte DEFAULT_b3 = 23;
    static final byte DEFAULT_b4 = 24;

    static final char DEFAULT_c0 = 30;
    static final char DEFAULT_c1 = 31;
    static final char DEFAULT_c2 = 32;
    static final char DEFAULT_c3 = 33;
    static final char DEFAULT_c4 = 34;

    static final double DEFAULT_d0 = 40;
    static final double DEFAULT_d1 = 41;
    static final double DEFAULT_d2 = 42;
    static final double DEFAULT_d3 = 43;
    static final double DEFAULT_d4 = 44;

    static final float DEFAULT_e0 = 50;
    static final float DEFAULT_e1 = 51;
    static final float DEFAULT_e2 = 52;
    static final float DEFAULT_e3 = 53;
    static final float DEFAULT_e4 = 54;

    static final int DEFAULT_f0 = 60;
    static final int DEFAULT_f1 = 61;
    static final int DEFAULT_f2 = 62;
    static final int DEFAULT_f3 = 63;
    static final int DEFAULT_f4 = 64;

    static final long DEFAULT_g0 = 70;
    static final long DEFAULT_g1 = 71;
    static final long DEFAULT_g2 = 72;
    static final long DEFAULT_g3 = 73;
    static final long DEFAULT_g4 = 74;

    static final short DEFAULT_h0 = 80;
    static final short DEFAULT_h1 = 81;
    static final short DEFAULT_h2 = 82;
    static final short DEFAULT_h3 = 83;
    static final short DEFAULT_h4 = 84;

    public static class TestTargetParent {
        private final String A1 = DEFAULT_A1;
        private String A2 = DEFAULT_A2;

        private final byte B1 = DEFAULT_B1;
        private byte B2 = DEFAULT_B2;

        private final char C1 = DEFAULT_C1;
        private char C2 = DEFAULT_C2;

        private final double D1 = DEFAULT_D1;
        private double D2 = DEFAULT_D2;

        private final float E1 = DEFAULT_E1;
        private float E2 = DEFAULT_E2;

        private final int F1 = DEFAULT_F1;
        private int F2 = DEFAULT_F2;

        private final long G1 = DEFAULT_G1;
        private long G2 = DEFAULT_G2;

        private final short H1 = DEFAULT_H1;
        private short H2 = DEFAULT_H2;
    }

    public static class TestTarget extends TestTargetParent {
        private static final String a1 = DEFAULT_a1;
        private static final BigDecimal a2 = DEFAULT_a2;
        private static String a3 = DEFAULT_a3;
        private static BigDecimal a4 = DEFAULT_a4;
        private final String a5 = DEFAULT_a5;
        private final BigDecimal a6 = DEFAULT_a6;
        private String a7 = DEFAULT_a7;
        private BigDecimal a8 = DEFAULT_a8;

        private static final Byte b0 = DEFAULT_b0;
        private static final byte b1 = DEFAULT_b1;
        private static byte b2 = DEFAULT_b2;
        private final byte b3 = DEFAULT_b3;
        private byte b4 = DEFAULT_b4;

        private static final Character c0 = DEFAULT_c0;
        private static final char c1 = DEFAULT_c1;
        private static char c2 = DEFAULT_c2;
        private final char c3 = DEFAULT_c3;
        private char c4 = DEFAULT_c4;

        private static final Double d0 = DEFAULT_d0;
        private static final double d1 = DEFAULT_d1;
        private static double d2 = DEFAULT_d2;
        private final double d3 = DEFAULT_d3;
        private double d4 = DEFAULT_d4;

        private static final Float e0 = DEFAULT_e0;
        private static final float e1 = DEFAULT_e1;
        private static float e2 = DEFAULT_e2;
        private final float e3 = DEFAULT_e3;
        private float e4 = DEFAULT_e4;

        private static final Integer f0 = DEFAULT_f0;
        private static final int f1 = DEFAULT_f1;
        private static int f2 = DEFAULT_f2;
        private final int f3 = DEFAULT_f3;
        private int f4 = DEFAULT_f4;

        private static final Long g0 = DEFAULT_g0;
        private static final long g1 = DEFAULT_g1;
        private static long g2 = DEFAULT_g2;
        private final long g3 = DEFAULT_g3;
        private long g4 = DEFAULT_g4;

        private static final Short h0 = DEFAULT_h0;
        private static final short h1 = DEFAULT_h1;
        private static short h2 = DEFAULT_h2;
        private final short h3 = DEFAULT_h3;
        private short h4 = DEFAULT_h4;
    }

    public static final String INSTANCE_FINAL_STRING = "A1";
    public static final String INSTANCE_STRING = "A2";
    public static final String INSTANCE_FINAL_BYTE = "B1";
    public static final String INSTANCE_BYTE = "B2";
    public static final String INSTANCE_FINAL_CHAR = "C1";
    public static final String INSTANCE_CHAR = "C2";
    public static final String INSTANCE_FINAL_DOUBLE = "D1";
    public static final String INSTANCE_DOUBLE = "D2";
    public static final String INSTANCE_FINAL_FLOAT = "E1";
    public static final String INSTANCE_FLOAT = "E2";
    public static final String INSTANCE_FINAL_INT = "F1";
    public static final String INSTANCE_INT = "F2";
    public static final String INSTANCE_FINAL_LONG = "G1";
    public static final String INSTANCE_LONG = "G2";
    public static final String INSTANCE_FINAL_SHORT = "H1";
    public static final String INSTANCE_SHORT = "H2";

    public static final String static_final_String = "a1";
    public static final String static_final_BigDecimal = "a2";
    public static final String static_String = "a3";
    public static final String static_BigDecimal = "a4";
    public static final String instance_final_String = "a5";
    public static final String instance_final_BigDecimal = "a6";
    public static final String instance_String = "a7";
    public static final String instance_BigDecimal = "a8";

    public static final String static_final_Byte = "b0";
    public static final String static_final_byte = "b1";
    public static final String static_byte = "b2";
    public static final String instance_final_byte = "b3";
    public static final String instance_byte = "b4";

    public static final String static_final_Character = "c0";
    public static final String static_final_char = "c1";
    public static final String static_char = "c2";
    public static final String instance_final_char = "c3";
    public static final String instance_char = "c4";

    public static final String static_final_Double = "d0";
    public static final String static_final_double = "d1";
    public static final String static_double = "d2";
    public static final String instance_final_double = "d3";
    public static final String instance_double = "d4";

    public static final String static_final_Float = "e0";
    public static final String static_final_float = "e1";
    public static final String static_float = "e2";
    public static final String instance_final_float = "e3";
    public static final String instance_float = "e4";

    public static final String static_final_Integer = "f0";
    public static final String static_final_int = "f1";
    public static final String static_int = "f2";
    public static final String instance_final_int = "f3";
    public static final String instance_int = "f4";

    public static final String static_final_Long = "g0";
    public static final String static_final_long = "g1";
    public static final String static_long = "g2";
    public static final String instance_final_long = "g3";
    public static final String instance_long = "g4";

    public static final String static_final_Short = "h0";
    public static final String static_final_short = "h1";
    public static final String static_short = "h2";
    public static final String instance_final_short = "h3";
    public static final String instance_short = "h4";

    public static class NormalTester<Target extends FieldAbstract, FieldType> {
        private final String name;
        private final Class<FieldType> fieldType;
        private final FieldStyle fieldStyle;
        private final Target target;

        public NormalTester(String name, Class<FieldType> fieldType, FieldStyle fieldStyle, FiBiFunction<String, Class<FieldType>, Target> builder) {
            this.name = name;
            this.fieldType = fieldType;
            this.fieldStyle = fieldStyle;
            this.target = builder.applyOrHandle(this.name, this.fieldType);
        }

        public NormalTester(String name, Class<FieldType> fieldType, FieldStyle fieldStyle, FiFunction<String, Target> builder) {
            this.name = name;
            this.fieldType = fieldType;
            this.fieldStyle = fieldStyle;
            this.target = builder.applyOrHandle(this.name);
        }

        public void test(FiConsumer<Target> action) {
            testCommon((target) -> {
                Assertions.assertThat(target.getField().getType()).isEqualTo(this.fieldType);
                action.acceptOrHandle(target);
            });
        }

        public void testWithAssignable(FiConsumer<Target> action) {
            testCommon((target) -> {
                Assertions.assertThat(this.fieldType).isAssignableFrom(target.getField().getType());
                action.acceptOrHandle(target);
            });
        }

        private void testCommon(Consumer<Target> action) {
            Assertions.assertThat(this.target.getField().getName()).isEqualTo(this.name);
            Assertions.assertThat(this.target.getFieldStyle()).isEqualTo(this.fieldStyle);

            if (this.fieldStyle.isAncestor()) {
                Assertions.assertThat(this.target.getField().getDeclaringClass()).isAssignableFrom(TestTarget.class);
            } else {
                Assertions.assertThat(this.target.getField().getDeclaringClass()).isEqualTo(TestTarget.class);
            }

            action.accept(this.target);
        }
    }

    public static class ErrorObjectTester {
        private final FiBiFunction<String, Class<?>, FieldAbstract> builder;

        public ErrorObjectTester(FiBiFunction<String, Class<?>, FieldAbstract> builder) {
            this.builder = builder;
        }

        public void noSuchField(Class<?> fieldType, String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name, fieldType))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(name);
        }

        public void withoutInstance(Class<?> fieldType, String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name, fieldType))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.Message.OPERATING_INSTANCE_WITHOUT_TARGET, name));
        }

        public void errorFieldType(Class<?> fieldType, String name, Class<?> actuallyType) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name, fieldType))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.Message.ERROR_FIELD_TYPE, name, actuallyType, fieldType));
        }

        public void fieldIsFinal(Class<?> fieldType, String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name, fieldType))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.Message.FIELD_IS_FINAL, name));
        }
    }

    public static class ErrorPrimitiveTester {

        private final FiFunction<String, FieldAbstract> builder;

        public ErrorPrimitiveTester(FiFunction<String, FieldAbstract> builder) {
            this.builder = builder;
        }

        public void noSuchField(String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(name);
        }

        public void withoutInstance(String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.Message.OPERATING_INSTANCE_WITHOUT_TARGET, name));
        }

        public void errorFieldType(String name, Class<?> fieldType, Class<?> actuallyType) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.Message.ERROR_FIELD_TYPE, name, actuallyType, fieldType));
        }

        public void fieldIsFinal(String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.Message.FIELD_IS_FINAL, name));
        }

    }

    @BeforeEach
    void setUp() {
        TestTarget.a3 = DEFAULT_a3;
        TestTarget.a4 = DEFAULT_a4;
        TestTarget.b2 = DEFAULT_b2;
        TestTarget.c2 = DEFAULT_c2;
        TestTarget.d2 = DEFAULT_d2;
        TestTarget.e2 = DEFAULT_e2;
        TestTarget.f2 = DEFAULT_f2;
        TestTarget.g2 = DEFAULT_g2;
        TestTarget.h2 = DEFAULT_h2;
    }

    //

    @Test
    void buildObjectGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());
        FieldExtractor ancestorExtractor = new FieldExtractor(TestTargetParent.class, new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_String, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a1);
            });

            new NormalTester<>(static_String, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a3);
            });

            new NormalTester<>(static_final_BigDecimal, BigDecimal.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a2);
            });
            new NormalTester<>(static_final_BigDecimal, Number.class, style, extractor::buildGetter).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a2);
            });

            new NormalTester<>(static_BigDecimal, BigDecimal.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a4);
            });
            new NormalTester<>(static_BigDecimal, Number.class, style, extractor::buildGetter).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a4);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_String, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a5);
            });

            new NormalTester<>(instance_String, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a7);
            });

            new NormalTester<>(instance_final_BigDecimal, BigDecimal.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a6);
            });
            new NormalTester<>(instance_final_BigDecimal, Number.class, style, extractor::buildGetter).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a6);
            });

            new NormalTester<>(instance_BigDecimal, BigDecimal.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a8);
            });
            new NormalTester<>(instance_BigDecimal, Number.class, style, extractor::buildGetter).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a8);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> ancestorTest = (extractor, style) -> {
            new NormalTester<>(INSTANCE_FINAL_STRING, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_A1);
            });

            new NormalTester<>(INSTANCE_STRING, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_A2);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);

        ancestorTest.accept(ancestorExtractor, FieldStyle.ANCESTOR);
    }

    @Test
    void buildObjectGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorObjectTester errorTester = new ErrorObjectTester(extractor::buildGetter);

            errorTester.noSuchField(String.class, "kerker");

            errorTester.errorFieldType(Double.class, static_final_String, String.class);
            errorTester.errorFieldType(Float.class, static_final_BigDecimal, BigDecimal.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(String.class, instance_String);
                errorTester.withoutInstance(BigDecimal.class, instance_BigDecimal);
                errorTester.withoutInstance(String.class, instance_String);
                errorTester.withoutInstance(BigDecimal.class, instance_BigDecimal);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);

        FieldExtractor extractor = new FieldExtractor(TestTargetParent.class, new TestTarget());
        ErrorObjectTester errorTester = new ErrorObjectTester(extractor::buildGetter);
        errorTester.noSuchField(String.class, instance_String);
    }

    @Test
    void buildObjectSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());
        FieldExtractor ancestorExtractor = new FieldExtractor(TestTargetParent.class, new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_String, String.class, style, extractor::buildSetter).test((target) -> {
                String newString = String.valueOf(System.currentTimeMillis());

                target.set(newString);

                FieldObjectGetter<String> getter = staticExtractor.buildGetter(static_String, String.class);
                Assertions.assertThat(getter.get()).isEqualTo(newString);
            });

            new NormalTester<>(static_BigDecimal, BigDecimal.class, style, extractor::buildSetter).test((target) -> {
                BigDecimal newBigDecimal = new BigDecimal(System.currentTimeMillis());

                target.set(newBigDecimal);

                FieldObjectGetter<BigDecimal> getter = staticExtractor.buildGetter(static_BigDecimal, BigDecimal.class);
                Assertions.assertThat(getter.get()).isEqualTo(newBigDecimal);
            });
            new NormalTester<>(static_BigDecimal, Number.class, style, extractor::buildSetter).testWithAssignable((target) -> {
                BigDecimal newBigDecimal = new BigDecimal(System.currentTimeMillis());

                target.set(newBigDecimal);

                FieldObjectGetter<Number> getter = staticExtractor.buildGetter(static_BigDecimal, Number.class);
                Assertions.assertThat(getter.get()).isEqualTo(newBigDecimal);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_String, String.class, style, extractor::buildSetter).test((target) -> {
                String newString = String.valueOf(System.currentTimeMillis());

                target.set(newString);

                FieldObjectGetter<String> getter = extractor.buildGetter(instance_String, String.class);
                Assertions.assertThat(getter.get()).isEqualTo(newString);
            });

            new NormalTester<>(instance_BigDecimal, BigDecimal.class, style, extractor::buildSetter).test((target) -> {
                BigDecimal newBigDecimal = new BigDecimal(System.currentTimeMillis());

                target.set(newBigDecimal);

                FieldObjectGetter<BigDecimal> getter = extractor.buildGetter(instance_BigDecimal, BigDecimal.class);
                Assertions.assertThat(getter.get()).isEqualTo(newBigDecimal);
            });
            new NormalTester<>(instance_BigDecimal, Number.class, style, extractor::buildSetter).testWithAssignable((target) -> {
                BigDecimal newBigDecimal = new BigDecimal(System.currentTimeMillis());

                target.set(newBigDecimal);

                FieldObjectGetter<Number> getter = extractor.buildGetter(instance_BigDecimal, Number.class);
                Assertions.assertThat(getter.get()).isEqualTo(newBigDecimal);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> ancestorTest = (extractor, style) -> {
            new NormalTester<>(INSTANCE_STRING, String.class, style, extractor::buildSetter).test((target) -> {
                String newString = String.valueOf(System.currentTimeMillis());

                target.set(newString);

                FieldObjectGetter<String> getter = extractor.buildGetter(INSTANCE_STRING, String.class);
                Assertions.assertThat(getter.get()).isEqualTo(newString);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);

        ancestorTest.accept(ancestorExtractor, FieldStyle.ANCESTOR);
    }

    @Test
    void buildObjectSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorObjectTester errorTester = new ErrorObjectTester(extractor::buildSetter);

            errorTester.noSuchField(String.class, "kerker");

            errorTester.errorFieldType(Double.class, static_final_String, String.class);
            errorTester.errorFieldType(Float.class, static_final_BigDecimal, BigDecimal.class);

            errorTester.fieldIsFinal(String.class, static_final_String);
            errorTester.fieldIsFinal(BigDecimal.class, static_final_BigDecimal);
            errorTester.fieldIsFinal(String.class, instance_final_String);
            errorTester.fieldIsFinal(BigDecimal.class, instance_final_BigDecimal);

            if (isStaticExtractor) {
                errorTester.withoutInstance(String.class, instance_String);
                errorTester.withoutInstance(BigDecimal.class, instance_BigDecimal);
                errorTester.withoutInstance(String.class, instance_String);
                errorTester.withoutInstance(BigDecimal.class, instance_BigDecimal);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);

        FieldExtractor extractor = new FieldExtractor(TestTargetParent.class, new TestTarget());
        ErrorObjectTester errorTester = new ErrorObjectTester(extractor::buildSetter);
        errorTester.noSuchField(String.class, instance_String);
        errorTester.fieldIsFinal(String.class, INSTANCE_FINAL_STRING);
    }

    @Test
    void buildObjectOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());
        FieldExtractor ancestorExtractor = new FieldExtractor(TestTargetParent.class, new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_String, String.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a3);
            });

            new NormalTester<>(static_BigDecimal, BigDecimal.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a4);
            });
            new NormalTester<>(static_BigDecimal, Number.class, style, extractor::buildOperator).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a4);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_String, String.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a7);
            });

            new NormalTester<>(instance_BigDecimal, BigDecimal.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a8);
            });
            new NormalTester<>(instance_BigDecimal, Number.class, style, extractor::buildOperator).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_a8);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> ancestorTest = (extractor, style) -> {
            new NormalTester<>(INSTANCE_STRING, String.class, style, extractor::buildOperator).test((target) -> {
                String newString = String.valueOf(System.currentTimeMillis());

                target.set(newString);

                FieldObjectGetter<String> getter = extractor.buildGetter(INSTANCE_STRING, String.class);
                Assertions.assertThat(getter.get()).isEqualTo(newString);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);

        ancestorTest.accept(ancestorExtractor, FieldStyle.ANCESTOR);
    }

    @Test
    void buildObjectOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorObjectTester errorTester = new ErrorObjectTester(extractor::buildOperator);

            errorTester.noSuchField(String.class, "kerker");

            errorTester.errorFieldType(Double.class, static_final_String, String.class);
            errorTester.errorFieldType(Float.class, static_final_BigDecimal, BigDecimal.class);

            errorTester.fieldIsFinal(String.class, static_final_String);
            errorTester.fieldIsFinal(BigDecimal.class, static_final_BigDecimal);
            errorTester.fieldIsFinal(String.class, instance_final_String);
            errorTester.fieldIsFinal(BigDecimal.class, instance_final_BigDecimal);

            if (isStaticExtractor) {
                errorTester.withoutInstance(String.class, instance_String);
                errorTester.withoutInstance(BigDecimal.class, instance_BigDecimal);
                errorTester.withoutInstance(String.class, instance_String);
                errorTester.withoutInstance(BigDecimal.class, instance_BigDecimal);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    //

    @Test
    void buildByteGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_byte, byte.class, style, extractor::buildByteGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_b1);
            });

            new NormalTester<>(static_byte, byte.class, style, extractor::buildByteGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_b2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_byte, byte.class, style, extractor::buildByteGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_b3);
            });

            new NormalTester<>(instance_byte, byte.class, style, extractor::buildByteGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_b4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildByteGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildByteGetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Byte, byte.class, Byte.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_final_byte);
                errorTester.withoutInstance(instance_byte);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildByteSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_byte, byte.class, style, extractor::buildByteSetter).test((target) -> {
                byte newByte = -127;

                target.set(newByte);

                FieldByteGetter getter = staticExtractor.buildByteGetter(static_byte);
                Assertions.assertThat(getter.get()).isEqualTo(newByte);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_byte, byte.class, style, extractor::buildByteSetter).test((target) -> {
                byte newByte = -127;

                target.set(newByte);

                FieldByteGetter getter = instanceExtractor.buildByteGetter(instance_byte);
                Assertions.assertThat(getter.get()).isEqualTo(newByte);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildByteSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildByteSetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Byte, byte.class, Byte.class);

            errorTester.fieldIsFinal(static_final_byte);
            errorTester.fieldIsFinal(instance_final_byte);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_byte);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildByteOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_byte, byte.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_b2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_byte, byte.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_b4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildByteOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildByteOperator);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Byte, byte.class, Byte.class);

            errorTester.fieldIsFinal(static_final_byte);
            errorTester.fieldIsFinal(instance_final_byte);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_byte);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    //

    @Test
    void buildCharGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_char, char.class, style, extractor::buildCharGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_c1);
            });

            new NormalTester<>(static_char, char.class, style, extractor::buildCharGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_c2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_char, char.class, style, extractor::buildCharGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_c3);
            });

            new NormalTester<>(instance_char, char.class, style, extractor::buildCharGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_c4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildCharGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildCharGetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Character, char.class, Character.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_final_char);
                errorTester.withoutInstance(instance_char);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildCharSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_char, char.class, style, extractor::buildCharSetter).test((target) -> {
                char newChar = 'z';

                target.set(newChar);

                FieldCharGetter getter = staticExtractor.buildCharGetter(static_char);
                Assertions.assertThat(getter.get()).isEqualTo(newChar);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_char, char.class, style, extractor::buildCharSetter).test((target) -> {
                char newChar = 'z';

                target.set(newChar);

                FieldCharGetter getter = instanceExtractor.buildCharGetter(instance_char);
                Assertions.assertThat(getter.get()).isEqualTo(newChar);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildCharSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildCharSetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Character, char.class, Character.class);

            errorTester.fieldIsFinal(static_final_char);
            errorTester.fieldIsFinal(instance_final_char);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_char);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildCharOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_char, char.class, style, extractor::buildCharOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_c2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_char, char.class, style, extractor::buildCharOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_c4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildCharOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildCharOperator);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Character, char.class, Character.class);

            errorTester.fieldIsFinal(static_final_char);
            errorTester.fieldIsFinal(instance_final_char);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_char);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    //

    @Test
    void buildDoubleGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_double, double.class, style, extractor::buildDoubleGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_d1);
            });

            new NormalTester<>(static_double, double.class, style, extractor::buildDoubleGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_d2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_double, double.class, style, extractor::buildDoubleGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_d3);
            });

            new NormalTester<>(instance_double, double.class, style, extractor::buildDoubleGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_d4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildDoubleGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildDoubleGetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Double, double.class, Double.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_final_double);
                errorTester.withoutInstance(instance_double);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildDoubleSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_double, double.class, style, extractor::buildDoubleSetter).test((target) -> {
                double newDouble = -9527;

                target.set(newDouble);

                FieldDoubleGetter getter = staticExtractor.buildDoubleGetter(static_double);
                Assertions.assertThat(getter.get()).isEqualTo(newDouble);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_double, double.class, style, extractor::buildDoubleSetter).test((target) -> {
                double newDouble = -9527;

                target.set(newDouble);

                FieldDoubleGetter getter = instanceExtractor.buildDoubleGetter(instance_double);
                Assertions.assertThat(getter.get()).isEqualTo(newDouble);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildDoubleSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildDoubleSetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Double, double.class, Double.class);

            errorTester.fieldIsFinal(static_final_double);
            errorTester.fieldIsFinal(instance_final_double);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_double);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildDoubleOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_double, double.class, style, extractor::buildDoubleOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_d2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_double, double.class, style, extractor::buildDoubleOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_d4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildDoubleOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildDoubleOperator);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Double, double.class, Double.class);

            errorTester.fieldIsFinal(static_final_double);
            errorTester.fieldIsFinal(instance_final_double);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_double);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    //

    @Test
    void buildFloatGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_float, float.class, style, extractor::buildFloatGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_e1);
            });

            new NormalTester<>(static_float, float.class, style, extractor::buildFloatGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_e2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_float, float.class, style, extractor::buildFloatGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_e3);
            });

            new NormalTester<>(instance_float, float.class, style, extractor::buildFloatGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_e4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildFloatGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildFloatGetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Float, float.class, Float.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_final_float);
                errorTester.withoutInstance(instance_float);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildFloatSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_float, float.class, style, extractor::buildFloatSetter).test((target) -> {
                float newFloat = -9527;

                target.set(newFloat);

                FieldFloatGetter getter = staticExtractor.buildFloatGetter(static_float);
                Assertions.assertThat(getter.get()).isEqualTo(newFloat);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_float, float.class, style, extractor::buildFloatSetter).test((target) -> {
                float newFloat = -9527;

                target.set(newFloat);

                FieldFloatGetter getter = instanceExtractor.buildFloatGetter(instance_float);
                Assertions.assertThat(getter.get()).isEqualTo(newFloat);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildFloatSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildFloatSetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Float, float.class, Float.class);

            errorTester.fieldIsFinal(static_final_float);
            errorTester.fieldIsFinal(instance_final_float);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_float);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildFloatOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_float, float.class, style, extractor::buildFloatOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_e2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_float, float.class, style, extractor::buildFloatOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_e4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildFloatOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildFloatOperator);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Float, float.class, Float.class);

            errorTester.fieldIsFinal(static_final_float);
            errorTester.fieldIsFinal(instance_final_float);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_float);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    //

    @Test
    void buildIntGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_int, int.class, style, extractor::buildIntGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_f1);
            });

            new NormalTester<>(static_int, int.class, style, extractor::buildIntGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_f2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_int, int.class, style, extractor::buildIntGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_f3);
            });

            new NormalTester<>(instance_int, int.class, style, extractor::buildIntGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_f4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildIntGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildIntGetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Integer, int.class, Integer.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_final_int);
                errorTester.withoutInstance(instance_int);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildIntSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_int, int.class, style, extractor::buildIntSetter).test((target) -> {
                int newInt = -9527;

                target.set(newInt);

                FieldIntGetter getter = staticExtractor.buildIntGetter(static_int);
                Assertions.assertThat(getter.get()).isEqualTo(newInt);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_int, int.class, style, extractor::buildIntSetter).test((target) -> {
                int newInt = -9527;

                target.set(newInt);

                FieldIntGetter getter = instanceExtractor.buildIntGetter(instance_int);
                Assertions.assertThat(getter.get()).isEqualTo(newInt);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildIntSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildIntSetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Integer, int.class, Integer.class);

            errorTester.fieldIsFinal(static_final_int);
            errorTester.fieldIsFinal(instance_final_int);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_int);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildIntOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_int, int.class, style, extractor::buildIntOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_f2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_int, int.class, style, extractor::buildIntOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_f4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildIntOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildIntOperator);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Integer, int.class, Integer.class);

            errorTester.fieldIsFinal(static_final_int);
            errorTester.fieldIsFinal(instance_final_int);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_int);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    //

    @Test
    void buildLongGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_long, long.class, style, extractor::buildLongGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_g1);
            });

            new NormalTester<>(static_long, long.class, style, extractor::buildLongGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_g2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_long, long.class, style, extractor::buildLongGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_g3);
            });

            new NormalTester<>(instance_long, long.class, style, extractor::buildLongGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_g4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildLongGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildLongGetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Long, long.class, Long.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_final_long);
                errorTester.withoutInstance(instance_long);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildLongSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_long, long.class, style, extractor::buildLongSetter).test((target) -> {
                long newLong = -9527;

                target.set(newLong);

                FieldLongGetter getter = staticExtractor.buildLongGetter(static_long);
                Assertions.assertThat(getter.get()).isEqualTo(newLong);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_long, long.class, style, extractor::buildLongSetter).test((target) -> {
                long newLong = -9527;

                target.set(newLong);

                FieldLongGetter getter = instanceExtractor.buildLongGetter(instance_long);
                Assertions.assertThat(getter.get()).isEqualTo(newLong);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildLongSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildLongSetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Long, long.class, Long.class);

            errorTester.fieldIsFinal(static_final_long);
            errorTester.fieldIsFinal(instance_final_long);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_long);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildLongOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_long, long.class, style, extractor::buildLongOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_g2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_long, long.class, style, extractor::buildLongOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_g4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildLongOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildLongOperator);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Long, long.class, Long.class);

            errorTester.fieldIsFinal(static_final_long);
            errorTester.fieldIsFinal(instance_final_long);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_long);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    //

    @Test
    void buildShortGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_short, short.class, style, extractor::buildShortGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_h1);
            });

            new NormalTester<>(static_short, short.class, style, extractor::buildShortGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_h2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_short, short.class, style, extractor::buildShortGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_h3);
            });

            new NormalTester<>(instance_short, short.class, style, extractor::buildShortGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_h4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildShortGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildShortGetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Short, short.class, Short.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_final_short);
                errorTester.withoutInstance(instance_short);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildShortSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_short, short.class, style, extractor::buildShortSetter).test((target) -> {
                short nerShort = -9527;

                target.set(nerShort);

                FieldShortGetter getter = staticExtractor.buildShortGetter(static_short);
                Assertions.assertThat(getter.get()).isEqualTo(nerShort);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_short, short.class, style, extractor::buildShortSetter).test((target) -> {
                short newShort = -9527;

                target.set(newShort);

                FieldShortGetter getter = instanceExtractor.buildShortGetter(instance_short);
                Assertions.assertThat(getter.get()).isEqualTo(newShort);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildShortSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildShortSetter);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Short, short.class, Short.class);

            errorTester.fieldIsFinal(static_final_short);
            errorTester.fieldIsFinal(instance_final_short);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_short);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

    @Test
    void buildShortOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_short, short.class, style, extractor::buildShortOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_h2);
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_short, short.class, style, extractor::buildShortOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(DEFAULT_h4);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildShortOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorPrimitiveTester errorTester = new ErrorPrimitiveTester(extractor::buildShortOperator);

            errorTester.noSuchField("kerker");

            errorTester.errorFieldType(static_final_Short, short.class, Short.class);

            errorTester.fieldIsFinal(static_final_short);
            errorTester.fieldIsFinal(instance_final_short);

            if (isStaticExtractor) {
                errorTester.withoutInstance(instance_short);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }

}
