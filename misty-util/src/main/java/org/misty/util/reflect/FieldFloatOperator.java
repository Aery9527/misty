package org.misty.util.reflect;

import java.lang.reflect.Field;

public class FieldFloatOperator extends FieldObjectAbstract {

    private final FieldFloatGetter getter;

    private final FieldFloatSetter setter;

    protected FieldFloatOperator(Field field, Object target) {
        super(field, target);
        this.getter = new FieldFloatGetter(field, target);
        this.setter = new FieldFloatSetter(field, target);
    }

    protected FieldFloatOperator(Field field) {
        super(field);
        this.getter = new FieldFloatGetter(field);
        this.setter = new FieldFloatSetter(field);
    }

    public float get() {
        return this.getter.get();
    }

    public void set(float f) {
        this.setter.set(f);
    }

}
