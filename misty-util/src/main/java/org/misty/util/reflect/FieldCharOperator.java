package org.misty.util.reflect;

import java.lang.reflect.Field;

public class FieldCharOperator extends FieldObjectAbstract {

    private final FieldCharGetter getter;

    private final FieldCharSetter setter;

    protected FieldCharOperator(Field field, Object target) {
        super(field, target);
        this.getter = new FieldCharGetter(field, target);
        this.setter = new FieldCharSetter(field, target);
    }

    protected FieldCharOperator(Field field) {
        super(field);
        this.getter = new FieldCharGetter(field);
        this.setter = new FieldCharSetter(field);
    }

    public char get() {
        return this.getter.get();
    }

    public void set(char b) {
        this.setter.set(b);
    }

}
