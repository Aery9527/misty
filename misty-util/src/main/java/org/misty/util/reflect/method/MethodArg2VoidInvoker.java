package org.misty.util.reflect.method;

public class MethodArg2VoidInvoker<Arg1, Arg2> {

    private final MethodVoidInvoker invoker;

    public MethodArg2VoidInvoker(MethodVoidInvoker invoker) {
        this.invoker = invoker;
    }

    public void invoke(Arg1 arg1, Arg2 arg2) {
        this.invoker.invoke(arg1, arg2);
    }

}
