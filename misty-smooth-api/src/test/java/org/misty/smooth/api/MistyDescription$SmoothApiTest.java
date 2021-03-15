package org.misty.smooth.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.description.core.MistyDescription;
import org.misty.description.core.MistyDescriptionFinder;

import java.util.List;

class MistyDescription$SmoothApiTest {

    @Test
    public void test() {
        List<MistyDescription> list = MistyDescriptionFinder.findBySPI();

        MistyDescription$SmoothApi main = new MistyDescription$SmoothApi();
        Assertions.assertThat(list).contains(main);
    }

}