package org.misty.util.reflect;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Field;

public class FieldFloatGetter extends FieldObjectAbstract {

    private final FiSupplier<Float> getter;

    protected FieldFloatGetter(Field field, Object target) {
        super(field, target);
        this.getter = wrapAccessible(field, () -> (float) field.get(target));
    }

    protected FieldFloatGetter(Field field) {
        super(field);
        this.getter = wrapAccessible(field, () -> (float) field.get(null));
    }

    public float get() {
        return this.getter.getOrHandle();
    }

    private FiSupplier<Float> wrapAccessible(Field field, FiSupplier<Float> getter) {
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
