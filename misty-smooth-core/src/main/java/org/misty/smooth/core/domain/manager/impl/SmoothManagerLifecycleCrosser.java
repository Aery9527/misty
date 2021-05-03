package org.misty.smooth.core.domain.manager.impl;

import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.manager.context.SmoothManagerContext;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.lifecycle.SmoothManagerRegister;

public class SmoothManagerLifecycleCrosser extends SmoothCrossWrapper<SmoothManagerLifecycle> implements SmoothManagerLifecycle {

    public SmoothManagerLifecycleCrosser(ClassLoader wrapClassLoader, SmoothManagerLifecycle smoothManagerLifecycle) {
        super(wrapClassLoader, smoothManagerLifecycle);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public void initial(SmoothManagerContext managerContext, SmoothManagerRegister managerRegister) {

    }

}
