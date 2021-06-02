package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.tool.LazyInitializer;
import org.misty.smooth.api.vo.SmoothId;

public final class SmoothServiceRequestOrigin {

    public static class Format {
        public static final String DESCRIPTION = "ServiceOrigin:%s";
    }

    private final SmoothId<?> caller;

    private final SmoothServiceRequest request;

    private final LazyInitializer<String> description;

    @SuppressWarnings("CodeBlock2Expr")
    public SmoothServiceRequestOrigin(SmoothId<?> caller, SmoothServiceRequest request) {
        this.caller = caller;
        this.request = request;
        this.description = new LazyInitializer<>(() -> {
            return String.format(Format.DESCRIPTION, this.caller.getDescription());
        }, false);
    }

    @Override
    public String toString() {
        return this.description.get();
    }

    public SmoothId<?> getCaller() {
        return caller;
    }

    public SmoothServiceRequest getRequest() {
        return request;
    }

}
