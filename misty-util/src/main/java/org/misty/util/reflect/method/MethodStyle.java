package org.misty.util.reflect.method;

public enum MethodStyle {
    STATIC, INSTANCE, ANCESTOR;

    public boolean isStatic() {
        return this.equals(STATIC);
    }

    public boolean isInstance() {
        return this.equals(INSTANCE);
    }

    public boolean isAncestor() {
        return this.equals(ANCESTOR);
    }

}
