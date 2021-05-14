package org.misty.util.reflect.method;

public class MethodArg2ReturnInvoker<ReturnType, Arg1, Arg2> {

    private final MethodObjectInvoker<ReturnType> invoker;

    public MethodArg2ReturnInvoker(MethodObjectInvoker<ReturnType> invoker) {
        this.invoker = invoker;
    }

    public ReturnType invoke(Arg1 arg1, Arg2 arg2) {
        return this.invoker.invoke(arg1, arg2);
    }

}
