package org.misty.smooth.api.service;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;

import java.util.function.Consumer;

public class SmoothServiceInvoker {

    public static final String FORMAT = "SmoothServiceInvoker(%s)(%s)";

    private final String moduleName;

    private final String serviceId;

    private final SmoothContext smoothContext;

    private final String toString;

    public SmoothServiceInvoker(String moduleName, String serviceId, SmoothContext smoothContext) {
        this.moduleName = moduleName;
        this.serviceId = serviceId;
        this.smoothContext = smoothContext;
        this.toString = String.format(FORMAT, moduleName, serviceId);
    }

    public void invoke(SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResult> resultProcessor) {
        this.smoothContext.invokeService(this.moduleName, this.serviceId, serviceRequest, resultProcessor);
    }

    @Override
    public String toString() {
        return this.toString.toString();
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

    public String getServiceId() {
        return serviceId;
    }
}
