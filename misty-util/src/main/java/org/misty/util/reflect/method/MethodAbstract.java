package org.misty.util.reflect.method;

import org.misty.util.error.MistyErrorUtil;
import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Method;
import java.util.Optional;

public abstract class MethodAbstract {

    private final Method method;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<Object> target;

    private final MethodStyle methodStyle;

    protected MethodAbstract(Method method, Object target) {
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

    protected MethodAbstract(Method method) {
        this.method = method;
        this.target = Optional.empty();
        this.methodStyle = MethodStyle.STATIC;
    }

    protected FiConsumer<Object[]> wrapAccessible(Method method, FiConsumer<Object[]> invoker) {
        return (parameters) -> {
            try {
                method.setAccessible(true);
                invoker.acceptOrHandle(parameters);
            } catch (Throwable t) {
                throw MistyErrorUtil.ignoreProxyException(t);
            } finally {
                method.setAccessible(false);
            }
        };
    }

    public Method getMethod() {
        return this.method;
    }

    public Optional<Object> getTarget() {
        return target;
    }

    public MethodStyle getMethodStyle() {
        return methodStyle;
    }

}
