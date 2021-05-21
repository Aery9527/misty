package org.misty.util.reflect.field;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FieldStyleTest {

    @Test
    void isStatic() {
        Assertions.assertThat(FieldStyle.STATIC.isStatic()).isTrue();
    }

    @Test
    void isInstance() {
        Assertions.assertThat(FieldStyle.INSTANCE.isInstance()).isTrue();
    }

    @Test
    void isAncestor() {
        Assertions.assertThat(FieldStyle.ANCESTOR.isAncestor()).isTrue();
    }

}
