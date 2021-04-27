package org.misty.smooth.launcher;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.URLClassPath;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

class SmoothLauncherTest {

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        // test normal
        printClasspath("before");
        SmoothLauncher.start();
        printImplantedLibs();
        printClasspath("after");

        // test lack lib
        List<String> removedLibs = new ArrayList<>();
        for (String match : MistyResourceFinder.DEFAULT_IMPLANT_LIBS) {
            removedLibs.add(match);

            resetSmoothLauncher();
            removeAppClassLoaderURL(match);

            Assertions.assertThatThrownBy(SmoothLauncher::start)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage(String.format(MistyResourceFinder.NOT_FOUND_LIB_MSG_FORMAT, removedLibs));

        }
    }

    @SuppressWarnings("unchecked")
    private void resetSmoothLauncher() throws NoSuchFieldException, IllegalAccessException {
        Field field = SmoothLauncher.class.getDeclaredField("IMPLANTED_LIBS");
        try {
            field.setAccessible(true);
            AtomicReference<List<URL>> implantedLibs = (AtomicReference<List<URL>>) field.get(null);
            implantedLibs.set(null);
        } finally {
            field.setAccessible(false);
        }
    }

    private void removeAppClassLoaderURL(String removedMatch) throws NoSuchFieldException, IllegalAccessException {
        URLClassLoader appClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        URLClassPath ucp = getURLClassPath(appClassLoader);
        List<URL> path = getPath(ucp);
        path.removeIf(url -> url.toString().contains(removedMatch));
    }

    private URLClassPath getURLClassPath(URLClassLoader appClassLoader) throws NoSuchFieldException, IllegalAccessException {
        Field field = URLClassLoader.class.getDeclaredField("ucp");
        try {
            field.setAccessible(true);
            return (URLClassPath) field.get(appClassLoader);
        } finally {
            field.setAccessible(false);
        }
    }

    @SuppressWarnings("unchecked")
    private List<URL> getPath(URLClassPath ucp) throws NoSuchFieldException, IllegalAccessException {
        Field field = URLClassPath.class.getDeclaredField("path");
        try {
            field.setAccessible(true);
            return (List<URL>) field.get(ucp);
        } finally {
            field.setAccessible(false);
        }
    }

    private static void printClasspath(String action) {
        System.out.println();
        System.out.println("===== " + action + " =====");

        printClasspath(SmoothLauncherTest.class.getClassLoader());
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

    private void printImplantedLibs() {
        System.out.println();
        System.out.println("===== ImplantedLibs =====");

        List<URL> implantedLibs = SmoothLauncher.getImplantedLibs();
        implantedLibs.forEach(System.out::println);
    }

}
