package org.misty.util.reflect.field;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Field;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class FieldObjectGetter<TargetType> extends FieldAbstract {

    private final Supplier<TargetType> getter;

    protected FieldObjectGetter(Field field, Object target) {
        super(field, target);
        this.getter = wrapAccessible(field, () -> (TargetType) field.get(target));
    }

    protected FieldObjectGetter(Field field) {
        super(field);
        this.getter = wrapAccessible(field, () -> (TargetType) field.get(null));
    }

    public TargetType get() {
        return this.getter.get();
    }

    private Supplier<TargetType> wrapAccessible(Field field, FiSupplier<TargetType> getter) {
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
