package org.misty.smooth.manager.lifecycle;

import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.mark.SmoothGuide;
import org.misty.smooth.manager.context.SmoothManagerContext;

@SmoothGuide(needCross = true,
        implementationBy = SmoothGuide.Domain.MANAGER,
        usedBy = SmoothGuide.Domain.CORE
)
public interface SmoothManagerLifecycle extends SmoothLifecycle {

    void initial(SmoothManagerContext managerContext, SmoothManagerRegister managerRegister);

}
