package org.misty.util.reflect.method;

import org.misty.util.fi.FiFunction;

import java.lang.reflect.Method;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class MethodObjectInvoker1<ReturnType, Arg1> extends MethodAbstract {

    private final Function<Arg1, ReturnType> invoker;

    protected MethodObjectInvoker1(Method method, Object target) {
        super(method, target);
        this.invoker = wrapAccessible(method, (arg1) -> (ReturnType) method.invoke(target, arg1));
    }

    protected MethodObjectInvoker1(Method method) {
        super(method);
        this.invoker = wrapAccessible(method, (arg1) -> (ReturnType) method.invoke(null, arg1));
    }

    //

    public ReturnType invoke(Arg1 arg1) {
        return this.invoker.apply(arg1);
    }

    private Function<Arg1, ReturnType> wrapAccessible(Method method, FiFunction<Arg1, ReturnType> invoker) {
        return (arg1) -> {
            try {
                method.setAccessible(true);
                return invoker.applyOrHandle(arg1);
            } finally {
                method.setAccessible(false);
            }
        };
    }

}
