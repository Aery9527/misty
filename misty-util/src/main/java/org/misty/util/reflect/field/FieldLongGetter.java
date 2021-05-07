package org.misty.util.reflect.field;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Field;

public class FieldLongGetter extends FieldAbstract {

    private final FiSupplier<Long> getter;

    protected FieldLongGetter(Field field, Object target) {
        super(field, target);
        this.getter = wrapAccessible(field, () -> (long) field.get(target));
    }

    protected FieldLongGetter(Field field) {
        super(field);
        this.getter = wrapAccessible(field, () -> (long) field.get(null));
    }

    public long get() {
        return this.getter.getOrHandle();
    }

    private FiSupplier<Long> wrapAccessible(Field field, FiSupplier<Long> getter) {
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
