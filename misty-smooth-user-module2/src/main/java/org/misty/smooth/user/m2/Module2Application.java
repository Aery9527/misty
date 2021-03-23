package org.misty.smooth.user.m2;

import org.misty.smooth.api.lifecycle.module.SmoothModuleRegister;
import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Module2Application implements SmoothModuleLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass()); // slf4j

    @Override
    public String getName() {
        return "module2";
    }

    @Override
    public String getVersion() {
        return "v1";
    }

    @Override
    public void initial(SmoothContext smoothContext, SmoothModuleRegister actionRegister) {

    }

}
