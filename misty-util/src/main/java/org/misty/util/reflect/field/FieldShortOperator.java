package org.misty.util.reflect.field;

import java.lang.reflect.Field;

public class FieldShortOperator extends FieldAbstract {

    private final FieldShortGetter getter;

    private final FieldShortSetter setter;

    protected FieldShortOperator(Field field, Object target) {
        super(field, target);
        this.getter = new FieldShortGetter(field, target);
        this.setter = new FieldShortSetter(field, target);
    }

    protected FieldShortOperator(Field field) {
        super(field);
        this.getter = new FieldShortGetter(field);
        this.setter = new FieldShortSetter(field);
    }

    public short get() {
        return this.getter.get();
    }

    public void set(short s) {
        this.setter.set(s);
    }

}
