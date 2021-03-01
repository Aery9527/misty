package org.misty.util.error;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MistyErrorTest {

    @Test
    public void test() {
        try {
            MistyError.UNHANDLED.thrown();
        } catch (MistyException me) {
            Assertions.assertThat(MistyError.UNHANDLED.isSame(me)).isEqualTo(true);
            Assertions.assertThat(MistyError.UNHANDLED.isSame(me.getErrorDefinition())).isEqualTo(true);
            Assertions.assertThat(MistyError.UNHANDLED.isSameType(me)).isEqualTo(true);
            Assertions.assertThat(MistyError.UNHANDLED.isSameType(me.getErrorDefinition())).isEqualTo(true);

            Assertions.assertThat(MistyError.ARGUMENT_ERROR.isSame(me)).isEqualTo(false);
            Assertions.assertThat(MistyError.ARGUMENT_ERROR.isSame(me.getErrorDefinition())).isEqualTo(false);
            Assertions.assertThat(MistyError.ARGUMENT_ERROR.isSameType(me)).isEqualTo(true);
            Assertions.assertThat(MistyError.ARGUMENT_ERROR.isSameType(me.getErrorDefinition())).isEqualTo(true);

            Assertions.assertThat(TestError.UNHANDLED.isSame(me)).isEqualTo(false);
            Assertions.assertThat(TestError.UNHANDLED.isSame(me.getErrorDefinition())).isEqualTo(false);
            Assertions.assertThat(TestError.UNHANDLED.isSameType(me)).isEqualTo(false);
            Assertions.assertThat(TestError.UNHANDLED.isSameType(me.getErrorDefinition())).isEqualTo(false);
        }
    }

    public enum TestError implements MistyErrorDefinition {
        UNHANDLED; // 故意設為一樣, 用以區別當type都相同時的狀況

        @Override
        public String getType() {
            return MistyError.UNHANDLED.getType(); // 故意設為一樣, 用以區別當type都相同時的狀況
        }

        @Override
        public String getCode() {
            return String.format("%02d", ordinal());
        }
    }

}