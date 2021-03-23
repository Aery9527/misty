package org.misty.smooth.core;

import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.init.SmoothCoreContextBuilder;

public class SmoothApplication {

    public static SmoothCoreContext start(String[] args) {
        return builder(args).build();
    }

    public static SmoothCoreContextBuilder builder(String[] args) {
        return new SmoothCoreContextBuilder(args);
    }

}
