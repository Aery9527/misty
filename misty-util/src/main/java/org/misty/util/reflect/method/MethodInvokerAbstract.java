package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.util.Optional;

public abstract class MethodInvokerAbstract implements MethodInvoker {

    private final Method method;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<Object> target;

    private final MethodStyle methodStyle;

    protected MethodInvokerAbstract(Method method, Object target) {
        this.method = method;
        this.target = Optional.of(target);

        Class<?> declaringClass = method.getDeclaringClass();
        Class<?> targetClass = target.getClass();
        if (declaringClass.equals(targetClass)) {
            this.methodStyle = MethodStyle.INSTANCE;
        } else {
            this.methodStyle = MethodStyle.ANCESTOR;
        }
    }

    protected MethodInvokerAbstract(Method method) {
        this.method = method;
        this.target = Optional.empty();
        this.methodStyle = MethodStyle.STATIC;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Optional<Object> getTarget() {
        return target;
    }

    @Override
    public MethodStyle getMethodStyle() {
        return methodStyle;
    }

}
