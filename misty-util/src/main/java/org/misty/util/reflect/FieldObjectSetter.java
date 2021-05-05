package org.misty.util.reflect;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Field;
import java.util.function.Consumer;

public class FieldObjectSetter<TargetType> extends FieldObjectAbstract<TargetType> {

    private final Consumer<TargetType> setter;

    protected FieldObjectSetter(Field field, Object target) {
        super(field, target);
        this.setter = wrapAccessible(field, (o) -> field.set(target, o));
    }

    protected FieldObjectSetter(Field field) {
        super(field);
        this.setter = wrapAccessible(field, (o) -> field.set(null, o));
    }

    public void set(TargetType target) {
        this.setter.accept(target);
    }

    private Consumer<TargetType> wrapAccessible(Field field, FiConsumer<TargetType> setter) {
        return (target) -> {
            try {
                field.setAccessible(true);
                setter.acceptOrHandle(target);
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
