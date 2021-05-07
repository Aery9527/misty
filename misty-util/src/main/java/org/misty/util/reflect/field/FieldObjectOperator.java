package org.misty.util.reflect.field;

import java.lang.reflect.Field;

public class FieldObjectOperator<TargetType> extends FieldAbstract {

    private final FieldObjectGetter<TargetType> getter;

    private final FieldObjectSetter<TargetType> setter;

    protected FieldObjectOperator(Field field, Object target) {
        super(field, target);
        this.getter = new FieldObjectGetter<>(field, target);
        this.setter = new FieldObjectSetter<>(field, target);
    }

    protected FieldObjectOperator(Field field) {
        super(field);
        this.getter = new FieldObjectGetter<>(field);
        this.setter = new FieldObjectSetter<>(field);
    }

    public TargetType get() {
        return this.getter.get();
    }

    public void set(TargetType target) {
        this.setter.set(target);
    }

}
