package org.misty.smooth.launcher.core;

import java.net.URL;
import java.util.List;

/**
 * 因為smooth對於classloader的規劃, 必須要在最上層的ClassLoader載入共用類別,
 * 所以會透過reflection機制, 在smooth-user的main裡對ExtClassLoader的classpath植入一些smooth的lib.
 * 也因此{@link SmoothLauncher}(與其{@link #start()}內)不可引用到其他smooth的class,
 * 因為若引用了會導致AppClassLoader先載入該class, 而非{@link #start()}進行ExtClassLoader植入後,
 * 由ExtClassLoader進行載入class, 這樣就不是smooth對於classloader規劃的需求, 會出問題!
 * 所以, smooth-user的main裡同樣的道理, 調用{@link #start()}之前不可調用其他方法或在static階段使用smooth其他的class.
 */
public class SmoothLauncher {

    public static void start() {
        MistyResourceFinder mistyResourceFinder = new MistyResourceFinder();
        List<URL> implantedLibs = mistyResourceFinder.findDefaultImplantLibs();

        // TODO
//        implantedLibs.forEach(System.out::println);
    }

}
