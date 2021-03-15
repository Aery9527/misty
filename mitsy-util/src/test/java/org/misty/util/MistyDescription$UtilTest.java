package org.misty.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.description.MistyDescription$DescriptionCode;
import org.misty.description.core.MistyDescription;
import org.misty.description.core.MistyDescriptionFinder;

import java.util.List;

class MistyDescription$UtilTest {

    @Test
    public void test() {
        List<MistyDescription> list = MistyDescriptionFinder.findBySPI();

        MistyDescription$Util main = new MistyDescription$Util();
        Assertions.assertThat(list).contains(main);
    }

}