package org.misty.smooth.core.domain.module.impl;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.lifecycle.module.SmoothModuleRegister;

public class SmoothModuleLifecycleCrosser extends SmoothCrossWrapper<SmoothModuleLifecycle> implements SmoothModuleLifecycle {

    public SmoothModuleLifecycleCrosser(ClassLoader wrapClassLoader, SmoothModuleLifecycle smoothModuleLifecycle) {
        super(wrapClassLoader, smoothModuleLifecycle);
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
    public void initial(SmoothContext smoothContext, SmoothModuleRegister moduleRegister) {

    }

}
