package org.misty.smooth.api.passable;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SmoothPassableFunctionalTest {

    static class TestException extends Exception {
    }

    @Test
    void SmoothPassableRunnable() {
        Assertions.assertThatThrownBy(() -> {
            SmoothPassableRunnable runnable = () -> {
                throw new TestException();
            };
            SmoothPassableFunctional.wrap(runnable);
        }).isInstanceOf(TestException.class);
    }

    @Test
    void testWrap() {
        Assertions.assertThatThrownBy(() -> {
            SmoothPassableSupplier<String> supplier = () -> {
                throw new TestException();
            };
            SmoothPassableFunctional.wrap(supplier);
        }).isInstanceOf(TestException.class);
    }
}
