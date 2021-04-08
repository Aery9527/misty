package org.misty.smooth.core.init;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.core.constant.ThreadPoolArgument;
import org.misty.smooth.core.context.validator.EnvironmentIntegerValidator;
import org.misty.util.verify.Judge;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntSupplier;

public class SmoothCoreExecutorServiceBuilder implements Function<SmoothEnvironment, ExecutorService> {

    @Override
    public ExecutorService apply(SmoothEnvironment smoothEnvironment) {
        int corePoolSize = getCorePoolSize(smoothEnvironment);
        int maximumPoolSize = getMaximumPoolSize(smoothEnvironment);
        long keepAliveTime = getKeepAliveTime(smoothEnvironment);
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = getWorkQueue(smoothEnvironment);
        ThreadFactory threadFactory = getThreadFactory(smoothEnvironment);
        RejectedExecutionHandler handler = getHandler(smoothEnvironment);

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public int getCorePoolSize(SmoothEnvironment smoothEnvironment) {
        String key = ThreadPoolArgument.CoreSize.KEY;
        int preset = ThreadPoolArgument.CoreSize.PRESET;
        int min = ThreadPoolArgument.CoreSize.MIX;
        int max = ThreadPoolArgument.CoreSize.MAX;
        EnvironmentIntegerValidator validator = new EnvironmentIntegerValidator(key, preset, min, max);
        return smoothEnvironment.getValue(key, validator);
    }

    public int getMaximumPoolSize(SmoothEnvironment smoothEnvironment) {
        String key = ThreadPoolArgument.MaxSize.KEY;
        int preset = ThreadPoolArgument.MaxSize.PRESET;
        int min = ThreadPoolArgument.MaxSize.MIX;
        int max = ThreadPoolArgument.MaxSize.MAX;
        EnvironmentIntegerValidator validator = new EnvironmentIntegerValidator(key, preset, min, max);
        return smoothEnvironment.getValue(key, validator);
    }

    public long getKeepAliveTime(SmoothEnvironment smoothEnvironment) {
        String key = ThreadPoolArgument.AliveSecond.KEY;
        int preset = ThreadPoolArgument.AliveSecond.PRESET;
        int min = ThreadPoolArgument.AliveSecond.MIX;
        int max = ThreadPoolArgument.AliveSecond.MAX;
        EnvironmentIntegerValidator validator = new EnvironmentIntegerValidator(key, preset, min, max);
        return smoothEnvironment.getValue(key, validator);
    }

    public BlockingQueue<Runnable> getWorkQueue(SmoothEnvironment smoothEnvironment) {
        return new SynchronousQueue<>(true);
    }

    public ThreadFactory getThreadFactory(SmoothEnvironment smoothEnvironment) {
        String namePrefix = smoothEnvironment.getValue(ThreadPoolArgument.NamePrefix.KEY, (value) -> {
            if (Judge.isNullOrEmpty(value)) {
                return ThreadPoolArgument.NamePrefix.PRESET;
            } else {
                return value;
            }
        });

        AtomicInteger count = new AtomicInteger(1);

        IntSupplier counter = () -> {
            int c = count.getAndIncrement();
            if (c > 8192) {
                count.set(1);
                return 1;
            } else {
                return c;
            }
        };

        return (runnable) -> new Thread(runnable, namePrefix + counter.getAsInt());
    }

    public RejectedExecutionHandler getHandler(SmoothEnvironment smoothEnvironment) {
        return new ThreadPoolExecutor.AbortPolicy();
    }

}
