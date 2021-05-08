package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "OptionalUsedAsFieldOrParameterType"})
public class MethodExtractor {

    public static class Message {
        public static final String ERROR_RETURN_TYPE = "field \"%s\" return type is %s not %s.";
        public static final String OPERATING_INSTANCE_WITHOUT_TARGET = "can't build invoker of field \"%s\" without instance.";
    }

    private final Optional<Object> target;

    private final Class<?> clazz;

    public MethodExtractor(Object target) {
        Objects.requireNonNull(target);

        this.target = Optional.of(target);
        this.clazz = target.getClass();
    }

    public MethodExtractor(Class<?> clazz) {
        Objects.requireNonNull(clazz);

        this.target = Optional.empty();
        this.clazz = clazz;
    }

    public <Type1, Type2 extends Type1> MethodExtractor(Class<Type1> accessibleType, Type2 target) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(accessibleType);

        this.target = Optional.of(target);
        this.clazz = accessibleType;
    }

    public MethodVoidInvoker buildVoidInvoker(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = getMethodAndCheck(name, void.class, parameterTypes);
        boolean isStaticMethod = Modifier.isStatic(method.getModifiers());
        return isStaticMethod ? new MethodVoidInvoker(method) : new MethodVoidInvoker(method, this.target.get());
    }

    public <ReturnType> MethodObjectInvoker<ReturnType> buildObjectInvoker(
            String name, Class<ReturnType> returnType, Class<?>... parameterTypes
    ) throws NoSuchMethodException {
        Method method = getMethodAndCheck(name, returnType, parameterTypes);
        boolean isStaticMethod = Modifier.isStatic(method.getModifiers());
        return isStaticMethod ? new MethodObjectInvoker<>(method) : new MethodObjectInvoker<>(method, this.target.get());
    }

    //

    private Method getMethodAndCheck(String name, Class<?> returnType, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = this.clazz.getDeclaredMethod(name, parameterTypes);

        Class<?> actuallyReturnType = method.getReturnType();
        if (!returnType.isAssignableFrom(actuallyReturnType)) {
            throw new NoSuchMethodException(String.format(Message.ERROR_RETURN_TYPE, name, actuallyReturnType, returnType));
        }

        int modifiers = method.getModifiers();
        boolean isInstanceMethod = !Modifier.isStatic(modifiers);
        boolean isClassTarget = !this.target.isPresent();
        if (isInstanceMethod && isClassTarget) {
            throw new NoSuchMethodException(String.format(Message.OPERATING_INSTANCE_WITHOUT_TARGET, name));
        }

        return method;
    }

}
