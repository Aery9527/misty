package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.vo.SmoothModuleId;

public final class SmoothServiceOrigin {

    public static final String STRING_FORMAT = SmoothServiceOrigin.class.getSimpleName() + "[from:%s]";

    private final SmoothModuleId invokedModule;

    private final SmoothServiceRequest request;

    private final String toString;

    public SmoothServiceOrigin(SmoothModuleId invokedModule, SmoothServiceRequest request) {
        this.invokedModule = invokedModule;
        this.request = request;
        this.toString = String.format(STRING_FORMAT, this.invokedModule.toString());
    }

    @Override
    public String toString() {
        return this.toString;
    }

    public SmoothModuleId getInvokedModule() {
        return invokedModule;
    }

    public SmoothServiceRequest getRequest() {
        return request;
    }
}
