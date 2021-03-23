package org.misty.smooth.api.lifecycle.module;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;

public interface SmoothModuleLifecycle extends SmoothLifecycle {

    void initial(SmoothContext smoothContext, SmoothModuleRegister moduleRegister);

}
