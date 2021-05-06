package org.misty.util.reflect;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Field;

public class FieldByteGetter extends FieldObjectAbstract {

    private final FiSupplier<Byte> getter;

    protected FieldByteGetter(Field field, Object target) {
        super(field, target);
        this.getter = wrapAccessible(field, () -> (byte) field.get(target));
    }

    protected FieldByteGetter(Field field) {
        super(field);
        this.getter = wrapAccessible(field, () -> (byte) field.get(null));
    }

    public byte get() {
        return this.getter.getOrHandle();
    }

    private FiSupplier<Byte> wrapAccessible(Field field, FiSupplier<Byte> getter) {
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
