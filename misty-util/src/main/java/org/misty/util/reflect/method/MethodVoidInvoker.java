package org.misty.util.reflect.method;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Method;

public class MethodVoidInvoker extends MethodAbstract {

    private final FiConsumer<Object[]> invoker;

    protected MethodVoidInvoker(Method method, Object target) {
        super(method, target);
        this.invoker = super.wrapAccessible(method, (parameters) -> method.invoke(target, parameters));
    }

    protected MethodVoidInvoker(Method method) {
        super(method);
        this.invoker = super.wrapAccessible(method, (parameters) -> method.invoke(null, parameters));
    }

    public void invoke(Object... parameters) {
        this.invoker.acceptOrHandle(parameters);
    }

}
