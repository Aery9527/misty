package org.misty.util.reflect.method;

public class MethodArg0ReturnInvoker<ReturnType> {

    private final MethodReturnAbstract<ReturnType> invoker;

    public MethodArg0ReturnInvoker(MethodReturnAbstract<ReturnType> invoker) {
        this.invoker = invoker;
    }

    public ReturnType invoke() {
        return this.invoker.invoke();
    }

}
