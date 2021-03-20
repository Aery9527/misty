package org.misty.smooth.api.vo;

public class SmoothServiceId {

    public static final String FORMAT = "SmoothService(%s)(%s)";

    private final String serviceId;

    private final String serviceName;

    private final String toString;

    public SmoothServiceId(String serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.toString = String.format(FORMAT, this.serviceId, this.serviceName);
    }

    @Override
    public String toString() {
        return this.toString;
    }

    @Override
    public int hashCode() {
        return this.serviceId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothServiceId) {
            return ((SmoothServiceId) obj).toString.equals(this.toString);
        } else {
            return false;
        }
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }
}
