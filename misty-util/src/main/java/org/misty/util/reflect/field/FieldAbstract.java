package org.misty.util.reflect.field;

import java.lang.reflect.Field;
import java.util.Optional;

abstract class FieldAbstract {

    private final Field field;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<Object> target;

    private final FieldStyle fieldStyle;

    protected FieldAbstract(Field field, Object target) {
        this.field = field;
        this.target = Optional.of(target);

        Class<?> declaringClass = field.getDeclaringClass();
        Class<?> targetClass = target.getClass();
        if (declaringClass.equals(targetClass)) {
            this.fieldStyle = FieldStyle.INSTANCE;
        } else {
            this.fieldStyle = FieldStyle.ANCESTOR;
        }
    }

    protected FieldAbstract(Field field) {
        this.field = field;
        this.target = Optional.empty();
        this.fieldStyle = FieldStyle.STATIC;
    }

    public Field getField() {
        return this.field;
    }

    public Optional<Object> getTarget() {
        return target;
    }

    public FieldStyle getFieldStyle() {
        return fieldStyle;
    }

}
