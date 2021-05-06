package org.misty.util.reflect;

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

    static final String DEFAULT_a1 = "a";
    static final BigDecimal DEFAULT_a2 = new BigDecimal("1");
    static final String DEFAULT_a3 = "b";
    static final BigDecimal DEFAULT_a4 = new BigDecimal("2");
    static final String DEFAULT_a5 = "c";
    static final BigDecimal DEFAULT_a6 = new BigDecimal("3");
    static final String DEFAULT_a7 = "d";
    static final BigDecimal DEFAULT_a8 = new BigDecimal("4");

    static final byte DEFAULT_b1 = 1;
    static final byte DEFAULT_b2 = 2;
    static final byte DEFAULT_b3 = 3;
    static final byte DEFAULT_b4 = 4;

    public static class TestTarget {
        public static final String a1 = DEFAULT_a1;
        public static final BigDecimal a2 = DEFAULT_a2;
        public static String a3 = DEFAULT_a3;
        public static BigDecimal a4 = DEFAULT_a4;
        public final String a5 = DEFAULT_a5;
        public final BigDecimal a6 = DEFAULT_a6;
        public String a7 = DEFAULT_a7;
        public BigDecimal a8 = DEFAULT_a8;

        public static final byte b1 = DEFAULT_b1;
        public static byte b2 = DEFAULT_b2;
        public final byte b3 = DEFAULT_b3;
        public byte b4 = DEFAULT_b4;
    }

    public static final String static_final_String = "a1";
    public static final String static_final_BigDecimal = "a2";
    public static final String static_String = "a3";
    public static final String static_BigDecimal = "a4";
    public static final String instance_final_String = "a5";
    public static final String instance_final_BigDecimal = "a6";
    public static final String instance_String = "a7";
    public static final String instance_BigDecimal = "a8";

    public static final String static_final_byte = "b1";
    public static final String static_byte = "b2";
    public static final String instance_final_byte = "b3";
    public static final String instance_byte = "b4";

    public static class NormalTester<Target extends FieldObjectAbstract, FieldType> {
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
                Assertions.assertThat(target.getFieldType()).isEqualTo(this.fieldType);
                action.acceptOrHandle(target);
            });
        }

        public void testWithAssignable(FiConsumer<Target> action) {
            testCommon((target) -> {
                Assertions.assertThat(this.fieldType).isAssignableFrom(target.getFieldType());
                action.acceptOrHandle(target);
            });
        }

        private void testCommon(Consumer<Target> action) {
            Assertions.assertThat(this.target.getDeclaringClass()).isEqualTo(TestTarget.class);
            Assertions.assertThat(this.target.getName()).isEqualTo(this.name);
            Assertions.assertThat(this.target.getFieldStyle()).isEqualTo(this.fieldStyle);
            action.accept(this.target);
        }
    }

    public static class ErrorTester {
        private final FiBiFunction<String, Class<?>, FieldObjectAbstract> builder;

        public ErrorTester(FiBiFunction<String, Class<?>, FieldObjectAbstract> builder) {
            this.builder = builder;
        }

        public ErrorTester(FiFunction<String, FieldObjectAbstract> builder) {
            this.builder = (name, clazz) -> {
                FieldObjectAbstract target = builder.applyOrHandle(name);
                Class<?> fieldType = target.getFieldType();
                Assertions.assertThat(fieldType).isEqualTo(clazz);
                return target;
            };
        }

        public void noSuchField(Class<?> fieldType, String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name, fieldType))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(name);
        }

        public void withoutInstance(Class<?> fieldType, String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name, fieldType))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.ErrorMessage.OPERATING_INSTANCE_WITHOUT_TARGET, name));
        }

        public void errorFieldType(Class<?> fieldType, String name, Class<?> actuallyType) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name, fieldType))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.ErrorMessage.ERROR_FIELD_TYPE, name, actuallyType, fieldType));
        }

        public void fieldIsFinal(Class<?> fieldType, String name) {
            Assertions.assertThatThrownBy(() -> this.builder.applyOrHandle(name, fieldType))
                    .isInstanceOf(NoSuchFieldException.class)
                    .hasMessage(String.format(FieldExtractor.ErrorMessage.FIELD_IS_FINAL, name));
        }
    }

    @BeforeEach
    void setUp() {
        TestTarget.a3 = DEFAULT_a3;
        TestTarget.a4 = DEFAULT_a4;
    }

    @Test
    void buildObjectGetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

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

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildObjectGetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorTester errorTester = new ErrorTester(extractor::buildGetter);

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
    }

    @Test
    void buildObjectSetter$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

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

                FieldObjectGetter<String> getter = instanceExtractor.buildGetter(instance_String, String.class);
                Assertions.assertThat(getter.get()).isEqualTo(newString);
            });

            new NormalTester<>(instance_BigDecimal, BigDecimal.class, style, extractor::buildSetter).test((target) -> {
                BigDecimal newBigDecimal = new BigDecimal(System.currentTimeMillis());

                target.set(newBigDecimal);

                FieldObjectGetter<BigDecimal> getter = instanceExtractor.buildGetter(instance_BigDecimal, BigDecimal.class);
                Assertions.assertThat(getter.get()).isEqualTo(newBigDecimal);
            });
            new NormalTester<>(instance_BigDecimal, Number.class, style, extractor::buildSetter).testWithAssignable((target) -> {
                BigDecimal newBigDecimal = new BigDecimal(System.currentTimeMillis());

                target.set(newBigDecimal);

                FieldObjectGetter<Number> getter = instanceExtractor.buildGetter(instance_BigDecimal, Number.class);
                Assertions.assertThat(getter.get()).isEqualTo(newBigDecimal);
            });
        };

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildObjectSetter$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorTester errorTester = new ErrorTester(extractor::buildSetter);

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

    @Test
    void buildObjectOperator$normal() {
        FieldExtractor staticExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

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

        staticTest.accept(staticExtractor, FieldStyle.STATIC);

        staticTest.accept(instanceExtractor, FieldStyle.STATIC); // test instanceExtractor can use static field
        instanceTest.accept(instanceExtractor, FieldStyle.INSTANCE);
    }

    @Test
    void buildObjectOperator$error() {
        BiConsumer<FieldExtractor, Boolean> test = (extractor, isStaticExtractor) -> {
            ErrorTester errorTester = new ErrorTester(extractor::buildOperator);

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
            ErrorTester errorTester = new ErrorTester(extractor::buildByteGetter);

            errorTester.noSuchField(byte.class, "kerker");
            errorTester.noSuchField(Byte.class, static_final_byte);

            errorTester.errorFieldType(Double.class, static_final_byte, byte.class);

            if (isStaticExtractor) {
                errorTester.withoutInstance(byte.class, instance_final_byte);
                errorTester.withoutInstance(byte.class, instance_byte);
            }
        };

        test.accept(new FieldExtractor(TestTarget.class), true);
        test.accept(new FieldExtractor(new TestTarget()), false);
    }


}
