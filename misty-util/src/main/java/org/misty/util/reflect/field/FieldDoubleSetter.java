package org.misty.util.reflect.field;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Field;

public class FieldDoubleSetter extends FieldAbstract {

    private final FiConsumer<Double> setter;

    protected FieldDoubleSetter(Field field, Object target) {
        super(field, target);
        this.setter = wrapAccessible(field, (d) -> field.set(target, d));
    }

    protected FieldDoubleSetter(Field field) {
        super(field);
        this.setter = wrapAccessible(field, (d) -> field.set(null, d));
    }

    public void set(double target) {
        this.setter.acceptOrHandle(target);
    }

    private FiConsumer<Double> wrapAccessible(Field field, FiConsumer<Double> setter) {
        return (d) -> {
            try {
                field.setAccessible(true);
                setter.acceptOrHandle(d);
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
