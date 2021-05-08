package org.misty.util.reflect.method;

import org.misty.util.fi.FiBiFunction;

import java.lang.reflect.Method;
import java.util.function.BiFunction;

@SuppressWarnings("unchecked")
public class MethodObjectInvoker2<ReturnType, Arg1, Arg2> extends MethodAbstract {

    private final BiFunction<Arg1, Arg2, ReturnType> invoker;

    protected MethodObjectInvoker2(Method method, Object target) {
        super(method, target);
        this.invoker = wrapAccessible(method, (arg1, arg2) -> (ReturnType) method.invoke(target, arg1, arg2));
    }

    protected MethodObjectInvoker2(Method method) {
        super(method);
        this.invoker = wrapAccessible(method, (arg1, arg2) -> (ReturnType) method.invoke(null, arg1, arg2));
    }

    //

    public ReturnType invoke(Arg1 arg1, Arg2 arg2) {
        return this.invoker.apply(arg1, arg2);
    }

    private BiFunction<Arg1, Arg2, ReturnType> wrapAccessible(Method method, FiBiFunction<Arg1, Arg2, ReturnType> invoker) {
        return (arg1, arg2) -> {
            try {
                method.setAccessible(true);
                return invoker.applyOrHandle(arg1, arg2);
            } finally {
                method.setAccessible(false);
            }
        };
    }

}
