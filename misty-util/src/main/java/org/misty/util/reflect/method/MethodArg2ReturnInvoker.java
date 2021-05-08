package org.misty.util.reflect.method;

public class MethodArg2ReturnInvoker<ReturnType, Arg1, Arg2> {

    private final MethodReturnAbstract<ReturnType> invoker;

    public MethodArg2ReturnInvoker(MethodReturnAbstract<ReturnType> invoker) {
        this.invoker = invoker;
    }

    public ReturnType invoke(Arg1 arg1, Arg2 arg2) {
        return this.invoker.invoke(arg1, arg2);
    }

}
