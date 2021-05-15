package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.util.Optional;

public class MethodArg2VoidInvoker<Arg1, Arg2> implements MethodInvoker {

    private final MethodVoidInvoker invoker;

    public MethodArg2VoidInvoker(MethodVoidInvoker invoker) {
        this.invoker = invoker;
    }

    public void invoke(Arg1 arg1, Arg2 arg2) {
        this.invoker.invoke(arg1, arg2);
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
