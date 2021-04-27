package org.misty.smooth.user;

import org.misty.smooth.manager.context.SmoothManagerContext;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.lifecycle.SmoothManagerRegister;

public class SmoothUserManager implements SmoothManagerLifecycle {

    @Override
    public String getName() {
        return "manager";
    }

    @Override
    public String getVersion() {
        return "v1";
    }

    @Override
    public void initial(SmoothManagerContext managerContext, SmoothManagerRegister managerRegister) {

    }

}
