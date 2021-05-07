package org.misty.util.reflect.field;

import java.lang.reflect.Field;

abstract class FieldAbstract {

    private final Field field;

    private final FieldStyle fieldStyle;

    protected FieldAbstract(Field field, Object target) {
        this.field = field;
        this.fieldStyle = FieldStyle.INSTANCE;
    }

    protected FieldAbstract(Field field) {
        this.field = field;
        this.fieldStyle = FieldStyle.STATIC;
    }

    public Field getField() {
        return this.field;
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
