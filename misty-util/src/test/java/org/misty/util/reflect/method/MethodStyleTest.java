package org.misty.util.reflect.method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MethodStyleTest {

    @Test
    void isStatic() {
        Assertions.assertThat(MethodStyle.STATIC.isStatic()).isTrue();
    }

    @Test
    void isInstance() {
        Assertions.assertThat(MethodStyle.INSTANCE.isInstance()).isTrue();
    }

    @Test
    void isAncestor() {
        Assertions.assertThat(MethodStyle.ANCESTOR.isAncestor()).isTrue();
    }
}
