package org.misty.util.reflect.method;

import org.misty.util.fi.FiFunction;

import java.lang.reflect.Method;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class MethodObjectInvoker<ReturnType> extends MethodAbstract {

    private final Function<Object[], ReturnType> invoker;

    protected MethodObjectInvoker(Method method, Object target) {
        super(method, target);
        this.invoker = wrapAccessible(method, (parameters) -> (ReturnType) method.invoke(target, parameters));
    }

    protected MethodObjectInvoker(Method method) {
        super(method);
        this.invoker = wrapAccessible(method, (parameters) -> (ReturnType) method.invoke(null, parameters));
    }

    //

    public ReturnType invoke(Object... parameters) {
        return this.invoker.apply(parameters);
    }

    private Function<Object[], ReturnType> wrapAccessible(Method method, FiFunction<Object[], ReturnType> invoker) {
        return (parameters) -> {
            try {
                method.setAccessible(true);
                return invoker.applyOrHandle(parameters);
            } finally {
                method.setAccessible(false);
            }
        };
    }

}
