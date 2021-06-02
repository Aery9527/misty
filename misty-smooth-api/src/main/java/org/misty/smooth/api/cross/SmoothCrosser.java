package org.misty.smooth.api.cross;

import org.misty.smooth.api.passable.SmoothPassableRunnable;
import org.misty.smooth.api.passable.SmoothPassableSupplier;

public class SmoothCrosser {

    private final ClassLoader wrapClassLoader;

    public SmoothCrosser(ClassLoader wrapClassLoader) {
        if (wrapClassLoader == null) {
            throw new NullPointerException("wrapClassLoader can't be null.");
        }

        this.wrapClassLoader = wrapClassLoader;
    }

    public void wrap(SmoothPassableRunnable action) {
        wrap(() -> {
            action.runOrThrow();
            return null;
        });
    }

    public <ReturnType> ReturnType wrap(SmoothPassableSupplier<ReturnType> action) {
        Thread currentThread = Thread.currentThread();
        ClassLoader originalContextClassLoader = currentThread.getContextClassLoader();

        if (originalContextClassLoader == this.wrapClassLoader) {
            return action.getOrHandle();
        }

        try {
            currentThread.setContextClassLoader(this.wrapClassLoader);
            return action.getOrHandle();
        } finally {
            currentThread.setContextClassLoader(originalContextClassLoader);
        }
    }

    public ClassLoader getWrapClassLoader() {
        return wrapClassLoader;
    }

}
