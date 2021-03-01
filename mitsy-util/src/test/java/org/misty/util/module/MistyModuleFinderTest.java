package org.misty.util.module;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.util._test.MistyModule1;
import org.misty.util._test.MistyModule2;

import java.util.List;

class MistyModuleFinderTest {

    @Test
    public void test() {
        List<MistyModule> mistyModules = MistyModuleFinder.findBySPI();

        MistyModule1 mistyModule1 = new MistyModule1();
        MistyModule2 mistyModule2 = new MistyModule2();
        Assertions.assertThat(mistyModules).contains(mistyModule1, mistyModule2);
    }

}