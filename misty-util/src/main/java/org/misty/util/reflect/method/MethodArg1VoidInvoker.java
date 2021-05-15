package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.util.Optional;

public class MethodArg1VoidInvoker<Arg1> implements MethodInvoker {

    private final MethodVoidInvoker invoker;

    public MethodArg1VoidInvoker(MethodVoidInvoker invoker) {
        this.invoker = invoker;
    }

    public void invoke(Arg1 arg1) {
        this.invoker.invoke(arg1);
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
