package org.misty.smooth.user.m1;

import org.misty.smooth.api.context.SmoothActionRegister;
import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.SmoothModuleLifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Module1Application implements SmoothModuleLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass()); // slf4j

    @Override
    public String getName() {
        return "module1";
    }

    @Override
    public String getVersion() {
        return "v1";
    }

    @Override
    public void initial(SmoothContext smoothContext, SmoothActionRegister actionRegister) {

    }

}
