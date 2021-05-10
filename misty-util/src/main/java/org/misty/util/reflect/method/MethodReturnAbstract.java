package org.misty.util.reflect.method;

import java.lang.reflect.Method;

public abstract class MethodReturnAbstract<ReturnType> extends MethodAbstract {

    public MethodReturnAbstract(Method method, Object target) {
        super(method, target);
    }

    public MethodReturnAbstract(Method method) {
        super(method);
    }

    public abstract ReturnType invoke(Object... parameters);

}
