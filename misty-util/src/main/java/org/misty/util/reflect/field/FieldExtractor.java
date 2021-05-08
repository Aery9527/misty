package org.misty.util.reflect.field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "OptionalUsedAsFieldOrParameterType"})
public class FieldExtractor {

    public static class Message {
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

    public <Type1, Type2 extends Type1> FieldExtractor(Class<Type1> accessibleType, Type2 target) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(accessibleType);

        this.target = Optional.of(target);
        this.clazz = accessibleType;
    }

    //

    public <FieldType> FieldObjectGetter<FieldType> buildGetter(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, fieldType, false);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldObjectGetter<>(field) : new FieldObjectGetter<>(field, this.target.get());
    }

    public <FieldType> FieldObjectSetter<FieldType> buildSetter(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, fieldType, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldObjectSetter<>(field) : new FieldObjectSetter<>(field, this.target.get());
    }

    public <FieldType> FieldObjectOperator<FieldType> buildOperator(String name, Class<FieldType> fieldType) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, fieldType, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldObjectOperator<>(field) : new FieldObjectOperator<>(field, this.target.get());
    }

    //

    public FieldByteGetter buildByteGetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, byte.class, false);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldByteGetter(field) : new FieldByteGetter(field, this.target.get());
    }

    public FieldByteSetter buildByteSetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, byte.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldByteSetter(field) : new FieldByteSetter(field, this.target.get());
    }

    public FieldByteOperator buildByteOperator(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, byte.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldByteOperator(field) : new FieldByteOperator(field, this.target.get());
    }

    //

    public FieldCharGetter buildCharGetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, char.class, false);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldCharGetter(field) : new FieldCharGetter(field, this.target.get());
    }

    public FieldCharSetter buildCharSetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, char.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldCharSetter(field) : new FieldCharSetter(field, this.target.get());
    }

    public FieldCharOperator buildCharOperator(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, char.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldCharOperator(field) : new FieldCharOperator(field, this.target.get());
    }

    //

    public FieldDoubleGetter buildDoubleGetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, double.class, false);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldDoubleGetter(field) : new FieldDoubleGetter(field, this.target.get());
    }

    public FieldDoubleSetter buildDoubleSetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, double.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldDoubleSetter(field) : new FieldDoubleSetter(field, this.target.get());
    }

    public FieldDoubleOperator buildDoubleOperator(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, double.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldDoubleOperator(field) : new FieldDoubleOperator(field, this.target.get());
    }

    //

    public FieldFloatGetter buildFloatGetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, float.class, false);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldFloatGetter(field) : new FieldFloatGetter(field, this.target.get());
    }

    public FieldFloatSetter buildFloatSetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, float.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldFloatSetter(field) : new FieldFloatSetter(field, this.target.get());
    }

    public FieldFloatOperator buildFloatOperator(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, float.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldFloatOperator(field) : new FieldFloatOperator(field, this.target.get());
    }

    //

    public FieldIntGetter buildIntGetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, int.class, false);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldIntGetter(field) : new FieldIntGetter(field, this.target.get());
    }

    public FieldIntSetter buildIntSetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, int.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldIntSetter(field) : new FieldIntSetter(field, this.target.get());
    }

    public FieldIntOperator buildIntOperator(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, int.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldIntOperator(field) : new FieldIntOperator(field, this.target.get());
    }

    //

    public FieldLongGetter buildLongGetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, long.class, false);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldLongGetter(field) : new FieldLongGetter(field, this.target.get());
    }

    public FieldLongSetter buildLongSetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, long.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldLongSetter(field) : new FieldLongSetter(field, this.target.get());
    }

    public FieldLongOperator buildLongOperator(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, long.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldLongOperator(field) : new FieldLongOperator(field, this.target.get());
    }

    //

    public FieldShortGetter buildShortGetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, short.class, false);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldShortGetter(field) : new FieldShortGetter(field, this.target.get());
    }

    public FieldShortSetter buildShortSetter(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, short.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldShortSetter(field) : new FieldShortSetter(field, this.target.get());
    }

    public FieldShortOperator buildShortOperator(String name) throws NoSuchFieldException {
        Field field = getFieldAndCheck(name, short.class, true);
        boolean isStaticField = Modifier.isStatic(field.getModifiers());
        return isStaticField ? new FieldShortOperator(field) : new FieldShortOperator(field, this.target.get());
    }

    //

    private <FieldType> Field getFieldAndCheck(String name, Class<FieldType> fieldType, boolean checkCanSet) throws NoSuchFieldException {
        Field field = this.clazz.getDeclaredField(name);
        int modifiers = field.getModifiers();

        Class<?> declaredType = field.getType();
        if (!fieldType.isAssignableFrom(declaredType)) {
            throw new NoSuchFieldException(String.format(Message.ERROR_FIELD_TYPE, name, declaredType, fieldType));
        }

        if (checkCanSet && Modifier.isFinal(modifiers)) {
            throw new NoSuchFieldException(String.format(Message.FIELD_IS_FINAL, name));
        }

        boolean isInstanceField = !Modifier.isStatic(modifiers);
        boolean isClassTarget = !this.target.isPresent();
        if (isInstanceField && isClassTarget) {
            throw new NoSuchFieldException(String.format(Message.OPERATING_INSTANCE_WITHOUT_TARGET, name));
        }

        return field;
    }

}
