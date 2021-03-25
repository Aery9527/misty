package org.misty.smooth.api.lifecycle.module;

import org.misty.smooth.api.error.SmoothActionRegisterException;
import org.misty.smooth.api.lifecycle.SmoothRegister;
import org.misty.smooth.api.service.SmoothService;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

public interface SmoothModuleRegister extends SmoothRegister {

    default void registerService(SmoothService... services) throws SmoothActionRegisterException {
        registerService(Arrays.asList(services));
    }

    void registerService(Collection<SmoothService> services) throws SmoothActionRegisterException;

    void registerServiceResultProcessErrorHandler(Consumer<Throwable> errorHandler) throws SmoothActionRegisterException;

}