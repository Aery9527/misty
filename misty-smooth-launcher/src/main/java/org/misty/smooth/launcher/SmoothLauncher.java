package org.misty.smooth.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 因為smooth對於classloader的規劃, 必須要在最上層的ClassLoader載入共用類別.
 * 所以會要求使用者要在main裡優先操作{@link #start()}這個方法,
 * 而該方法會從AppClassLoader取出需要由ExtClassLoader載入的classpath(共類類別)植入.
 * 也因此在smooth-user的main與{@link SmoothLauncher}不可引用到其他smooth的class,
 * 因為若引用了會導致AppClassLoader提早載入該class, 而非在{@link #start()}進行ExtClassLoader植入後,
 * 由ExtClassLoader載入class, 這樣就不是smooth對於classloader規劃的需求, 會出問題!
 */
public class SmoothLauncher {

    private static final AtomicReference<List<URL>> IMPLANTED_LIBS = new AtomicReference<>();

    public static void start() {
        boolean first = IMPLANTED_LIBS.compareAndSet(null, Collections.emptyList());
        if (!first) {
            throw new IllegalStateException("can't start again.");
        }

        MistyResourceFinder mistyResourceFinder = new MistyResourceFinder();
        List<URL> implantedLibs = mistyResourceFinder.findDefaultImplantLibs();
        IMPLANTED_LIBS.set(Collections.unmodifiableList(implantedLibs));

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        URLClassLoader extClassLoader = (URLClassLoader) appClassLoader.getParent();
        implantClasspath(extClassLoader, implantedLibs);
    }

    private static void implantClasspath(URLClassLoader extClassLoader, List<URL> implantedLibs) {
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);

            for (URL url : implantedLibs) {
                addURL.invoke(extClassLoader, url);
            }

            addURL.setAccessible(false);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<URL> getImplantedLibs() {
        return IMPLANTED_LIBS.get();
    }

}
