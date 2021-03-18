package org.misty.smooth.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.description.MistyDescription$Description;
import org.misty.description.core.MistyDescription;
import org.misty.description.core.MistyDescriptionFinder;
import org.misty.smooth.api.MistyDescription$SmoothApi;
import org.misty.util.MistyDescription$Util;

import java.util.List;

class MistyDescription$SmoothCoreTest {

    @Test
    public void test() {
        List<MistyDescription> list = MistyDescriptionFinder.findBySPI();
        list.forEach(System.out::println);
        Assertions.assertThat(list).contains(new MistyDescription$SmoothCore());
    }

}