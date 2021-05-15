package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.util.Optional;

public class MethodArg1ReturnInvoker<ReturnType, Arg1> implements MethodInvoker {

    private final MethodObjectInvoker<ReturnType> invoker;

    public MethodArg1ReturnInvoker(MethodObjectInvoker<ReturnType> invoker) {
        this.invoker = invoker;
    }

    public ReturnType invoke(Arg1 arg1) {
        return this.invoker.invoke(arg1);
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
