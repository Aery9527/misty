package org.misty.smooth.core.init;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.misty.smooth.core.constant.ThreadPoolArgument;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.context.impl.SmoothCoreEnvironmentPreset;

class SmoothCoreExecutorServiceBuilderTest {

    private SmoothCoreEnvironment environment = new SmoothCoreEnvironmentPreset();

    private SmoothCoreExecutorServiceBuilder builder = new SmoothCoreExecutorServiceBuilder();

    @BeforeEach
    void initialEnvironment() {
        this.environment = new SmoothCoreEnvironmentPreset();
        this.builder = new SmoothCoreExecutorServiceBuilder();
    }

    @Test
    public void getCorePoolSize() {
        Assertions.assertThat(this.builder.getCorePoolSize(this.environment))
                .isEqualTo(ThreadPoolArgument.CoreSize.PRESET);

        this.environment.addArgument(ThreadPoolArgument.CoreSize.KEY, "55");
        Assertions.assertThat(this.builder.getCorePoolSize(this.environment))
                .isEqualTo(55);


    }

}