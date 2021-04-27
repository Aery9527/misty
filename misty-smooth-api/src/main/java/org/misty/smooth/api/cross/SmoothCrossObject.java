package org.misty.smooth.api.cross;

import org.misty.smooth.api.error.SmoothCrossException;

import java.util.function.Supplier;

public class SmoothCrossObject {

    private final ClassLoader wrapClassLoader;

    public SmoothCrossObject(ClassLoader wrapClassLoader) {
        if (wrapClassLoader == null) {
            throw new NullPointerException("wrapClassLoader can't be null.");
        }

        this.wrapClassLoader = wrapClassLoader;
    }

    public void wrap(SmoothCrossRunnable action) throws SmoothCrossException {
        wrap(() -> {
            action.run();
            return null;
        });
    }

    public <ReturnType> ReturnType wrap(SmoothCrossSupplier<ReturnType> action) throws SmoothCrossException {
        Thread currentThread = Thread.currentThread();
        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        Supplier<ReturnType> newAction = () -> {
            try {
                return action.get();
            } catch (Exception t) {
                throw new SmoothCrossException(t);
            }
        };

        if (contextClassLoader == this.wrapClassLoader) {
            return newAction.get();
        }

        try {
            currentThread.setContextClassLoader(this.wrapClassLoader);
            return newAction.get();
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }
    }

    public ClassLoader getWrapClassLoader() {
        return wrapClassLoader;
    }

}
