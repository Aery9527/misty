package org.misty.smooth.manager.lifecycle;

import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.lifecycle.module.SmoothModuleRegister;
import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.manager.context.SmoothManagerContext;

public interface SmoothManagerLifecycle extends SmoothLifecycle {

    void initial(SmoothManagerContext managerContext, SmoothManagerRegister managerRegister);

}
