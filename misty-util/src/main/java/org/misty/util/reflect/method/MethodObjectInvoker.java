package org.misty.util.reflect.method;

import org.misty.util.error.MistyErrorUtil;
import org.misty.util.fi.FiFunction;

import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class MethodObjectInvoker<ReturnType> extends MethodInvokerAbstract {

    private final FiFunction<Object[], ReturnType> invoker;

    protected MethodObjectInvoker(Method method, Object target) {
        super(method, target);
        this.invoker = wrapAccessible(method, (parameters) -> (ReturnType) method.invoke(target, parameters));
    }

    protected MethodObjectInvoker(Method method) {
        super(method);
        this.invoker = wrapAccessible(method, (parameters) -> (ReturnType) method.invoke(null, parameters));
    }

    public ReturnType invoke(Object... parameters) {
        return this.invoker.applyOrHandle(parameters);
    }

    private FiFunction<Object[], ReturnType> wrapAccessible(Method method, FiFunction<Object[], ReturnType> invoker) {
        return (parameters) -> {
            try {
                method.setAccessible(true);
                return invoker.applyOrHandle(parameters);
            } catch (Throwable t) {
                throw MistyErrorUtil.ignoreProxyException(t);
            } finally {
                method.setAccessible(false);
            }
        };
    }
}
