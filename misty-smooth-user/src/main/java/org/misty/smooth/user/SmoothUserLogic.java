package org.misty.smooth.user;

import org.misty.smooth.core.SmoothApplication;
import org.misty.smooth.core.context.api.SmoothCoreContext;

public class SmoothUserLogic {

    public void go(String[] args) {
        SmoothCoreContext smoothCoreContext = SmoothApplication.start(args);
    }

}
