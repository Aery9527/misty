package org.misty.util.module;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.MistyUtilModule;
import org.misty.util._test.MistyModule1;
import org.misty.util._test.MistyModule2;

import java.util.List;

class MistyModuleFinderTest {

    @Test
    public void test() {
        List<MistyModule> mistyModules = MistyModuleFinder.findBySPI();

        MistyUtilModule mistyUtilModule = new MistyUtilModule(); // main裡的實作
        MistyModule1 mistyModule1 = new MistyModule1(); // test裡的實作
        MistyModule2 mistyModule2 = new MistyModule2(); // test裡的實作
        Assertions.assertThat(mistyModules).contains(mistyUtilModule, mistyModule1, mistyModule2);
    }

}