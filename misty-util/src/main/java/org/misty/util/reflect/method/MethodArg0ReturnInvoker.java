package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.util.Optional;

public class MethodArg0ReturnInvoker<ReturnType> implements MethodInvoker {

    private final MethodObjectInvoker<ReturnType> invoker;

    public MethodArg0ReturnInvoker(MethodObjectInvoker<ReturnType> invoker) {
        super();
        this.invoker = invoker;
    }

    public ReturnType invoke() {
        return this.invoker.invoke();
    }

    @Override
    public Method getMethod() {
        return this.invoker.getMethod();
    }

    @Override
    public Optional<Object> getTarget() {
        return this.invoker.getTarget();
    }

    @Override
    public MethodStyle getMethodStyle() {
        return this.invoker.getMethodStyle();
    }
}
