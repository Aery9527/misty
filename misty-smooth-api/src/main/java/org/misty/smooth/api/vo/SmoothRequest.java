package org.misty.smooth.api.vo;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SmoothRequest extends SmoothTransporter {

    /**
     * FIXME 這邊有可能傳遞module的物件, 要想個方法當其他module一直拿著此物件導致無法卸載時的處理方式
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<InputStream> attachment;

    public SmoothRequest() {
        this.attachment = Optional.empty();
    }

    public SmoothRequest(Map<String, List<String>> header) {
        super(header);
        this.attachment = Optional.empty();
    }

    public SmoothRequest(Map<String, List<String>> header, Map<String, String> body) {
        super(header, body);
        this.attachment = Optional.empty();
    }

    public SmoothRequest(InputStream attachment) {
        this.attachment = Optional.ofNullable(attachment);
    }

    public SmoothRequest(Map<String, List<String>> header, InputStream attachment) {
        super(header);
        this.attachment = Optional.ofNullable(attachment);
    }

    public SmoothRequest(Map<String, List<String>> header, Map<String, String> body, InputStream attachment) {
        super(header, body);
        this.attachment = Optional.ofNullable(attachment);
    }

    public Optional<InputStream> getAttachment() {
        return attachment;
    }

}
