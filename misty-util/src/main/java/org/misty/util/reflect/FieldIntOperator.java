package org.misty.util.reflect;

import java.lang.reflect.Field;

public class FieldIntOperator extends FieldObjectAbstract {

    private final FieldIntGetter getter;

    private final FieldIntSetter setter;

    protected FieldIntOperator(Field field, Object target) {
        super(field, target);
        this.getter = new FieldIntGetter(field, target);
        this.setter = new FieldIntSetter(field, target);
    }

    protected FieldIntOperator(Field field) {
        super(field);
        this.getter = new FieldIntGetter(field);
        this.setter = new FieldIntSetter(field);
    }

    public int get() {
        return this.getter.get();
    }

    public void set(int i) {
        this.setter.set(i);
    }

}
