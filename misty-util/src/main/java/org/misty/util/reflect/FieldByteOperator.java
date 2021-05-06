package org.misty.util.reflect;

import java.lang.reflect.Field;

public class FieldByteOperator extends FieldObjectAbstract {

    private final FieldByteGetter getter;

    private final FieldByteSetter setter;

    protected FieldByteOperator(Field field, Object target) {
        super(field, target);
        this.getter = new FieldByteGetter(field, target);
        this.setter = new FieldByteSetter(field, target);
    }

    protected FieldByteOperator(Field field) {
        super(field);
        this.getter = new FieldByteGetter(field);
        this.setter = new FieldByteSetter(field);
    }

    public byte get() {
        return this.getter.get();
    }

    public void set(byte b) {
        this.setter.set(b);
    }

}
