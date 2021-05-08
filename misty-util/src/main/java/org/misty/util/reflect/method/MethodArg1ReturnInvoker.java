package org.misty.util.reflect.method;

public class MethodArg1ReturnInvoker<ReturnType, Arg1> {

    private final MethodReturnAbstract<ReturnType> invoker;

    public MethodArg1ReturnInvoker(MethodReturnAbstract<ReturnType> invoker) {
        this.invoker = invoker;
    }

    public ReturnType invoke(Arg1 arg1) {
        return this.invoker.invoke(arg1);
    }

}
