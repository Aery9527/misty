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

    public MethodArg0VoidInvoker toArgInvoker() {
        // do check
        return new MethodArg0VoidInvoker(this);
    }

    public <Arg1> MethodArg1VoidInvoker<Arg1> toArgInvoker(Class<Arg1> arg1Type) {
        // do check
        return new MethodArg1VoidInvoker<>(this);
    }

    public <Arg1, Arg2> MethodArg2VoidInvoker<Arg1, Arg2> toArgInvoker(Class<Arg1> arg1Type, Class<Arg2> arg2Type) {
        // do check
        return new MethodArg2VoidInvoker<>(this);
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
