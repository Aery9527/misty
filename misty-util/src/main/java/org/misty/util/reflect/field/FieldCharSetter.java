package org.misty.util.reflect.field;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Field;

public class FieldCharSetter extends FieldAbstract {

    private final FiConsumer<Character> setter;

    protected FieldCharSetter(Field field, Object target) {
        super(field, target);
        this.setter = wrapAccessible(field, (b) -> field.set(target, b));
    }

    protected FieldCharSetter(Field field) {
        super(field);
        this.setter = wrapAccessible(field, (b) -> field.set(null, b));
    }

    public void set(char target) {
        this.setter.acceptOrHandle(target);
    }

    private FiConsumer<Character> wrapAccessible(Field field, FiConsumer<Character> setter) {
        return (b) -> {
            try {
                field.setAccessible(true);
                setter.acceptOrHandle(b);
            } finally {
                field.setAccessible(false);
            }
        };
    }

}
