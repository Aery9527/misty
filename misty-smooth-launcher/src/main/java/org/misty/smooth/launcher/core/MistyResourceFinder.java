package org.misty.smooth.launcher.core;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MistyResourceFinder {

    public static final String DEFAULT_IMPLANT_DESCRIPTION = "misty-description";

    public static final String DEFAULT_IMPLANT_SMOOTH_API = "misty-smooth-api";

    public List<URL> findDefaultImplantLibs() {
        return find(DEFAULT_IMPLANT_DESCRIPTION, DEFAULT_IMPLANT_SMOOTH_API);
    }

    public List<URL> find(String... matchs) {
        List<URL> result = new ArrayList<>();

        URLClassLoader appClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        URL[] classpath = appClassLoader.getURLs();
        Arrays.stream(classpath)
                .filter((url) -> {
                    for (String match : matchs) {
                        if (url.toString().contains(match)) {
                            return true;
                        }
                    }
                    return false;
                })
                .forEach(result::add);

        return result;
    }

}
