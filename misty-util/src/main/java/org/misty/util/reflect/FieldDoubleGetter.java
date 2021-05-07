package org.misty.util.reflect;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Field;

public class FieldDoubleGetter extends FieldObjectAbstract {

    private final FiSupplier<Double> getter;

    protected FieldDoubleGetter(Field field, Object target) {
        super(field, target);
        this.getter = wrapAccessible(field, () -> (double) field.get(target));
    }

    protected FieldDoubleGetter(Field field) {
        super(field);
        this.getter = wrapAccessible(field, () -> (double) field.get(null));
    }

    public double get() {
        return this.getter.getOrHandle();
    }

    private FiSupplier<Double> wrapAccessible(Field field, FiSupplier<Double> getter) {
        return () -> {
            try {
                field.setAccessible(true);
                return getter.getOrHandle();
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
