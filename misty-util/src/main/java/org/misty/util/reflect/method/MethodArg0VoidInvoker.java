package org.misty.util.reflect.method;

public class MethodArg0VoidInvoker {

    private final MethodVoidInvoker invoker;

    public MethodArg0VoidInvoker(MethodVoidInvoker invoker) {
        this.invoker = invoker;
    }

    public void invoke() {
        this.invoker.invoke();
    }

}
