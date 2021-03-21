package org.misty.smooth.manager.lifecycle;

import org.misty.smooth.api.context.SmoothActionRegister;
import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.SmoothModuleLifecycle;
import org.misty.smooth.manager.context.SmoothManagerContext;

public interface SmoothManagerLifecycle extends SmoothModuleLifecycle {

    @Override
    default void initial(SmoothContext smoothContext, SmoothActionRegister actionRegister) {
        initial((SmoothManagerContext) smoothContext, actionRegister);
    }

    void initial(SmoothManagerContext smoothContext, SmoothActionRegister actionRegister);

}
