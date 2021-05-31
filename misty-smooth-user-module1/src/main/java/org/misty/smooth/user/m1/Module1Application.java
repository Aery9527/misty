package org.misty.smooth.user.m1;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.lifecycle.module.SmoothModuleRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;

public class Module1Application implements SmoothModuleLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getName() {
        return "module1";
    }

    @Override
    public String getVersion() {
        return "v1";
    }

    @Override
    public Set<String> getAttachment() {
        return Collections.emptySet();
    }

    @Override
    public void online() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void initial(SmoothContext smoothContext, SmoothModuleRegister actionRegister) {

    }

}
