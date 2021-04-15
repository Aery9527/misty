package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

public final class SmoothServiceResponseResult {

    public static final String STRING_FORMAT = SmoothServiceResponseResult.class.getSimpleName() + "[by:%s:%s]";

    private final SmoothModuleId serviceModule;

    private final SmoothServiceId serviceId;

    private final SmoothServiceResponse response;

    private final String toString;

    public SmoothServiceResponseResult(SmoothModuleId moduleId, SmoothServiceId serviceId, SmoothServiceResponse response) {
        this.serviceModule = moduleId;
        this.serviceId = serviceId;
        this.response = response;
        this.toString = String.format(STRING_FORMAT, this.serviceModule.toString(), this.serviceId.toString());
    }

    @Override
    public String toString() {
        return this.toString;
    }

    public SmoothModuleId getServiceModule() {
        return serviceModule;
    }

    public SmoothServiceId getServiceId() {
        return serviceId;
    }

    public SmoothServiceResponse getResponse() {
        return response;
    }
}
