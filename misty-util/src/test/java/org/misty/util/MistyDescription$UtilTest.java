package org.misty.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.description.MistyDescription;
import org.misty.description.MistyDescriptionFinder;

import java.util.List;

class MistyDescription$UtilTest {

    @Test
    public void test() {
        List<MistyDescription> list = MistyDescriptionFinder.findBySPI();
        list.forEach(System.out::println);
        Assertions.assertThat(list).contains(new MistyDescription$Util());
    }

}