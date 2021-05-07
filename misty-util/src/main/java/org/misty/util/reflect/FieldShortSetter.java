package org.misty.util.reflect;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Field;

public class FieldShortSetter extends FieldObjectAbstract {

    private final FiConsumer<Short> setter;

    protected FieldShortSetter(Field field, Object target) {
        super(field, target);
        this.setter = wrapAccessible(field, (s) -> field.set(target, s));
    }

    protected FieldShortSetter(Field field) {
        super(field);
        this.setter = wrapAccessible(field, (s) -> field.set(null, s));
    }

    public void set(short target) {
        this.setter.acceptOrHandle(target);
    }

    private FiConsumer<Short> wrapAccessible(Field field, FiConsumer<Short> setter) {
        return (s) -> {
            try {
                field.setAccessible(true);
                setter.acceptOrHandle(s);
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
