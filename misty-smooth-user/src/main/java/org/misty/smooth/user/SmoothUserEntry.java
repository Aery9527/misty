package org.misty.smooth.user;

import org.misty.smooth.launcher.SmoothLauncher;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class SmoothUserEntry {

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        boolean showClasspath = false; // to show classpath between before and after of SmoothLauncher.start()
        printClasspath(showClasspath, "before");
        SmoothLauncher.start(); // [1] invoke before any logic
        printClasspath(showClasspath, "after");

        // [2] do business logic, like SpringApplication.run(.class, args)
        new SmoothUserLogic().go();
    }

    private static void printClasspath(boolean showClasspath, String action) {
        if (!showClasspath) {
            return;
        }

        System.out.println();
        System.out.println("===== " + action + " =====");

        printClasspath(SmoothUserEntry.class.getClassLoader());
    }

    private static void printClasspath(ClassLoader classLoader) {
        if (classLoader == null) {
            return;
        }

        printClasspath(classLoader.getParent());

        URLClassLoader ucl = (URLClassLoader) classLoader;
        URL[] classpath = ucl.getURLs();

        System.out.println("[" + classLoader + "] (" + classLoader.getClass() + ")");
        Arrays.asList(classpath).forEach(System.out::println);
    }

}
