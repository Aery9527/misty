package org.misty.util.tool.cycle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.ut.common.AssertionsTool;

public class CycleIntTest {

    @Test
    public void positive() {
        int start = 0;
        int limit = 3;

        CycleInt cycleInt1 = new CycleInt(start, limit);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(1);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(2);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(3);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(1);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(2);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(3);

        CycleInt cycleInt2 = new CycleInt(start, limit, 2);
        Assertions.assertThat(cycleInt2.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt2.getCurrentAndNext()).isEqualTo(2);
        Assertions.assertThat(cycleInt2.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt2.getCurrentAndNext()).isEqualTo(2);

        CycleInt cycleInt3 = new CycleInt(start, limit, 3);
        Assertions.assertThat(cycleInt3.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt3.getCurrentAndNext()).isEqualTo(3);
        Assertions.assertThat(cycleInt3.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt3.getCurrentAndNext()).isEqualTo(3);

        CycleInt cycleInt4 = new CycleInt(start, limit, 4);
        Assertions.assertThat(cycleInt4.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt4.getCurrentAndNext()).isEqualTo(0);
    }

    @Test
    public void negative() {
        int start = 0;
        int limit = -3;

        CycleInt cycleInt1 = new CycleInt(start, limit, -1);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(-1);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(-2);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(-3);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(-1);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(-2);
        Assertions.assertThat(cycleInt1.getCurrentAndNext()).isEqualTo(-3);

        CycleInt cycleInt2 = new CycleInt(start, limit, -2);
        Assertions.assertThat(cycleInt2.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt2.getCurrentAndNext()).isEqualTo(-2);
        Assertions.assertThat(cycleInt2.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt2.getCurrentAndNext()).isEqualTo(-2);

        CycleInt cycleInt3 = new CycleInt(start, limit, -3);
        Assertions.assertThat(cycleInt3.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt3.getCurrentAndNext()).isEqualTo(-3);
        Assertions.assertThat(cycleInt3.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt3.getCurrentAndNext()).isEqualTo(-3);

        CycleInt cycleInt4 = new CycleInt(start, limit, -4);
        Assertions.assertThat(cycleInt4.getCurrentAndNext()).isEqualTo(0);
        Assertions.assertThat(cycleInt4.getCurrentAndNext()).isEqualTo(0);
    }

    @Test
    public void error() {
        Assertions.assertThatThrownBy(AssertionsTool.wrap("error1", () -> new CycleInt(0, 3, -1)))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(AssertionsTool.wrap("error2", () -> new CycleInt(0, -3, 1)))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(AssertionsTool.wrap("error3", () -> new CycleInt(0, 3, 0)))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(AssertionsTool.wrap("error4", () -> new CycleInt(1, 1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
