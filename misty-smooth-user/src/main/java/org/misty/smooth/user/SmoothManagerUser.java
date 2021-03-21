package org.misty.smooth.user;

import org.misty.smooth.api.context.SmoothActionRegister;
import org.misty.smooth.manager.context.SmoothManagerContext;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;

public class SmoothManagerUser implements SmoothManagerLifecycle {

    @Override
    public String getName() {
        return "manager";
    }

    @Override
    public String getVersion() {
        return "v1";
    }

    @Override
    public void initial(SmoothManagerContext smoothContext, SmoothActionRegister actionRegister) {

    }

}
