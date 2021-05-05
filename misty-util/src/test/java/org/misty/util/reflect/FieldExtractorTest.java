package org.misty.util.reflect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class FieldExtractorTest {

    public static class TestTarget {
        private static final String sfo1 = "a";
        private static String so1 = "b";
        private final String ifo1 = "c";
        private String io1 = "d";

        private static final BigDecimal sfo2 = new BigDecimal("1");
        private static BigDecimal so2 = new BigDecimal("2");
        private final BigDecimal ifo2 = new BigDecimal("3");
        private BigDecimal io2 = new BigDecimal("4");
    }

    @Test
    void buildObjectOperator_static_object$normal() throws NoSuchFieldException {
        FieldExtractor fieldExtractor = new FieldExtractor(TestTarget.class);

        String name1 = "so1";
        FieldObjectOperator<String> fieldObjectOperator1 = fieldExtractor.buildOperator(name1, String.class);
        Assertions.assertThat(fieldObjectOperator1.get()).isEqualTo("b");
        Assertions.assertThat(fieldObjectOperator1.getName()).isEqualTo(name1);
        Assertions.assertThat(fieldObjectOperator1.getDeclaringClass()).isEqualTo(TestTarget.class);
        Assertions.assertThat(fieldObjectOperator1.getType()).isEqualTo(String.class);
        Assertions.assertThat(fieldObjectOperator1.getStyle()).isEqualTo(FieldExtractorStyle.STATIC);

        String name2 = "so2";
        FieldObjectOperator<BigDecimal> fieldObjectOperator2 = fieldExtractor.buildOperator(name2, BigDecimal.class);
        Assertions.assertThat(fieldObjectOperator2.get()).isEqualTo(new BigDecimal("2"));
        Assertions.assertThat(fieldObjectOperator2.getName()).isEqualTo(name2);
        Assertions.assertThat(fieldObjectOperator2.getDeclaringClass()).isEqualTo(TestTarget.class);
        Assertions.assertThat(fieldObjectOperator2.getType()).isEqualTo(BigDecimal.class);
        Assertions.assertThat(fieldObjectOperator2.getStyle()).isEqualTo(FieldExtractorStyle.STATIC);

        FieldObjectOperator<Number> fieldObjectOperator3 = fieldExtractor.buildOperator(name2, Number.class);
        Assertions.assertThat(Number.class).isAssignableFrom(fieldObjectOperator3.getType());
    }

    @Test
    void buildObjectOperator_static_object$error() {
        FieldExtractor fieldExtractor = new FieldExtractor(TestTarget.class);

        String name1 = "kerker";
        Assertions.assertThatThrownBy(() -> fieldExtractor.buildOperator(name1, String.class))
                .isInstanceOf(NoSuchFieldException.class)
                .hasMessage(name1);

        Assertions.assertThatThrownBy(() -> fieldExtractor.buildOperator("ifo1", String.class))
                .isInstanceOf(NoSuchFieldException.class)
                .hasMessage(FieldExtractor.ErrorMessage.OPERATING_INSTANCE_WITHOUT_TARGET);

        String name2 = "so1";
        Assertions.assertThatThrownBy(() -> fieldExtractor.buildOperator(name2, Double.class))
                .isInstanceOf(NoSuchFieldException.class)
                .hasMessage(String.format(FieldExtractor.ErrorMessage.ERROR_FIELD_TYPE, name2, String.class, Double.class));

        String name3 = "sfo1";
        Assertions.assertThatThrownBy(() -> fieldExtractor.buildOperator(name3, String.class))
                .isInstanceOf(NoSuchFieldException.class)
                .hasMessage(String.format(FieldExtractor.ErrorMessage.FIELD_IS_FINAL, name3));
    }

    @Test
    void buildObjectOperator_instance_object$normal() throws NoSuchFieldException {
        FieldExtractor fieldExtractor = new FieldExtractor(new TestTarget());

        String name1 = "so1";
        FieldObjectOperator<String> fieldObjectOperator1 = fieldExtractor.buildOperator(name1, String.class);
        Assertions.assertThat(fieldObjectOperator1.get()).isEqualTo("b");
        Assertions.assertThat(fieldObjectOperator1.getName()).isEqualTo(name1);
        Assertions.assertThat(fieldObjectOperator1.getDeclaringClass()).isEqualTo(TestTarget.class);
        Assertions.assertThat(fieldObjectOperator1.getType()).isEqualTo(String.class);
        Assertions.assertThat(fieldObjectOperator1.getStyle()).isEqualTo(FieldExtractorStyle.STATIC);

        String name2 = "so2";
        FieldObjectOperator<BigDecimal> fieldObjectOperator2 = fieldExtractor.buildOperator(name2, BigDecimal.class);
        Assertions.assertThat(fieldObjectOperator2.get()).isEqualTo(new BigDecimal("2"));
        Assertions.assertThat(fieldObjectOperator2.getName()).isEqualTo(name2);
        Assertions.assertThat(fieldObjectOperator2.getDeclaringClass()).isEqualTo(TestTarget.class);
        Assertions.assertThat(fieldObjectOperator2.getType()).isEqualTo(BigDecimal.class);
        Assertions.assertThat(fieldObjectOperator2.getStyle()).isEqualTo(FieldExtractorStyle.STATIC);

        FieldObjectOperator<Number> fieldObjectOperator21 = fieldExtractor.buildOperator(name2, Number.class);
        Assertions.assertThat(Number.class).isAssignableFrom(fieldObjectOperator21.getType());

        String name3 = "io1";
        FieldObjectOperator<String> fieldObjectOperator3 = fieldExtractor.buildOperator(name3, String.class);
        Assertions.assertThat(fieldObjectOperator3.get()).isEqualTo("d");
        Assertions.assertThat(fieldObjectOperator3.getName()).isEqualTo(name3);
        Assertions.assertThat(fieldObjectOperator3.getDeclaringClass()).isEqualTo(TestTarget.class);
        Assertions.assertThat(fieldObjectOperator3.getType()).isEqualTo(String.class);
        Assertions.assertThat(fieldObjectOperator3.getStyle()).isEqualTo(FieldExtractorStyle.INSTANCE);

        String name4 = "io2";
        FieldObjectOperator<BigDecimal> fieldObjectOperator4 = fieldExtractor.buildOperator(name4, BigDecimal.class);
        Assertions.assertThat(fieldObjectOperator4.get()).isEqualTo(new BigDecimal("4"));
        Assertions.assertThat(fieldObjectOperator4.getName()).isEqualTo(name4);
        Assertions.assertThat(fieldObjectOperator4.getDeclaringClass()).isEqualTo(TestTarget.class);
        Assertions.assertThat(fieldObjectOperator4.getType()).isEqualTo(BigDecimal.class);
        Assertions.assertThat(fieldObjectOperator4.getStyle()).isEqualTo(FieldExtractorStyle.INSTANCE);

        FieldObjectOperator<Number> fieldObjectOperator41 = fieldExtractor.buildOperator(name4, Number.class);
        Assertions.assertThat(Number.class).isAssignableFrom(fieldObjectOperator41.getType());
    }

    @Test
    void buildObjectOperator_instance_object$error() {

    }

}
