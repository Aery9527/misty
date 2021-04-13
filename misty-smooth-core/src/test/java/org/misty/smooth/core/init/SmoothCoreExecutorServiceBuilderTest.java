package org.misty.smooth.core.init;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.misty.smooth.core.constant.ThreadPoolArgument;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.context.impl.SmoothCoreEnvironmentPreset;
import org.misty.util.error.MistyException;
import org.misty.util.verify.ExamineIntervals;
import org.misty.util.verify.ExaminerMessage;

import java.util.concurrent.ThreadFactory;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

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
        int size = ThreadPoolArgument.CoreSize.PRESET;
        Assertions.assertThat(this.builder.getCorePoolSize(this.environment)).isEqualTo(size);

        size = ThreadPoolArgument.CoreSize.MIX;
        this.environment.addArgument(ThreadPoolArgument.CoreSize.KEY, size + "");
        Assertions.assertThat(this.builder.getCorePoolSize(this.environment)).isEqualTo(size);

        size = ThreadPoolArgument.CoreSize.MAX;
        this.environment.addArgument(ThreadPoolArgument.CoreSize.KEY, size + "");
        Assertions.assertThat(this.builder.getCorePoolSize(this.environment)).isEqualTo(size);

        IntFunction<String> invalidMsg = (number) -> ExaminerMessage.requireInRange(ThreadPoolArgument.CoreSize.KEY, number,
                ExamineIntervals.Floor.INCLUDE, ThreadPoolArgument.CoreSize.MIX,
                ExamineIntervals.Ceiling.INCLUDE, ThreadPoolArgument.CoreSize.MAX);

        size = ThreadPoolArgument.CoreSize.MIX - 1;
        this.environment.addArgument(ThreadPoolArgument.CoreSize.KEY, size + "");
        Assertions.assertThatThrownBy(() -> this.builder.getCorePoolSize(this.environment))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(invalidMsg.apply(size));

        size = ThreadPoolArgument.CoreSize.MAX + 1;
        this.environment.addArgument(ThreadPoolArgument.CoreSize.KEY, size + "");
        Assertions.assertThatThrownBy(() -> this.builder.getCorePoolSize(this.environment))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(invalidMsg.apply(size));
    }

    @Test
    public void getMaximumPoolSize() {
        int size = ThreadPoolArgument.MaxSize.PRESET;
        Assertions.assertThat(this.builder.getMaximumPoolSize(this.environment)).isEqualTo(size);

        size = ThreadPoolArgument.MaxSize.MIX;
        this.environment.addArgument(ThreadPoolArgument.MaxSize.KEY, size + "");
        Assertions.assertThat(this.builder.getMaximumPoolSize(this.environment)).isEqualTo(size);

        size = ThreadPoolArgument.MaxSize.MAX;
        this.environment.addArgument(ThreadPoolArgument.MaxSize.KEY, size + "");
        Assertions.assertThat(this.builder.getMaximumPoolSize(this.environment)).isEqualTo(size);

        IntFunction<String> invalidMsg = (number) -> ExaminerMessage.requireInRange(ThreadPoolArgument.MaxSize.KEY, number,
                ExamineIntervals.Floor.INCLUDE, ThreadPoolArgument.MaxSize.MIX,
                ExamineIntervals.Ceiling.INCLUDE, ThreadPoolArgument.MaxSize.MAX);

        size = ThreadPoolArgument.MaxSize.MIX - 1;
        this.environment.addArgument(ThreadPoolArgument.MaxSize.KEY, size + "");
        Assertions.assertThatThrownBy(() -> this.builder.getMaximumPoolSize(this.environment))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(invalidMsg.apply(size));

        size = ThreadPoolArgument.MaxSize.MAX + 1;
        this.environment.addArgument(ThreadPoolArgument.MaxSize.KEY, size + "");
        Assertions.assertThatThrownBy(() -> this.builder.getMaximumPoolSize(this.environment))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(invalidMsg.apply(size));
    }

    @Test
    public void getKeepAliveTime() {
        int size = ThreadPoolArgument.AliveSecond.PRESET;
        Assertions.assertThat(this.builder.getKeepAliveTime(this.environment)).isEqualTo(size);

        size = ThreadPoolArgument.AliveSecond.MIX;
        this.environment.addArgument(ThreadPoolArgument.AliveSecond.KEY, size + "");
        Assertions.assertThat(this.builder.getKeepAliveTime(this.environment)).isEqualTo(size);

        size = ThreadPoolArgument.AliveSecond.MAX;
        this.environment.addArgument(ThreadPoolArgument.AliveSecond.KEY, size + "");
        Assertions.assertThat(this.builder.getKeepAliveTime(this.environment)).isEqualTo(size);

        IntFunction<String> invalidMsg = (number) -> ExaminerMessage.requireInRange(ThreadPoolArgument.AliveSecond.KEY, number,
                ExamineIntervals.Floor.INCLUDE, ThreadPoolArgument.AliveSecond.MIX,
                ExamineIntervals.Ceiling.INCLUDE, ThreadPoolArgument.AliveSecond.MAX);

        size = ThreadPoolArgument.AliveSecond.MIX - 1;
        this.environment.addArgument(ThreadPoolArgument.AliveSecond.KEY, size + "");
        Assertions.assertThatThrownBy(() -> this.builder.getKeepAliveTime(this.environment))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(invalidMsg.apply(size));

        size = ThreadPoolArgument.AliveSecond.MAX + 1;
        this.environment.addArgument(ThreadPoolArgument.AliveSecond.KEY, size + "");
        Assertions.assertThatThrownBy(() -> this.builder.getKeepAliveTime(this.environment))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(invalidMsg.apply(size));
    }

    @Test
    public void getThreadFactory_NamePrefix() {
        Runnable r = () -> {
        };

        String namePrefix = ThreadPoolArgument.NamePrefix.PRESET;
        ThreadFactory threadFactory = this.builder.getThreadFactory(this.environment);
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0001");
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0002");

        namePrefix = "kerker";
        this.environment.addArgument(ThreadPoolArgument.NamePrefix.KEY, namePrefix);
        threadFactory = this.builder.getThreadFactory(this.environment);
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0001");
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0002");
    }

    @Test
    public void getThreadFactory_Rotation() {
        Runnable r = () -> {
        };

        this.environment.addArgument(ThreadPoolArgument.Rotation.KEY, "3");
        String namePrefix = ThreadPoolArgument.NamePrefix.PRESET;
        ThreadFactory threadFactory = this.builder.getThreadFactory(this.environment);
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0001");
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0002");
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0003");
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0001");
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0002");
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0003");
        Assertions.assertThat(threadFactory.newThread(r).getName()).isEqualTo(namePrefix + "0001");
    }

}