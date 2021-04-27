package org.misty.smooth.user;

import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.init.SmoothCoreContextBuilder;

import java.net.MalformedURLException;
import java.net.URL;

public class SmoothUserLogic {

    public void go(String[] args) throws MalformedURLException {
        SmoothCoreContextBuilder builder = new SmoothCoreContextBuilder(args);
        SmoothCoreContext smoothCoreContext = builder.build();

//        URL self = new URL("");
//        smoothCoreContext.loadSmoothManager(self);
    }

}
