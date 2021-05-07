package org.misty.util.reflect.field;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Field;

public class FieldIntGetter extends FieldAbstract {

    private final FiSupplier<Integer> getter;

    protected FieldIntGetter(Field field, Object target) {
        super(field, target);
        this.getter = wrapAccessible(field, () -> (int) field.get(target));
    }

    protected FieldIntGetter(Field field) {
        super(field);
        this.getter = wrapAccessible(field, () -> (int) field.get(null));
    }

    public int get() {
        return this.getter.getOrHandle();
    }

    private FiSupplier<Integer> wrapAccessible(Field field, FiSupplier<Integer> getter) {
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
