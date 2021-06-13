package org.misty.smooth.api.service.vo;

import java.io.OutputStream;
import java.util.Optional;

public final class SmoothServiceResponse extends SmoothServicePayload {

    /**
     * FIXME 這邊有可能傳遞module的物件, 要想個方法當其他module一直拿著此物件導致無法卸載時的處理方式
     */
    private final OutputStream attachment;

    public SmoothServiceResponse() {
        this.attachment = null;
    }

    public SmoothServiceResponse(String body) {
        super(body);
        this.attachment = null;
    }

    public SmoothServiceResponse(OutputStream attachment) {
        this.attachment = new SmoothOutputStreamCrosser(attachment);
    }

    public SmoothServiceResponse(String body, OutputStream attachment) {
        super(body);
        this.attachment = new SmoothOutputStreamCrosser(attachment);
    }

    public Optional<OutputStream> getAttachment() {
        return Optional.ofNullable(attachment);
    }

}
