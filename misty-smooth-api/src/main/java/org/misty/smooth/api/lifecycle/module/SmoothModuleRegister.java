package org.misty.smooth.api.lifecycle.module;

import org.misty.smooth.api.error.SmoothActionRegisterException;
import org.misty.smooth.api.lifecycle.SmoothRegister;
import org.misty.smooth.api.mark.SmoothGuide;
import org.misty.smooth.api.service.SmoothService;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;

@SmoothGuide(needCross = true,
        implementationBy = SmoothGuide.Domain.CORE,
        usedBy = SmoothGuide.Domain.MODULE
)
public interface SmoothModuleRegister extends SmoothRegister {

    void registerService(SmoothService... services) throws SmoothActionRegisterException;

    void registerService(Collection<SmoothService> services) throws SmoothActionRegisterException;

    void registerResponseResultProcessErrorHandler(BiConsumer<SmoothService, Throwable> errorHandler) throws SmoothActionRegisterException;

}
