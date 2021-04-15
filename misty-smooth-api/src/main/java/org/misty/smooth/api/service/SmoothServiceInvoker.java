package org.misty.smooth.api.service;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothServiceInvoker {

    public static final String STRING_FORMAT = SmoothServiceInvoker.class.getSimpleName() + "(%s)(%s)";

    private final String moduleName;

    private final String serviceKey;

    private final SmoothContext smoothContext;

    private final String toString;

    public SmoothServiceInvoker(String moduleName, String serviceKey, SmoothContext smoothContext) {
        this.moduleName = moduleName;
        this.serviceKey = serviceKey;
        this.smoothContext = smoothContext;
        this.toString = String.format(STRING_FORMAT, moduleName, serviceKey);
    }

    public Future<SmoothServiceResponseResult> invoke(SmoothServiceRequest serviceRequest) {
        return this.smoothContext.invokeService(this.moduleName, this.serviceKey, serviceRequest);
    }

    public void invoke(SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor) {
        this.smoothContext.invokeService(this.moduleName, this.serviceKey, serviceRequest, resultProcessor);
    }

    @Override
    public String toString() {
        return this.toString;
    }

    @Override
    public int hashCode() {
        return this.toString.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothServiceInvoker) {
            return ((SmoothServiceInvoker) obj).toString.equals(this.toString);
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
