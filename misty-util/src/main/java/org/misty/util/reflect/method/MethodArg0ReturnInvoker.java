package org.misty.util.reflect.method;

public class MethodArg0ReturnInvoker<ReturnType> {

    private final MethodObjectInvoker<ReturnType> invoker;

    public MethodArg0ReturnInvoker(MethodObjectInvoker<ReturnType> invoker) {
        this.invoker = invoker;
    }

    public ReturnType invoke() {
        return this.invoker.invoke();
    }

}
