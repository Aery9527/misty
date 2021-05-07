package org.misty.util.reflect.method;

import java.lang.reflect.Method;

abstract class MethodAbstract {

    private final Method method;

    private final MethodStyle methodStyle;

    protected MethodAbstract(Method method, Object target) {
        this.method = method;
        this.methodStyle = MethodStyle.INSTANCE;
    }

    protected MethodAbstract(Method method) {
        this.method = method;
        this.methodStyle = MethodStyle.STATIC;
    }

    public Method getMethod() {
        return this.method;
    }

    public String getName() {
        return this.method.getName();
    }

    public Class<?> getDeclaringClass() {
        return this.method.getDeclaringClass();
    }

    public Class<?> getReturnType() {
        return this.method.getReturnType();
    }

    public MethodStyle getMethodStyle() {
        return methodStyle;
    }
}
