package org.misty.util.reflect;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Field;

public class FieldShortGetter extends FieldObjectAbstract {

    private final FiSupplier<Short> getter;

    protected FieldShortGetter(Field field, Object target) {
        super(field, target);
        this.getter = wrapAccessible(field, () -> (short) field.get(target));
    }

    protected FieldShortGetter(Field field) {
        super(field);
        this.getter = wrapAccessible(field, () -> (short) field.get(null));
    }

    public short get() {
        return this.getter.getOrHandle();
    }

    private FiSupplier<Short> wrapAccessible(Field field, FiSupplier<Short> getter) {
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
