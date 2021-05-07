package org.misty.util.reflect.field;

import java.lang.reflect.Field;

public class FieldDoubleOperator extends FieldAbstract {

    private final FieldDoubleGetter getter;

    private final FieldDoubleSetter setter;

    protected FieldDoubleOperator(Field field, Object target) {
        super(field, target);
        this.getter = new FieldDoubleGetter(field, target);
        this.setter = new FieldDoubleSetter(field, target);
    }

    protected FieldDoubleOperator(Field field) {
        super(field);
        this.getter = new FieldDoubleGetter(field);
        this.setter = new FieldDoubleSetter(field);
    }

    public double get() {
        return this.getter.get();
    }

    public void set(double d) {
        this.setter.set(d);
    }

}
