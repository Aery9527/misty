package org.misty.util.reflect.field;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Field;

public class FieldCharGetter extends FieldAbstract {

    private final FiSupplier<Character> getter;

    protected FieldCharGetter(Field field, Object target) {
        super(field, target);
        this.getter = wrapAccessible(field, () -> (char) field.get(target));
    }

    protected FieldCharGetter(Field field) {
        super(field);
        this.getter = wrapAccessible(field, () -> (char) field.get(null));
    }

    public char get() {
        return this.getter.getOrHandle();
    }

    private FiSupplier<Character> wrapAccessible(Field field, FiSupplier<Character> getter) {
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
