package org.misty.smooth.api.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SmoothTransporter {

    private Map<String, List<String>> header;

    private Map<String, String> body;

    public SmoothTransporter() {
        this(new HashMap<>());
    }

    public SmoothTransporter(Map<String, List<String>> header) {
        this(header, new HashMap<>());
    }

    public SmoothTransporter(Map<String, List<String>> header, Map<String, String> body) {
        this.header = header;
        this.body = body;
    }

    public Map<String, List<String>> getHeader() {
        return header;
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
            List<String> values = this.header.get(key);
            return values != null && values.contains(value);
        }
    }

    public boolean containsBody(String key) {
        if (this.body == null) {
            return false;
        } else {
            return this.body.containsKey(key);
        }
    }

    public void setHeader(Map<String, List<String>> header) {
        this.header = header;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }
}
