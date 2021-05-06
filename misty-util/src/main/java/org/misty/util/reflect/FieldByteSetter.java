package org.misty.util.reflect;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Field;

public class FieldByteSetter extends FieldObjectAbstract {

    private final FiConsumer<Byte> setter;

    protected FieldByteSetter(Field field, Object target) {
        super(field, target);
        this.setter = wrapAccessible(field, (b) -> field.set(target, b));
    }

    protected FieldByteSetter(Field field) {
        super(field);
        this.setter = wrapAccessible(field, (b) -> field.set(null, b));
    }

    public void set(byte target) {
        this.setter.acceptOrHandle(target);
    }

    private FiConsumer<Byte> wrapAccessible(Field field, FiConsumer<Byte> setter) {
        return (b) -> {
            try {
                field.setAccessible(true);
                setter.acceptOrHandle(b);
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
