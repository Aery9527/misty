package org.misty.description;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.description.core.MistyDescription;
import org.misty.description.core.MistyDescriptionFinder;

import java.util.List;

class MistyDescription$DescriptionCodeTest {

    @Test
    public void test() {
        List<MistyDescription> list = MistyDescriptionFinder.findBySPI();

        MistyDescription$Description main = new MistyDescription$Description();
        Assertions.assertThat(list).contains(main);
    }

}