package org.misty.util.reflect.method;

import org.misty.util.error.MistyErrorUtil;
import org.misty.util.fi.FiFunction;

import java.lang.reflect.Method;

public abstract class MethodReturnAbstract<ReturnType> extends MethodAbstract {

    public MethodReturnAbstract(Method method, Object target) {
        super(method, target);
    }

    public MethodReturnAbstract(Method method) {
        super(method);
    }

    public abstract ReturnType invoke(Object... parameters);

    protected FiFunction<Object[], ReturnType> wrapAccessible(Method method, FiFunction<Object[], ReturnType> invoker) {
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
