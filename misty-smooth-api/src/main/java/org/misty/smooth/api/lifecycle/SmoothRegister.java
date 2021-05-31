package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.error.SmoothActionRegisterException;
import org.misty.smooth.api.mark.SmoothGuide;

import java.util.concurrent.ExecutorService;

@SmoothGuide(
        implementationBy = SmoothGuide.Domain.CORE,
        usedBy = {SmoothGuide.Domain.MANAGER, SmoothGuide.Domain.MODULE}
)
public interface SmoothRegister {

    void registerExecutor(ExecutorService executor) throws SmoothActionRegisterException;

}
