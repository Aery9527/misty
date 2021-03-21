package org.misty.smooth.sole;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.description.MistyDescription;
import org.misty.description.MistyDescriptionFinder;

import java.util.List;

class MistyDescription$SmoothSoleTest {

    @Test
    public void test() {
        List<MistyDescription> list = MistyDescriptionFinder.findBySPI();
        list.forEach(System.out::println);
        Assertions.assertThat(list).contains(new MistyDescription$SmoothSole());
    }

}