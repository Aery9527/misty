package org.misty.util.reflect;

import java.lang.reflect.Field;

abstract class FieldObjectAbstract {

    private final FieldStyle fieldStyle;

    private final Field field;

    protected FieldObjectAbstract(Field field, Object target) {
        this.fieldStyle = FieldStyle.INSTANCE;
        this.field = field;
    }

    protected FieldObjectAbstract(Field field) {
        this.fieldStyle = FieldStyle.STATIC;
        this.field = field;
    }

    public String getName() {
        return this.field.getName();
    }

    public Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public Class<?> getFieldType() {
        return this.field.getType();
    }

    public FieldStyle getFieldStyle() {
        return fieldStyle;
    }

}
