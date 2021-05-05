package org.misty.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Optional;

public class FieldExtractor {

    public static class ErrorMessage {
        public static final String ERROR_STYLE_OPERATING = "can't get/set instance field without target.";
        public static final String ERROR_FIELD_TYPE = "field \"%s\" is %s not %s.";
        public static final String FIELD_IS_FINAL = "field \"%s\" is final, can't build setter.";
    }

    public enum Style {
        STATIC, INSTANCE;
    }

    private final Optional<Object> target;

    private final Class<?> clazz;

    public FieldExtractor(Object target) {
        Objects.requireNonNull(target);

        this.target = Optional.of(target);
        this.clazz = target.getClass();
    }

    public FieldExtractor(Class<?> clazz) {
        Objects.requireNonNull(clazz);

        this.target = Optional.empty();
        this.clazz = clazz;
    }

    public <FieldType> FieldObjectOperator<FieldType> buildOperator(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getField(name, fieldType, true);
        return this.target.<FieldObjectOperator<FieldType>>map(
                o -> new FieldObjectOperator<>(field, o)
        ).orElseGet(
                () -> new FieldObjectOperator<>(field)
        );
    }

    public <FieldType> FieldObjectGetter<FieldType> buildGetter(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getField(name, fieldType, false);
        return this.target.<FieldObjectGetter<FieldType>>map(
                o -> new FieldObjectGetter<>(field, o)
        ).orElseGet(
                () -> new FieldObjectGetter<>(field)
        );
    }

    public <FieldType> FieldObjectSetter<FieldType> buildSetter(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getField(name, fieldType, true);
        return this.target.<FieldObjectSetter<FieldType>>map(
                o -> new FieldObjectSetter<>(field, o)
        ).orElseGet(
                () -> new FieldObjectSetter<>(field)
        );
    }

    private <FieldType> Field getField(String name, Class<FieldType> fieldType, boolean checkCanSet) throws NoSuchFieldException {
        Field field = this.clazz.getDeclaredField(name);

        int modifiers = field.getModifiers();
        boolean isInstanceField = !Modifier.isStatic(modifiers);
        boolean hasInstance = this.target.isPresent();
        if (isInstanceField && !hasInstance) {
            throw new NoSuchFieldException(ErrorMessage.ERROR_STYLE_OPERATING);
        }

        Class<?> declaredType = field.getType();
        if (!fieldType.isAssignableFrom(declaredType)) {
            throw new NoSuchFieldException(String.format(ErrorMessage.ERROR_FIELD_TYPE, name, declaredType, fieldType));
        }

        if (checkCanSet && Modifier.isFinal(modifiers)) {
            throw new NoSuchFieldException(String.format(ErrorMessage.FIELD_IS_FINAL, name));
        }

        return field;
    }

}
