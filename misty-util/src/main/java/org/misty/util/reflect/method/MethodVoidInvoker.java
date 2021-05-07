package org.misty.util.reflect.method;

import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class MethodVoidInvoker extends MethodAbstract {

    private final Consumer<Object[]> invoker;

    protected MethodVoidInvoker(Method method, Object target) {
        super(method, target);
        this.invoker = wrapAccessible(method, (parameters) -> method.invoke(target, parameters));
    }

    protected MethodVoidInvoker(Method method) {
        super(method);
        this.invoker = wrapAccessible(method, (parameters) -> method.invoke(null, parameters));
    }

    //

    public void invoke(Object... parameters) {
        this.invoker.accept(parameters);
    }

    private Consumer<Object[]> wrapAccessible(Method method, FiConsumer<Object[]> invoker) {
        return (parameters) -> {
            try {
                method.setAccessible(true);
                invoker.acceptOrHandle(parameters);
            } finally {
                method.setAccessible(false);
            }
        };
    }

}
