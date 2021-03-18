package org.misty.smooth.api.vo;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SmoothResponse extends SmoothTransporter {

    /**
     * FIXME 這邊有可能傳遞module的物件, 要想個方法當其他module一直拿著此物件導致無法卸載時的處理方式
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<OutputStream> attachment;

    public SmoothResponse() {
        this.attachment = Optional.empty();
    }

    public SmoothResponse(Map<String, List<String>> header) {
        super(header);
        this.attachment = Optional.empty();
    }

    public SmoothResponse(Map<String, List<String>> header, Map<String, String> body) {
        super(header, body);
        this.attachment = Optional.empty();
    }

    public SmoothResponse(OutputStream attachment) {
        this.attachment = Optional.ofNullable(attachment);
    }

    public SmoothResponse(Map<String, List<String>> header, OutputStream attachment) {
        super(header);
        this.attachment = Optional.ofNullable(attachment);
    }

    public SmoothResponse(Map<String, List<String>> header, Map<String, String> body, OutputStream attachment) {
        super(header, body);
        this.attachment = Optional.ofNullable(attachment);
    }

    public Optional<OutputStream> getAttachment() {
        return attachment;
    }

}
