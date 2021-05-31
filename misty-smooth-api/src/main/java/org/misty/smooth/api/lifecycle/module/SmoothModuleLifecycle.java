package org.misty.smooth.api.lifecycle.module;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.mark.SmoothGuide;

@SmoothGuide(needCross = true,
        implementationBy = SmoothGuide.Domain.MODULE,
        usedBy = SmoothGuide.Domain.CORE
)
public interface SmoothModuleLifecycle extends SmoothLifecycle {

    void initial(SmoothContext smoothContext, SmoothModuleRegister moduleRegister);

}
