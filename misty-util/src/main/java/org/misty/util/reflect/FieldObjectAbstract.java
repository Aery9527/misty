package org.misty.util.reflect;

import java.lang.reflect.Field;

abstract class FieldObjectAbstract {

    private final FieldExtractorStyle style;

    private final Field field;

    protected FieldObjectAbstract(Field field, Object target) {
        this.style = FieldExtractorStyle.INSTANCE;
        this.field = field;
    }

    protected FieldObjectAbstract(Field field) {
        this.style = FieldExtractorStyle.STATIC;
        this.field = field;
    }

    public String getName() {
        return this.field.getName();
    }

    public Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public FieldExtractorStyle getStyle() {
        return style;
    }

}
