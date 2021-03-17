package org.misty.smooth.user;

import org.misty.smooth.launcher.core.SmoothLauncher;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class SmoothUserEntry {

    public static void main(String[] args) {
        printClasspath("before");

        SmoothLauncher.start();

        // do business logic...

        printClasspath("after");
    }

    private static void printClasspath(String action) {
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
