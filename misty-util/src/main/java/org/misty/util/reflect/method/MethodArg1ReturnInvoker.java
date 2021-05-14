package org.misty.util.reflect.method;

public class MethodArg1ReturnInvoker<ReturnType, Arg1> {

    private final MethodObjectInvoker<ReturnType> invoker;

    public MethodArg1ReturnInvoker(MethodObjectInvoker<ReturnType> invoker) {
        this.invoker = invoker;
    }

    public ReturnType invoke(Arg1 arg1) {
        return this.invoker.invoke(arg1);
    }

}
