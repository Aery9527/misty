package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "OptionalUsedAsFieldOrParameterType"})
public class MethodExtractor {

    public static final Class<?>[] EMPTY_CLASSES = new Class<?>[]{};

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

    //

    public <ReturnType> MethodObjectInvoker<ReturnType> buildObjectInvoker(
            String name, Class<ReturnType> returnType, Class<?>... parameterTypes
    ) throws NoSuchMethodException {
        Method method = getMethod(name, returnType, parameterTypes);
        MethodStyle style = getStyle(method);
        return style.isStatic() ? new MethodObjectInvoker<>(method) : new MethodObjectInvoker<>(method, this.target.get());
    }

    public <ReturnType, Arg1> MethodObjectInvoker1<ReturnType, Arg1> buildObjectInvoker1(
            String name, Class<ReturnType> returnType, Class<Arg1> arg1Type
    ) throws NoSuchMethodException {
        Method method = getMethod(name, returnType, arg1Type);
        MethodStyle style = getStyle(method);
        return style.isStatic() ? new MethodObjectInvoker1<>(method) : new MethodObjectInvoker1<>(method, this.target.get());
    }

    public <ReturnType, Arg1, Arg2> MethodObjectInvoker2<ReturnType, Arg1, Arg2> buildObjectInvoker2(
            String name, Class<ReturnType> returnType, Class<Arg1> arg1Type, Class<Arg2> arg2Type
    ) throws NoSuchMethodException {
        Method method = getMethod(name, returnType, arg1Type, arg2Type);
        MethodStyle style = getStyle(method);
        return style.isStatic() ? new MethodObjectInvoker2<>(method) : new MethodObjectInvoker2<>(method, this.target.get());
    }

    //

    public MethodVoidInvoker buildVoidInvoker(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = getMethod(name, void.class, parameterTypes);
        MethodStyle style = getStyle(method);
        return style.isStatic() ? new MethodVoidInvoker(method) : new MethodVoidInvoker(method, this.target.get());
    }

    //

    private Method getMethod(String name, Class<?> returnType, Class<?>... parameterTypes) throws NoSuchMethodException {
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

    private MethodStyle getStyle(Method method) {
        int modifiers = method.getModifiers();
        return Modifier.isStatic(modifiers) ? MethodStyle.STATIC : MethodStyle.INSTANCE;
    }

}
