package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.tool.LazyInitializer;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

public final class SmoothServiceResponseResult {

    public static class Format {
        public static final String DESCRIPTION = "ServiceResult:%s:%s";
    }

    private final SmoothModuleId responder;

    private final SmoothServiceId serviceId;

    private final SmoothServiceResponse response;

    private final LazyInitializer<String> description;

    @SuppressWarnings("CodeBlock2Expr")
    public SmoothServiceResponseResult(SmoothModuleId responder, SmoothServiceId serviceId, SmoothServiceResponse response) {
        this.responder = responder;
        this.serviceId = serviceId;
        this.response = response;
        this.description = new LazyInitializer<>(() -> {
            return String.format(Format.DESCRIPTION, this.responder.getDescription(), this.serviceId.toString());
        }, false);
    }

    @Override
    public String toString() {
        return this.description.get();
    }

    public SmoothModuleId getResponder() {
        return responder;
    }

    public SmoothServiceId getServiceId() {
        return serviceId;
    }

    public SmoothServiceResponse getResponse() {
        return response;
    }
}
