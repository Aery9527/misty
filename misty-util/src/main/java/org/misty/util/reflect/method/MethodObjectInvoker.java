package org.misty.util.reflect.method;

import org.misty.util.fi.FiFunction;

import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class MethodObjectInvoker<ReturnType> extends MethodReturnAbstract<ReturnType> {

    private final FiFunction<Object[], ReturnType> invoker;

    protected MethodObjectInvoker(Method method, Object target) {
        super(method, target);
        this.invoker = wrapAccessible(method, (parameters) -> (ReturnType) method.invoke(target, parameters));
    }

    protected MethodObjectInvoker(Method method) {
        super(method);
        this.invoker = wrapAccessible(method, (parameters) -> (ReturnType) method.invoke(null, parameters));
    }

    //

    @Override
    public ReturnType invoke(Object... parameters) {
        return this.invoker.applyOrHandle(parameters);
    }


}
