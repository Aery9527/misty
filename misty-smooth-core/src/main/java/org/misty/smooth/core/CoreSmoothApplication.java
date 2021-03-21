package org.misty.smooth.core;

import org.misty.smooth.core.context.api.CoreSmoothContext;
import org.misty.smooth.core.init.SmoothContextInitializer;

public class CoreSmoothApplication {

    public static CoreSmoothContext start(String[] arg) {
        SmoothContextInitializer initializer = new SmoothContextInitializer();
        return initializer.initial(arg);
    }

}
