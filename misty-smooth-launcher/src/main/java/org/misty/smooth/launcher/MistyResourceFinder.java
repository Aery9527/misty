package org.misty.smooth.launcher;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MistyResourceFinder {

    public static final String NOT_FOUND_LIB_MSG_FORMAT = "can't find libs following %s in AppClassLoader classpath.";

    public static final String[] DEFAULT_IMPLANT_LIBS = new String[]{"misty-description", "misty-smooth-api", "misty-smooth-manager"};

    public List<URL> findDefaultImplantLibs() {
        return findFromAppClassLoader(true, DEFAULT_IMPLANT_LIBS);
    }

    public List<URL> findFromAppClassLoader(boolean needFullyIncluded, String... matchs) {
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

        if (needFullyIncluded) {
            checkFullyIncluded(result, matchs);
        }

        return result;
    }

    private void checkFullyIncluded(List<URL> result, String[] matchs) {
        List<String> notIncludedMatch = new ArrayList<>();

        for (String match : matchs) {
            boolean exists = false;

            for (URL url : result) {
                if (url.toString().contains(match)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                notIncludedMatch.add(match);
            }
        }

        if (notIncludedMatch.isEmpty()) {
            return;
        }

        throw new IllegalStateException(String.format(NOT_FOUND_LIB_MSG_FORMAT, notIncludedMatch));
    }

}
