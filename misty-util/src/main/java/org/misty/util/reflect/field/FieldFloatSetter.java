package org.misty.util.reflect.field;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Field;

public class FieldFloatSetter extends FieldAbstract {

    private final FiConsumer<Float> setter;

    protected FieldFloatSetter(Field field, Object target) {
        super(field, target);
        this.setter = wrapAccessible(field, (f) -> field.set(target, f));
    }

    protected FieldFloatSetter(Field field) {
        super(field);
        this.setter = wrapAccessible(field, (f) -> field.set(null, f));
    }

    public void set(float target) {
        this.setter.acceptOrHandle(target);
    }

    private FiConsumer<Float> wrapAccessible(Field field, FiConsumer<Float> setter) {
        return (f) -> {
            try {
                field.setAccessible(true);
                setter.acceptOrHandle(f);
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
