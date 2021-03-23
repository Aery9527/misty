package org.misty.smooth.api.service.vo;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SmoothServiceResponse extends SmoothServiceTransporter {

    /**
     * FIXME 這邊有可能傳遞module的物件, 要想個方法當其他module一直拿著此物件導致無法卸載時的處理方式
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<OutputStream> attachment;

    public SmoothServiceResponse() {
        this.attachment = Optional.empty();
    }

    public SmoothServiceResponse(Map<String, List<String>> header) {
        super(header);
        this.attachment = Optional.empty();
    }

    public SmoothServiceResponse(Map<String, List<String>> header, Map<String, String> body) {
        super(header, body);
        this.attachment = Optional.empty();
    }

    public SmoothServiceResponse(OutputStream attachment) {
        this.attachment = Optional.of(new SmoothOutputStreamCrossWrapper(attachment));
    }

    public SmoothServiceResponse(Map<String, List<String>> header, OutputStream attachment) {
        super(header);
        this.attachment = Optional.of(new SmoothOutputStreamCrossWrapper(attachment));
    }

    public SmoothServiceResponse(Map<String, List<String>> header, Map<String, String> body, OutputStream attachment) {
        super(header, body);
        this.attachment = Optional.of(new SmoothOutputStreamCrossWrapper(attachment));
    }

    public Optional<OutputStream> getAttachment() {
        return attachment;
    }

}
