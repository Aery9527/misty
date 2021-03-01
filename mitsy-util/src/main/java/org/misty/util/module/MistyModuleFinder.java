package org.misty.util.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

public class MistyModuleFinder {

    public static List<MistyModule> findBySPI() {
        ServiceLoader<MistyModule> mistyModuleServiceLoader = ServiceLoader.load(MistyModule.class);

        List<MistyModule> list = new ArrayList<>();
        mistyModuleServiceLoader.forEach(list::add);
        return Collections.unmodifiableList(list);
    }

}
