package org.misty.util.reflect.method;

public enum MethodStyle {
    STATIC, INSTANCE;

    public boolean isStatic() {
        return this.equals(STATIC);
    }

    public boolean isInstance() {
        return this.equals(INSTANCE);
    }

}
