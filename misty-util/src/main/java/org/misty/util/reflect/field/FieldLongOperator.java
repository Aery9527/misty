package org.misty.util.reflect.field;

import java.lang.reflect.Field;

public class FieldLongOperator extends FieldAbstract {

    private final FieldLongGetter getter;

    private final FieldLongSetter setter;

    protected FieldLongOperator(Field field, Object target) {
        super(field, target);
        this.getter = new FieldLongGetter(field, target);
        this.setter = new FieldLongSetter(field, target);
    }

    protected FieldLongOperator(Field field) {
        super(field);
        this.getter = new FieldLongGetter(field);
        this.setter = new FieldLongSetter(field);
    }

    public long get() {
        return this.getter.get();
    }

    public void set(long l) {
        this.setter.set(l);
    }

}
