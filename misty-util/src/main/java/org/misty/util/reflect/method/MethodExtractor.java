package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class MethodExtractor {

    public static class Message {
        public static final String ERROR_RETURN_TYPE = "method \"%s\" return type is \"%s\" not \"%s\".";
        public static final String OPERATING_INSTANCE_WITHOUT_TARGET = "can't build invoker of method \"%s\" without instance.";
    }

    private final Class<?>[] EMPTY_PARAMETER_TYPES = new Class<?>[]{};

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
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

    public <Type1, Type2 extends Type1> MethodExtractor(Class<? super Type1> accessibleType, Type2 target) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(accessibleType);

        this.target = Optional.of(target);
        this.clazz = accessibleType;
    }

    //

    public MethodArg0VoidInvoker buildVoidInvoker(String name) throws NoSuchMethodException {
        MethodVoidInvoker invoker = buildVoidInvoker(name, EMPTY_PARAMETER_TYPES);
        return new MethodArg0VoidInvoker(invoker);
    }

    public <Arg1> MethodArg1VoidInvoker<Arg1> buildVoidInvoker(String name, Class<Arg1> arg1Type) throws NoSuchMethodException {
        MethodVoidInvoker invoker = buildVoidInvoker(name, new Class<?>[]{arg1Type});
        return new MethodArg1VoidInvoker<>(invoker);
    }

    public <Arg1, Arg2> MethodArg2VoidInvoker<Arg1, Arg2> buildVoidInvoker(
            String name, Class<Arg1> arg1Type, Class<Arg2> arg2Type
    ) throws NoSuchMethodException {
        MethodVoidInvoker invoker = buildVoidInvoker(name, new Class<?>[]{arg1Type, arg2Type});
        return new MethodArg2VoidInvoker<>(invoker);
    }

    public MethodVoidInvoker buildVoidInvoker(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = getMethodAndCheck(name, void.class, parameterTypes);
        boolean isStaticField = Modifier.isStatic(method.getModifiers());
        return isStaticField ? new MethodVoidInvoker(method) : new MethodVoidInvoker(method, this.target.get());
    }

    //

    public <ReturnType> MethodArg0ReturnInvoker<ReturnType> buildObjectInvoker(
            String name, Class<ReturnType> returnType
    ) throws NoSuchMethodException {
        MethodObjectInvoker<ReturnType> invoker = buildObjectInvoker(name, returnType, EMPTY_PARAMETER_TYPES);
        return new MethodArg0ReturnInvoker<>(invoker);
    }

    public <ReturnType, Arg1> MethodArg1ReturnInvoker<ReturnType, Arg1> buildObjectInvoker(
            String name, Class<ReturnType> returnType, Class<Arg1> arg1Type
    ) throws NoSuchMethodException {
        MethodObjectInvoker<ReturnType> invoker = buildObjectInvoker(name, returnType, new Class<?>[]{arg1Type});
        return new MethodArg1ReturnInvoker<>(invoker);
    }

    public <ReturnType, Arg1, Arg2> MethodArg2ReturnInvoker<ReturnType, Arg1, Arg2> buildObjectInvoker(
            String name, Class<ReturnType> returnType, Class<Arg1> arg1Type, Class<Arg2> arg2Type
    ) throws NoSuchMethodException {
        MethodObjectInvoker<ReturnType> invoker = buildObjectInvoker(name, returnType, new Class<?>[]{arg1Type, arg2Type});
        return new MethodArg2ReturnInvoker<>(invoker);
    }

    public <ReturnType> MethodObjectInvoker<ReturnType> buildObjectInvoker(
            String name, Class<ReturnType> returnType, Class<?>... parameterTypes
    ) throws NoSuchMethodException {
        Method method = getMethodAndCheck(name, returnType, parameterTypes);
        boolean isStaticField = Modifier.isStatic(method.getModifiers());
        return isStaticField ? new MethodObjectInvoker<>(method) : new MethodObjectInvoker<>(method, this.target.get());
    }

    //

    private Method getMethodAndCheck(String name, Class<?> returnType, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = this.clazz.getDeclaredMethod(name, parameterTypes);

        Class<?> actuallyReturnType = method.getReturnType();
        if (!returnType.isAssignableFrom(actuallyReturnType)) {
            throw new NoSuchMethodException(String.format(Message.ERROR_RETURN_TYPE, name, actuallyReturnType.getName(), returnType.getName()));
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
