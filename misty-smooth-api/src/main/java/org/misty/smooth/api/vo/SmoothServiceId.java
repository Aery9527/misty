package org.misty.smooth.api.vo;

public final class SmoothServiceId implements Comparable<SmoothServiceId> {

    public static final String STRING_FORMAT = "SmoothService(%s)(%s)";

    private final String serviceKey;

    private final String serviceName;

    private final String toString;

    public SmoothServiceId(String serviceKey, String serviceName) {
        this.serviceKey = serviceKey;
        this.serviceName = serviceName;
        this.toString = String.format(STRING_FORMAT, this.serviceKey, this.serviceName);
    }

    @Override
    public String toString() {
        return this.toString;
    }

    @Override
    public int hashCode() {
        return this.serviceKey.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothServiceId) {
            return ((SmoothServiceId) obj).toString.equals(this.toString);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(SmoothServiceId smoothServiceId) {
        return this.toString.compareTo(smoothServiceId.toString);
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public String getServiceName() {
        return serviceName;
    }
}
