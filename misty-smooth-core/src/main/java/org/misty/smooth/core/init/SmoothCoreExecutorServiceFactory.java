package org.misty.smooth.core.init;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.core.constant.ThreadPoolArgument;
import org.misty.smooth.core.context.validator.EnvironmentShortValidator;
import org.misty.smooth.core.error.SmoothCoreError;
import org.misty.util.verify.Examiner;
import org.misty.util.verify.Judge;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntSupplier;

public class SmoothCoreExecutorServiceFactory implements Function<SmoothEnvironment, ExecutorService> {

    private final ClassLoader executeClassLoader;

    public SmoothCoreExecutorServiceFactory() {
        this(Thread.currentThread().getContextClassLoader());
    }

    public SmoothCoreExecutorServiceFactory(ClassLoader executeClassLoader) {
        Examiner.refuseNullAndEmpty("executeClassLoader", executeClassLoader, SmoothCoreError.ARGUMENT_ERROR);
        this.executeClassLoader = executeClassLoader;
    }

    @Override
    public ExecutorService apply(SmoothEnvironment smoothEnvironment) {
        short corePoolSize = getCorePoolSize(smoothEnvironment);
        short maximumPoolSize = getMaximumPoolSize(smoothEnvironment);
        short keepAliveTime = getKeepAliveTime(smoothEnvironment);
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = getWorkQueue(smoothEnvironment);
        ThreadFactory threadFactory = getThreadFactory(smoothEnvironment);
        RejectedExecutionHandler handler = getHandler(smoothEnvironment);

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public short getCorePoolSize(SmoothEnvironment smoothEnvironment) {
        String key = ThreadPoolArgument.CoreSize.KEY;
        short preset = ThreadPoolArgument.CoreSize.PRESET;
        short min = ThreadPoolArgument.CoreSize.MIX;
        short max = ThreadPoolArgument.CoreSize.MAX;
        return smoothEnvironment.getValue(key, new EnvironmentShortValidator(key, preset, min, max));
    }

    public short getMaximumPoolSize(SmoothEnvironment smoothEnvironment) {
        String key = ThreadPoolArgument.MaxSize.KEY;
        short preset = ThreadPoolArgument.MaxSize.PRESET;
        short min = ThreadPoolArgument.MaxSize.MIX;
        short max = ThreadPoolArgument.MaxSize.MAX;
        return smoothEnvironment.getValue(key, new EnvironmentShortValidator(key, preset, min, max));
    }

    public short getKeepAliveTime(SmoothEnvironment smoothEnvironment) {
        String key = ThreadPoolArgument.AliveSecond.KEY;
        short preset = ThreadPoolArgument.AliveSecond.PRESET;
        short min = ThreadPoolArgument.AliveSecond.MIX;
        short max = ThreadPoolArgument.AliveSecond.MAX;
        return smoothEnvironment.getValue(key, new EnvironmentShortValidator(key, preset, min, max));
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
        short rotation = smoothEnvironment.getValue(key, new EnvironmentShortValidator(key, preset, min, max));

        short first = 1;
        AtomicInteger count = new AtomicInteger(first); // FIXME 改用 CycleInt
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
                count.set(first);
            }

            return count.getAndIncrement();
        };

        return (runnable) -> {
            String name = namePrefix + String.format("%03d", counter.getAsInt());
            Thread thread = new Thread(runnable, name);
            thread.setContextClassLoader(this.executeClassLoader);
            return thread;
        };
    }

    public RejectedExecutionHandler getHandler(SmoothEnvironment smoothEnvironment) {
        return new ThreadPoolExecutor.AbortPolicy();
    }

}
