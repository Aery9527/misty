package org.misty.util.reflect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.misty.util.fi.FiBiFunction;
import org.misty.util.fi.FiConsumer;

import java.math.BigDecimal;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class FieldExtractorTest {

    public static class TestTarget {
        public static final String static_final_String = "a";
        public static final BigDecimal static_final_BigDecimal = new BigDecimal("1");
        public static String static_String = "b";
        public static BigDecimal static_BigDecimal = new BigDecimal("2");
        public final String instance_final_String = "c";
        public final BigDecimal instance_final_BigDecimal = new BigDecimal("3");
        public String instance_String = "d";
        public BigDecimal instance_BigDecimal = new BigDecimal("4");
    }

    public static final String static_final_String = "static_final_String";
    public static final String static_final_BigDecimal = "static_final_BigDecimal";
    public static final String static_String = "static_String";
    public static final String static_BigDecimal = "static_BigDecimal";
    public static final String instance_final_String = "instance_final_String";
    public static final String instance_final_BigDecimal = "instance_final_BigDecimal";
    public static final String instance_String = "instance_String";
    public static final String instance_BigDecimal = "instance_BigDecimal";

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
        TestTarget.static_String = "b";
        TestTarget.static_BigDecimal = new BigDecimal("2");
    }

    @Test
    void buildObjectGetter$normal() {
        FieldExtractor classExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_final_String, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo("a");
            });

            new NormalTester<>(static_String, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo("b");
            });

            new NormalTester<>(static_final_BigDecimal, BigDecimal.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("1"));
            });
            new NormalTester<>(static_final_BigDecimal, Number.class, style, extractor::buildGetter).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("1"));
            });

            new NormalTester<>(static_BigDecimal, BigDecimal.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("2"));
            });
            new NormalTester<>(static_BigDecimal, Number.class, style, extractor::buildGetter).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("2"));
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_final_String, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo("c");
            });

            new NormalTester<>(instance_String, String.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo("d");
            });

            new NormalTester<>(instance_final_BigDecimal, BigDecimal.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("3"));
            });
            new NormalTester<>(instance_final_BigDecimal, Number.class, style, extractor::buildGetter).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("3"));
            });

            new NormalTester<>(instance_BigDecimal, BigDecimal.class, style, extractor::buildGetter).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("4"));
            });
            new NormalTester<>(instance_BigDecimal, Number.class, style, extractor::buildGetter).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("4"));
            });
        };

        staticTest.accept(classExtractor, FieldStyle.STATIC);

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
        FieldExtractor classExtractor = new FieldExtractor(TestTarget.class);
        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());

        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_String, String.class, style, extractor::buildSetter).test((target) -> {
                String newString = String.valueOf(System.currentTimeMillis());

                target.set(newString);

                FieldObjectGetter<String> getter = classExtractor.buildGetter(static_String, String.class);
                Assertions.assertThat(getter.get()).isEqualTo(newString);
            });

            new NormalTester<>(static_BigDecimal, BigDecimal.class, style, extractor::buildSetter).test((target) -> {
                BigDecimal newBigDecimal = new BigDecimal(System.currentTimeMillis());

                target.set(newBigDecimal);

                FieldObjectGetter<BigDecimal> getter = classExtractor.buildGetter(static_BigDecimal, BigDecimal.class);
                Assertions.assertThat(getter.get()).isEqualTo(newBigDecimal);
            });
            new NormalTester<>(static_BigDecimal, Number.class, style, extractor::buildSetter).testWithAssignable((target) -> {
                BigDecimal newBigDecimal = new BigDecimal(System.currentTimeMillis());

                target.set(newBigDecimal);

                FieldObjectGetter<Number> getter = classExtractor.buildGetter(static_BigDecimal, Number.class);
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

        staticTest.accept(classExtractor, FieldStyle.STATIC);

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
        BiConsumer<FieldExtractor, FieldStyle> staticTest = (extractor, style) -> {
            new NormalTester<>(static_String, String.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo("b");
            });

            new NormalTester<>(static_BigDecimal, BigDecimal.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("2"));
            });
            new NormalTester<>(static_BigDecimal, Number.class, style, extractor::buildOperator).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("2"));
            });
        };

        BiConsumer<FieldExtractor, FieldStyle> instanceTest = (extractor, style) -> {
            new NormalTester<>(instance_String, String.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo("d");
            });

            new NormalTester<>(instance_BigDecimal, BigDecimal.class, style, extractor::buildOperator).test((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("4"));
            });
            new NormalTester<>(instance_BigDecimal, Number.class, style, extractor::buildOperator).testWithAssignable((target) -> {
                Assertions.assertThat(target.get()).isEqualTo(new BigDecimal("4"));
            });
        };

        FieldExtractor classExtractor = new FieldExtractor(TestTarget.class);
        staticTest.accept(classExtractor, FieldStyle.STATIC);

        FieldExtractor instanceExtractor = new FieldExtractor(new TestTarget());
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


}
