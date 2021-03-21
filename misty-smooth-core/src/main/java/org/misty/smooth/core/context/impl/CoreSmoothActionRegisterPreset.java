package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.error.SmoothActionRegisterException;
import org.misty.smooth.api.service.SmoothService;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.CoreSmoothActionRegister;
import org.misty.util.ex.ReentrantLockEx;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class CoreSmoothActionRegisterPreset implements CoreSmoothActionRegister {

    private final ReentrantLockEx lock = new ReentrantLockEx();

    private volatile boolean closed = false;

    private final Map<SmoothServiceId, SmoothService> registeredService = new HashMap<>();

    @Override
    public void registerService(Collection<SmoothService> services) throws SmoothActionRegisterException {
        checkClose();
        this.lock.use(() -> {

        });
    }


    @Override
    public void registerExecutor(ExecutorService executor) throws SmoothActionRegisterException {
        checkClose();
        this.lock.use(() -> {

        });
    }

    @Override
    public void registerServiceResultProcessErrorHandler(Consumer<Throwable> errorHandler) throws SmoothActionRegisterException {
        checkClose();
        this.lock.use(() -> {

        });
    }

    @Override
    public void close() throws IOException {
        this.closed = true;
    }

    private void checkClose() {
        if (this.closed) {
            throw new SmoothActionRegisterException("register is closed.");
        }
    }

}
