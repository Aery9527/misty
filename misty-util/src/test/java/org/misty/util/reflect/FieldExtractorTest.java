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
        Assertions.assertThat(fieldObjectOperator1.getStyle()).isEqualTo(FieldExtractor.Style.STATIC);

        String name2 = "so2";
        FieldObjectOperator<BigDecimal> fieldObjectOperator2 = fieldExtractor.buildOperator(name2, BigDecimal.class);
        Assertions.assertThat(fieldObjectOperator2.get()).isEqualTo(new BigDecimal("2"));
        Assertions.assertThat(fieldObjectOperator2.getName()).isEqualTo(name2);
        Assertions.assertThat(fieldObjectOperator2.getDeclaringClass()).isEqualTo(TestTarget.class);
        Assertions.assertThat(fieldObjectOperator2.getType()).isEqualTo(BigDecimal.class);
        Assertions.assertThat(fieldObjectOperator2.getStyle()).isEqualTo(FieldExtractor.Style.STATIC);

        FieldObjectOperator<Number> fieldObjectOperator3 = fieldExtractor.buildOperator(name2, Number.class);
        Assertions.assertThat(Number.class).isAssignableFrom(fieldObjectOperator3.getType());
    }

    @Test
    void buildObjectOperator_static_object$error() {

    }

}
