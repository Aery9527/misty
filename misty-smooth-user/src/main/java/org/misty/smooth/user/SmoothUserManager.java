package org.misty.smooth.user;

import org.misty.smooth.manager.context.SmoothManagerContext;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.lifecycle.SmoothManagerRegister;

import java.util.Collections;
import java.util.Set;

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
    public void initial(SmoothManagerContext managerContext, SmoothManagerRegister managerRegister) {

    }

}
