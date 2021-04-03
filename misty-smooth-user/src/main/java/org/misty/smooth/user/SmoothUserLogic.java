package org.misty.smooth.user;

import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.init.SmoothCoreContextBuilder;

public class SmoothUserLogic {

    public void go(String[] args) {
        SmoothCoreContextBuilder builder = new SmoothCoreContextBuilder(args);
        SmoothCoreContext smoothCoreContext = builder.build();
    }

}
