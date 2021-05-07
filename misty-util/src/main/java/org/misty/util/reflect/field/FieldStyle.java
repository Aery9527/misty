package org.misty.util.reflect.field;

public enum FieldStyle {
    STATIC, INSTANCE;

    public boolean isStatic() {
        return this.equals(STATIC);
    }

    public boolean isInstance() {
        return this.equals(INSTANCE);
    }

}
