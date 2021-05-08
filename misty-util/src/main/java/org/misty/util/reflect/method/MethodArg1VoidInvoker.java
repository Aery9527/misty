package org.misty.util.reflect.method;

public class MethodArg1VoidInvoker<Arg1> {

    private final MethodVoidInvoker invoker;

    public MethodArg1VoidInvoker(MethodVoidInvoker invoker) {
        this.invoker = invoker;
    }

    public void invoke(Arg1 arg1) {
        this.invoker.invoke(arg1);
    }

}
