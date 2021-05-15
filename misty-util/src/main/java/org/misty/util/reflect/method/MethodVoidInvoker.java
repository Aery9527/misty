package org.misty.util.reflect.method;

import org.misty.util.error.MistyErrorUtil;
import org.misty.util.fi.FiConsumer;

import java.lang.reflect.Method;

public class MethodVoidInvoker extends MethodInvokerAbstract {

    private final FiConsumer<Object[]> invoker;

    protected MethodVoidInvoker(Method method, Object target) {
        super(method, target);
        this.invoker = wrapAccessible(method, (parameters) -> method.invoke(target, parameters));
    }

    protected MethodVoidInvoker(Method method) {
        super(method);
        this.invoker = wrapAccessible(method, (parameters) -> method.invoke(null, parameters));
    }

    public void invoke(Object... parameters) {
        this.invoker.acceptOrHandle(parameters);
    }

    private FiConsumer<Object[]> wrapAccessible(Method method, FiConsumer<Object[]> invoker) {
        return (parameters) -> {
            try {
                method.setAccessible(true);
                invoker.acceptOrHandle(parameters);
            } catch (Throwable t) {
                throw MistyErrorUtil.ignoreProxyException(t);
            } finally {
                method.setAccessible(false);
            }
        };
    }

}
