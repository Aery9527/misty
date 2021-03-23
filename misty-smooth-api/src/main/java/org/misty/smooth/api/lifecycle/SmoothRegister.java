package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.error.SmoothActionRegisterException;

import java.util.concurrent.ExecutorService;

public interface SmoothRegister {

    void registerExecutor(ExecutorService executor) throws SmoothActionRegisterException;

}
