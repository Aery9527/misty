package org.misty.util.reflect;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
abstract class FieldObjectAbstract<TargetType> {

    private final FieldExtractor.Style style;

    private final Field field;

    protected FieldObjectAbstract(Field field, Object target) {
        this.style = FieldExtractor.Style.INSTANCE;
        this.field = field;
    }

    protected FieldObjectAbstract(Field field) {
        this.style = FieldExtractor.Style.STATIC;
        this.field = field;
    }

    public String getName() {
        return this.field.getName();
    }

    public Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public Class<TargetType> getType() {
        return (Class<TargetType>) this.field.getType();
    }

    public FieldExtractor.Style getStyle() {
        return style;
    }

}
