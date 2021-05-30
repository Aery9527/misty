package org.misty.smooth.api.service;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothServiceInvoker {

    public static class Format {
        public static final String DESCRIPTION = SmoothServiceInvoker.class.getSimpleName() + "(%s)(%s)(%s)";
    }

    private final SmoothModuleId id;

    private final String serviceKey;

    private final SmoothServiceFutureInvoker futureInvoker;

    private final SmoothServiceCallbackInvoker callbackInvoker;

    private final String description;

    public SmoothServiceInvoker(String moduleName, String moduleVersion, String serviceKey,
                                SmoothServiceFutureInvoker futureInvoker,
                                SmoothServiceCallbackInvoker callbackInvoker
    ) {
        this.id = new SmoothModuleId(moduleName, moduleVersion);
        this.serviceKey = serviceKey;
        this.futureInvoker = futureInvoker;
        this.callbackInvoker = callbackInvoker;
        this.description = String.format(Format.DESCRIPTION, moduleName, moduleVersion, serviceKey);
    }

    public Future<SmoothServiceResponseResult> invoke(SmoothServiceRequest serviceRequest)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        return this.futureInvoker.invoke(this.id, this.serviceKey, serviceRequest);
    }

    public void invoke(SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        this.callbackInvoker.invoke(this.id, this.serviceKey, serviceRequest, resultProcessor);
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
        return this.id.getModuleName();
    }

    public String getModuleVersion() {
        return this.id.getModuleVersion();
    }

    public String getServiceKey() {
        return serviceKey;
    }
}
