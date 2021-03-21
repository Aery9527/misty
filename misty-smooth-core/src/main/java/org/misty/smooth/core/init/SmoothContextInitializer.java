package org.misty.smooth.core.init;

import org.misty.smooth.core.context.api.CoreSmoothContext;
import org.misty.smooth.core.context.impl.CoreSmoothContextPreset;

public class SmoothContextInitializer {

    public CoreSmoothContext initial(String[] arg) {
        return new CoreSmoothContextPreset();
    }

}
