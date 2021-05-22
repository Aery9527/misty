package org.misty.smooth.sole.context;

import org.misty.smooth.api.error.SmoothActionRegisterException;
import org.misty.smooth.api.lifecycle.module.SmoothModuleRegister;
import org.misty.smooth.api.service.SmoothService;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;

public class SmoothSoleRegister implements SmoothModuleRegister {

    @Override
    public void registerService(Collection<SmoothService> services) {

    }

    @Override
    public void registerExecutor(ExecutorService executor) {

    }

    @Override
    public void registerResponseResultProcessErrorHandler(BiConsumer<SmoothService, Throwable> errorHandler) throws SmoothActionRegisterException {

    }
}
