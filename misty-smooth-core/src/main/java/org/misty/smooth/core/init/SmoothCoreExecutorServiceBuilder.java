package org.misty.smooth.core.init;

import org.misty.smooth.core.context.api.SmoothCoreEnvironment;

import java.util.concurrent.*;
import java.util.function.Function;

public class SmoothCoreExecutorServiceBuilder implements Function<SmoothCoreEnvironment, ExecutorService> {

    @Override
    public ExecutorService apply(SmoothCoreEnvironment smoothCoreEnvironment) {
        int corePoolSize = getCorePoolSize(smoothCoreEnvironment);
        int maximumPoolSize = getMaximumPoolSize(smoothCoreEnvironment);
        long keepAliveTime = getKeepAliveTime(smoothCoreEnvironment);
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = getWorkQueue(smoothCoreEnvironment);
        ThreadFactory threadFactory = getThreadFactory(smoothCoreEnvironment);
        RejectedExecutionHandler handler = getHandler(smoothCoreEnvironment);

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public int getCorePoolSize(SmoothCoreEnvironment smoothCoreEnvironment) {
        return 2;
    }

    public int getMaximumPoolSize(SmoothCoreEnvironment smoothCoreEnvironment) {
        return 1024;
    }

    public long getKeepAliveTime(SmoothCoreEnvironment smoothCoreEnvironment) {
        return 60;
    }

    public BlockingQueue<Runnable> getWorkQueue(SmoothCoreEnvironment smoothCoreEnvironment) {
        return new SynchronousQueue<>();
    }

    public ThreadFactory getThreadFactory(SmoothCoreEnvironment smoothCoreEnvironment) {
        return (runnable) -> {
            return null; // FIXME
        };
    }

    public RejectedExecutionHandler getHandler(SmoothCoreEnvironment smoothCoreEnvironment) {
        return new ThreadPoolExecutor.AbortPolicy();
    }

}
