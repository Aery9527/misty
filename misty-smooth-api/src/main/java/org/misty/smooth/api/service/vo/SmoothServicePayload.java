package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.api.vo.SmoothUnscalableSet;

import java.util.Optional;

class SmoothServicePayload {

    private SmoothUnscalableMap header;

    private final String body;

    public SmoothServicePayload() {
        this(null);
    }

    public SmoothServicePayload(String body) {
        this.body = body;
    }

    public boolean containsHeader(String key) {
        if (this.header == null) {
            return false;
        } else {
            return this.header.containsKey(key);
        }
    }

    public boolean containsHeader(String key, String value) {
        if (this.header == null) {
            return false;
        } else {
            SmoothUnscalableSet content = this.header.get(key);
            return content != null && content.contains(value);
        }
    }

    public Optional<SmoothUnscalableMap> getHeader() {
        return Optional.ofNullable(this.header);
    }

    public SmoothUnscalableMap getHeaderOrCreate() {
        if (this.header == null) {
            this.header = new SmoothUnscalableMap();
        }

        return this.header;
    }

    public Optional<String> getBody() {
        return Optional.ofNullable(this.body);
    }

}
