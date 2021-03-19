package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

public final class SmoothServiceResult {

    /**
     * EX: SmoothResult(ModuleName)(ModuleVersion)(ServiceId)(ServiceName)
     */
    public static final String TO_STRING_FORMAT = SmoothServiceResult.class.getSimpleName() + "[%s:%s]";

    private final SmoothModuleId serviceProvider;

    private final SmoothServiceId serviceId;

    private final SmoothServiceResponse response;

    private final String toString;

    public SmoothServiceResult(SmoothModuleId moduleId, SmoothServiceId serviceId, SmoothServiceResponse response) {
        this.serviceProvider = moduleId;
        this.serviceId = serviceId;
        this.response = response;
        this.toString = String.format(TO_STRING_FORMAT, this.serviceProvider.toString(), this.serviceId.toString());
    }

    @Override
    public String toString() {
        return this.toString;
    }

    public SmoothModuleId getServiceProvider() {
        return serviceProvider;
    }

    public SmoothServiceId getServiceId() {
        return serviceId;
    }

    public SmoothServiceResponse getResponse() {
        return response;
    }
}
