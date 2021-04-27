package org.misty.smooth.api.service;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothServiceInvoker {

    public static class Format {
        public static final String DESCRIPTION = SmoothServiceInvoker.class.getSimpleName() + "(%s)(%s)";
    }

    private final String moduleName;

    private final String serviceKey;

    private final SmoothContext smoothContext;

    private final String description;

    public SmoothServiceInvoker(String moduleName, String serviceKey, SmoothContext smoothContext) {
        this.moduleName = moduleName;
        this.serviceKey = serviceKey;
        this.smoothContext = smoothContext;
        this.description = String.format(Format.DESCRIPTION, moduleName, serviceKey);
    }

    public Future<SmoothServiceResponseResult> invoke(SmoothServiceRequest serviceRequest) {
        return this.smoothContext.invokeService(this.moduleName, this.serviceKey, serviceRequest);
    }

    public void invoke(SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor) {
        this.smoothContext.invokeService(this.moduleName, this.serviceKey, serviceRequest, resultProcessor);
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothServiceInvoker) {
            return ((SmoothServiceInvoker) obj).description.equals(this.description);
        } else {
            return false;
        }
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getServiceKey() {
        return serviceKey;
    }
}
