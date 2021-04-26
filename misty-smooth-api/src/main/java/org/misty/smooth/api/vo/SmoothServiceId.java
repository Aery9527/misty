package org.misty.smooth.api.vo;

public final class SmoothServiceId implements SmoothId<SmoothServiceId> {

    public static class Format {
        public static final String DESCRIPTION = "SmoothService(%s)(%s)";
    }

    private final String serviceKey;

    private final String serviceName;

    private final String description;

    public SmoothServiceId(String serviceKey, String serviceName) {
        this.serviceKey = serviceKey;
        this.serviceName = serviceName;
        this.description = String.format(Format.DESCRIPTION, this.serviceKey, this.serviceName);
    }

    @Override
    public String getTypeKey() {
        return this.serviceKey;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int hashCode() {
        return this.serviceKey.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothServiceId) {
            return ((SmoothServiceId) obj).serviceKey.equals(this.serviceKey);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(SmoothServiceId smoothServiceId) {
        String self = toString();
        String comer = smoothServiceId.toString();
        return self.compareTo(comer);
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public String getServiceName() {
        return serviceName;
    }
}
