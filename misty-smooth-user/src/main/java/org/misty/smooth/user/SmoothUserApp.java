package org.misty.smooth.user;

import org.misty.smooth.launcher.SmoothLauncher;

import java.net.MalformedURLException;

public class SmoothUserApp {

    public static void main(String[] args) throws MalformedURLException {
        // [Step1] invoke "SmoothLauncher.start()" before any logic
        SmoothLauncher.start();

        // [Step2] do business logic, like "SpringApplication.run(xxx.class, args)"
        new SmoothUserLogic().go(args);
    }

}
