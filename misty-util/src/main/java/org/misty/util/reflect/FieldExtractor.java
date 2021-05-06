package org.misty.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "OptionalUsedAsFieldOrParameterType"})
public class FieldExtractor {

    public static class ErrorMessage {
        public static final String ERROR_FIELD_TYPE = "field \"%s\" is %s not %s.";
        public static final String FIELD_IS_FINAL = "the field \"%s\" is final, only can build getter.";
        public static final String OPERATING_INSTANCE_WITHOUT_TARGET = "can't build getter/setter of field \"%s\" without instance.";
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

    public <FieldType> FieldObjectGetter<FieldType> buildGetter(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getField(name, fieldType, false);
        FieldStyle style = getStyle(field);
        return style.isStatic() ? new FieldObjectGetter<>(field) : new FieldObjectGetter<>(field, this.target.get());
    }

    public <FieldType> FieldObjectSetter<FieldType> buildSetter(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getField(name, fieldType, true);
        FieldStyle style = getStyle(field);
        return style.isStatic() ? new FieldObjectSetter<>(field) : new FieldObjectSetter<>(field, this.target.get());
    }

    public <FieldType> FieldObjectOperator<FieldType> buildOperator(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getField(name, fieldType, true);
        FieldStyle style = getStyle(field);
        return style.isStatic() ? new FieldObjectOperator<>(field) : new FieldObjectOperator<>(field, this.target.get());
    }

    private <FieldType> Field getField(String name, Class<FieldType> fieldType, boolean checkCanSet) throws NoSuchFieldException {
        Field field = this.clazz.getDeclaredField(name);
        int modifiers = field.getModifiers();

        Class<?> declaredType = field.getType();
        if (!fieldType.isAssignableFrom(declaredType)) {
            throw new NoSuchFieldException(String.format(ErrorMessage.ERROR_FIELD_TYPE, name, declaredType, fieldType));
        }

        if (checkCanSet && Modifier.isFinal(modifiers)) {
            throw new NoSuchFieldException(String.format(ErrorMessage.FIELD_IS_FINAL, name));
        }

        boolean isInstanceField = !Modifier.isStatic(modifiers);
        boolean isClassTarget = !this.target.isPresent();
        if (isInstanceField && isClassTarget) {
            throw new NoSuchFieldException(String.format(ErrorMessage.OPERATING_INSTANCE_WITHOUT_TARGET, name));
        }

        return field;
    }

    private FieldStyle getStyle(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isStatic(modifiers) ? FieldStyle.STATIC : FieldStyle.INSTANCE;
    }

}
