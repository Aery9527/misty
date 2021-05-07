package org.misty.util.reflect.field;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Field;

public class FieldIntSetter extends FieldAbstract {

    private final FiConsumer<Integer> setter;

    protected FieldIntSetter(Field field, Object target) {
        super(field, target);
        this.setter = wrapAccessible(field, (i) -> field.set(target, i));
    }

    protected FieldIntSetter(Field field) {
        super(field);
        this.setter = wrapAccessible(field, (i) -> field.set(null, i));
    }

    public void set(int target) {
        this.setter.acceptOrHandle(target);
    }

    private FiConsumer<Integer> wrapAccessible(Field field, FiConsumer<Integer> setter) {
        return (i) -> {
            try {
                field.setAccessible(true);
                setter.acceptOrHandle(i);
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
