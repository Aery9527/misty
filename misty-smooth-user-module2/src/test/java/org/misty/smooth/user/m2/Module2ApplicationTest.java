package org.misty.smooth.user.m2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class Module2ApplicationTest {

    @Test
    public void test() {
        ServiceLoader<SmoothModuleLifecycle> serviceLoader = ServiceLoader.load(SmoothModuleLifecycle.class);
        List<SmoothModuleLifecycle> list = StreamSupport.stream(serviceLoader.spliterator(), false)
                .collect(Collectors.toCollection(ArrayList::new));

        SmoothModuleLifecycle lifecycle = new Module2Application();
        Assertions.assertThat(list).hasSize(1);
        Assertions.assertThat(list.get(0).getName()).isEqualTo(lifecycle.getName());
        Assertions.assertThat(list.get(0).getVersion()).isEqualTo(lifecycle.getVersion());
    }

}