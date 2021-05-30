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

    @Override
    public int hashCode() {
        return wrap(this.wrappedTarget::hashCode);
    }

    @Override
    public boolean equals(Object obj) {
        return wrap(() -> this.wrappedTarget.equals(obj));
    }

    @Override
    public String toString() {
        return wrap(this.wrappedTarget::toString);
    }

}
