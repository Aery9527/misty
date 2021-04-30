package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.vo.SmoothId;

public final class SmoothServiceRequestOrigin {

    public static final String STRING_FORMAT = SmoothServiceRequestOrigin.class.getSimpleName() + "[from:%s]";

    private final SmoothId<?> invoker;

    private final SmoothServiceRequest request;

    private final String toString;

    public SmoothServiceRequestOrigin(SmoothId<?> invoker, SmoothServiceRequest request) {
        this.invoker = invoker;
        this.request = request;
        this.toString = String.format(STRING_FORMAT, this.invoker.getDescription());
    }

    @Override
    public String toString() {
        return this.toString;
    }

    public SmoothId<?> getInvoker() {
        return invoker;
    }

    public SmoothServiceRequest getRequest() {
        return request;
    }


}
