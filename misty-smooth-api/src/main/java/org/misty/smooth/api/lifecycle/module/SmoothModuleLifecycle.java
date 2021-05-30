package org.misty.smooth.api.lifecycle.module;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.mark.Guide;

@Guide(
        implementationBy = Guide.Scope.MODULE,
        usedBy = Guide.Scope.CORE
)
public interface SmoothModuleLifecycle extends SmoothLifecycle {

    void initial(SmoothContext smoothContext, SmoothModuleRegister moduleRegister);

}
