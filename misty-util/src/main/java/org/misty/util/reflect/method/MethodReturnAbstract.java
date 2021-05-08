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

    public MethodArg0ReturnInvoker<ReturnType> toArgInvoker() {
        // do check
        return new MethodArg0ReturnInvoker<>(this);
    }

    public <Arg1> MethodArg1ReturnInvoker<ReturnType, Arg1> toArgInvoker(Class<Arg1> arg1Type) {
        // do check
        return new MethodArg1ReturnInvoker<>(this);
    }

    public <Arg1, Arg2> MethodArg2ReturnInvoker<ReturnType, Arg1, Arg2> toArgInvoker(Class<Arg1> arg1Type, Class<Arg2> arg2Type) {
        // do check
        return new MethodArg2ReturnInvoker<>(this);
    }

}
