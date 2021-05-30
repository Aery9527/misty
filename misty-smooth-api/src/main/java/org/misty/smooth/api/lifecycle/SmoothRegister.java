package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.error.SmoothActionRegisterException;
import org.misty.smooth.api.mark.Guide;

import java.util.concurrent.ExecutorService;

@Guide(
        implementationBy = Guide.Scope.CORE,
        usedBy = {Guide.Scope.MANAGER, Guide.Scope.MODULE}
)
public interface SmoothRegister {

    void registerExecutor(ExecutorService executor) throws SmoothActionRegisterException;

}
