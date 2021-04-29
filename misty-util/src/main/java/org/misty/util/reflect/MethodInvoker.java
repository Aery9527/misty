package org.misty.util.reflect;

import org.misty.util.fi.FiRunnable;
import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

@SuppressWarnings("ALL")
public class MethodInvoker {

    public static final String CANT_FIND_INSTANCE_METHOD_MSG = "not \"instance\" method of ";

    public static final String CANT_FIND_STATIC_METHOD_MSG = "not \"static\" method of ";

    public static final String CANT_FIND_VOID_METHOD_MSG = "not \"no return\" method of ";

    public static final String CANT_FIND_RETURNED_METHOD_MSG = "not return \"%s\" method of ";

    private final Optional<Object> target;

    private final Class<?> clazz;

    public MethodInvoker(Object target) {
        this.target = Optional.of(target);
        this.clazz = target.getClass();
    }

    public MethodInvoker(Class<?> clazz) {
        this.target = Optional.empty();
        this.clazz = clazz;
    }

    public VoidMethod find(String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = this.clazz.getMethod(methodName, parameterTypes);

        checkModifier(method);

        Class<?> methodReturnType = method.getReturnType();
        if (!void.class.equals(methodReturnType)) {
            throw new NoSuchMethodException(CANT_FIND_VOID_METHOD_MSG + method);
        }

        return new VoidMethod(this.target, method);
    }

    public <ReturnType> ReturnMethod<ReturnType> find(
            Class<ReturnType> returnType, String methodName, Class<?>... parameterTypes
    ) throws NoSuchMethodException {
        Method method = this.clazz.getMethod(methodName, parameterTypes);

        checkModifier(method);

        Class<?> methodReturnType = method.getReturnType();
        if (!returnType.isAssignableFrom(methodReturnType)) {
            throw new NoSuchMethodException(String.format(CANT_FIND_RETURNED_METHOD_MSG, returnType.getName()) + method);
        }

        return new ReturnMethod<>(this.target, method);
    }

    private void checkModifier(Method method) throws NoSuchMethodException {
        int modifiers = method.getModifiers();
        if (this.target.isPresent() && Modifier.isStatic(modifiers)) {
            throw new NoSuchMethodException(CANT_FIND_INSTANCE_METHOD_MSG + method);
        } else if (!this.target.isPresent() && !Modifier.isStatic(modifiers)) {
            throw new NoSuchMethodException(CANT_FIND_STATIC_METHOD_MSG + method);
        }
    }

    public static class TargetMethod {

        private final Optional<Object> target;

        private final Method method;

        public TargetMethod(Optional<Object> target, Method method) {
            this.target = target;
            this.method = method;
        }

        public Optional<Object> getTarget() {
            return target;
        }

        public Method getMethod() {
            return method;
        }
    }

    public static class VoidMethod extends TargetMethod {

        public VoidMethod(Optional<Object> target, Method method) {
            super(target, method);
        }

        public void invoke(Object... args) {
            FiRunnable action = () -> {
                Optional<Object> target = super.getTarget();
                Method method = super.getMethod();

                if (target.isPresent()) {
                    method.invoke(target.get(), args);
                } else {
                    method.invoke(null, args);
                }
            };
            action.runOrHandle();
        }
    }

    public static class ReturnMethod<ReturnType> extends TargetMethod {

        public ReturnMethod(Optional<Object> target, Method method) {
            super(target, method);
        }

        @SuppressWarnings("unchecked")
        public ReturnType invoke(Object... args) {
            FiSupplier<ReturnType> action = () -> {
                Optional<Object> target = super.getTarget();
                Method method = super.getMethod();

                if (target.isPresent()) {
                    return (ReturnType) method.invoke(target.get(), args);
                } else {
                    return (ReturnType) method.invoke(null, args);
                }
            };
            return action.getOrHandle();
        }
    }

}
