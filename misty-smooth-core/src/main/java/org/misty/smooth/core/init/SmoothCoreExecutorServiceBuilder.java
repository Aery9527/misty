package org.misty.smooth.core.init;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.core.constant.ThreadPoolArgument;
import org.misty.smooth.core.context.validator.EnvironmentShortValidator;
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
        short preset = ThreadPoolArgument.CoreSize.PRESET;
        short min = ThreadPoolArgument.CoreSize.MIX;
        short max = ThreadPoolArgument.CoreSize.MAX;
        EnvironmentShortValidator validator = new EnvironmentShortValidator(key, preset, min, max);
        return smoothEnvironment.getValue(key, validator);
    }

    public int getMaximumPoolSize(SmoothEnvironment smoothEnvironment) {
        String key = ThreadPoolArgument.MaxSize.KEY;
        short preset = ThreadPoolArgument.MaxSize.PRESET;
        short min = ThreadPoolArgument.MaxSize.MIX;
        short max = ThreadPoolArgument.MaxSize.MAX;
        EnvironmentShortValidator validator = new EnvironmentShortValidator(key, preset, min, max);
        return smoothEnvironment.getValue(key, validator);
    }

    public long getKeepAliveTime(SmoothEnvironment smoothEnvironment) {
        String key = ThreadPoolArgument.AliveSecond.KEY;
        short preset = ThreadPoolArgument.AliveSecond.PRESET;
        short min = ThreadPoolArgument.AliveSecond.MIX;
        short max = ThreadPoolArgument.AliveSecond.MAX;
        EnvironmentShortValidator validator = new EnvironmentShortValidator(key, preset, min, max);
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

        String key = ThreadPoolArgument.Rotation.KEY;
        short preset = ThreadPoolArgument.Rotation.PRESET;
        short min = ThreadPoolArgument.Rotation.MIX;
        short max = ThreadPoolArgument.Rotation.MAX;
        EnvironmentShortValidator validator = new EnvironmentShortValidator(key, preset, min, max);
        short rotation = smoothEnvironment.getValue(key, validator);

        AtomicInteger count = new AtomicInteger(min);
        IntSupplier counter = () -> {
            int c = count.getAndIncrement();
            if (c <= rotation) {
                return c;
            }

            synchronized (count) {
                c = count.getAndIncrement();
                if (c <= rotation) {
                    return c;
                }
                count.set(min);
            }

            return count.getAndIncrement();
        };

        return (runnable) -> {
            String name = namePrefix + String.format("%04d", counter.getAsInt());
            return new Thread(runnable, name);
        };
    }

    public RejectedExecutionHandler getHandler(SmoothEnvironment smoothEnvironment) {
        return new ThreadPoolExecutor.AbortPolicy();
    }

}
