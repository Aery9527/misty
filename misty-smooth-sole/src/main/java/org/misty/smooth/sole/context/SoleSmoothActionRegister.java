package org.misty.smooth.sole.context;

import org.misty.smooth.api.context.SmoothActionRegister;
import org.misty.smooth.api.service.SmoothService;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class SoleSmoothActionRegister implements SmoothActionRegister {

    @Override
    public void registerService(Collection<SmoothService> services) {

    }

    @Override
    public void registerExecutor(ExecutorService executor) {

    }

    @Override
    public void registerServiceResultProcessErrorHandler(Consumer<Throwable> errorHandler) {

    }
}
