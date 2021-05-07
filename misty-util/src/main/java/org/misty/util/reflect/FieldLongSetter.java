package org.misty.util.reflect;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Field;

public class FieldLongSetter extends FieldObjectAbstract {

    private final FiConsumer<Long> setter;

    protected FieldLongSetter(Field field, Object target) {
        super(field, target);
        this.setter = wrapAccessible(field, (l) -> field.set(target, l));
    }

    protected FieldLongSetter(Field field) {
        super(field);
        this.setter = wrapAccessible(field, (l) -> field.set(null, l));
    }

    public void set(long target) {
        this.setter.acceptOrHandle(target);
    }

    private FiConsumer<Long> wrapAccessible(Field field, FiConsumer<Long> setter) {
        return (l) -> {
            try {
                field.setAccessible(true);
                setter.acceptOrHandle(l);
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
