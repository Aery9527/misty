package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.mark.SmoothGuide;

import java.io.InputStream;
import java.util.Optional;

public final class SmoothServiceRequest extends SmoothServicePayload {

    /**
     * FIXME 這邊有可能傳遞遊module的ClassLoader載入的物件, 要想個方法當其他module一直拿著此物件導致無法卸載時的處理方式
     */
    @SmoothGuide(needCross = true,
            implementationBy = SmoothGuide.Domain.ANY,
            usedBy = SmoothGuide.Domain.MODULE
    )
    private final InputStream attachment;

    public SmoothServiceRequest() {
        this.attachment = null;
    }

    public SmoothServiceRequest(String body) {
        super(body);
        this.attachment = null;
    }

    public SmoothServiceRequest(InputStream attachment) {
        this.attachment = new SmoothInputStreamCrosser(attachment);
    }

    public SmoothServiceRequest(String body, InputStream attachment) {
        super(body);
        this.attachment = new SmoothInputStreamCrosser(attachment);
    }

    public Optional<InputStream> getAttachment() {
        return Optional.ofNullable(attachment);
    }

}
