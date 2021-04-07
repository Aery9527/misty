package org.misty.smooth.core.init;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.core.constant.ThreadPoolArgument;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;

import java.util.concurrent.*;
import java.util.function.Function;

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
        return smoothEnvironment.getValue(ThreadPoolArgument.CoreSize.KEY, ThreadPoolArgument.CoreSize.VERIFY_AND_TRANSFORM);
    }

    public int getMaximumPoolSize(SmoothEnvironment smoothEnvironment) {
        return 1024;
    }

    public long getKeepAliveTime(SmoothEnvironment smoothEnvironment) {
        return 60;
    }

    public BlockingQueue<Runnable> getWorkQueue(SmoothEnvironment smoothEnvironment) {
        return new SynchronousQueue<>();
    }

    public ThreadFactory getThreadFactory(SmoothEnvironment smoothEnvironment) {
        return (runnable) -> {
            return null; // FIXME
        };
    }

    public RejectedExecutionHandler getHandler(SmoothEnvironment smoothEnvironment) {
        return new ThreadPoolExecutor.AbortPolicy();
    }

}
