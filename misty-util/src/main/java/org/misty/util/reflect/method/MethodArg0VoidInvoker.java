package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.util.Optional;

public class MethodArg0VoidInvoker implements MethodInvoker {

    private final MethodVoidInvoker invoker;

    public MethodArg0VoidInvoker(MethodVoidInvoker invoker) {
        this.invoker = invoker;
    }

    public void invoke() {
        this.invoker.invoke();
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
