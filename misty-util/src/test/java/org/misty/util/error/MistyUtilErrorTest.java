package org.misty.util.error;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MistyUtilErrorTest {

    @Test
    public void test() {
//        try {
//            MistyError.UNHANDLED.thrown();
//        } catch (MistyException me) {
//            Assertions.assertThat(MistyUtilError.UNHANDLED.isSame(me)).isEqualTo(true);
//            Assertions.assertThat(MistyUtilError.UNHANDLED.isSame(me.getErrorDefinition())).isEqualTo(true);
//            Assertions.assertThat(MistyUtilError.UNHANDLED.isSameType(me)).isEqualTo(true);
//            Assertions.assertThat(MistyUtilError.UNHANDLED.isSameType(me.getErrorDefinition())).isEqualTo(true);
//
//            Assertions.assertThat(MistyUtilError.ARGUMENT_ERROR.isSame(me)).isEqualTo(false);
//            Assertions.assertThat(MistyUtilError.ARGUMENT_ERROR.isSame(me.getErrorDefinition())).isEqualTo(false);
//            Assertions.assertThat(MistyUtilError.ARGUMENT_ERROR.isSameType(me)).isEqualTo(true);
//            Assertions.assertThat(MistyUtilError.ARGUMENT_ERROR.isSameType(me.getErrorDefinition())).isEqualTo(true);
//
//            Assertions.assertThat(TestError.UNHANDLED.isSame(me)).isEqualTo(false);
//            Assertions.assertThat(TestError.UNHANDLED.isSame(me.getErrorDefinition())).isEqualTo(false);
//            Assertions.assertThat(TestError.UNHANDLED.isSameType(me)).isEqualTo(false);
//            Assertions.assertThat(TestError.UNHANDLED.isSameType(me.getErrorDefinition())).isEqualTo(false);
//        }
//
//        try {
//            MistyUtilError.ARGUMENT_ERROR.thrown();
//        } catch (Throwable t) {
//            Assertions.assertThat(MistyUtilError.ARGUMENT_ERROR.isSame(t)).isEqualTo(true);
//            Assertions.assertThat(MistyUtilError.ARGUMENT_ERROR.isSameType(t)).isEqualTo(true);
//        }
    }

//    public enum TestError implements MistyErrorDefinition {
//        UNHANDLED; // 故意設為一樣, 用以區別當type都相同時的狀況
//
//        @Override
//        public String getType() {
//            return MistyUtilError.UNHANDLED.getType(); // 故意設為一樣, 用以區別當type都相同時的狀況
//        }
//
//        @Override
//        public String getCode() {
//            return String.format("%02d", ordinal());
//        }
//    }

}
