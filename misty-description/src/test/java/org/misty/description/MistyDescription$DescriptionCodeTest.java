package org.misty.description;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MistyDescription$DescriptionCodeTest {

    @Test
    public void test() {
        List<MistyDescription> list = MistyDescriptionFinder.findBySPI();
        list.forEach(System.out::println);
        Assertions.assertThat(list).contains(new MistyDescription$Description());
    }

}