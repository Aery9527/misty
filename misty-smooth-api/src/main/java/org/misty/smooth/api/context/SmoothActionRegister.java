package org.misty.smooth.api.context;

import org.misty.smooth.api.service.SmoothService;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface SmoothActionRegister {

    default void registerService(SmoothService... services) {
        registerService(Arrays.asList(services));
    }

    void registerService(Collection<SmoothService> services);

    void registerExecutor(Executor executor);

    void registerServiceResultProcessErrorHandler(Consumer<Throwable> errorHandler);

}
