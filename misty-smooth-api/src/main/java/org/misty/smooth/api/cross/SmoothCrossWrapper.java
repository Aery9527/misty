package org.misty.smooth.api.cross;

public class SmoothCrossWrapper<WrappedTarget> extends SmoothCrosser {

    private final WrappedTarget wrappedTarget;

    public SmoothCrossWrapper(ClassLoader wrapClassLoader, WrappedTarget wrappedTarget) {
        super(wrapClassLoader);

        if (wrappedTarget == null) {
            throw new NullPointerException("wrappedTarget can't be null.");
        }

        this.wrappedTarget = wrappedTarget;
    }

    public WrappedTarget getWrappedTarget() {
        return wrappedTarget;
    }

}
