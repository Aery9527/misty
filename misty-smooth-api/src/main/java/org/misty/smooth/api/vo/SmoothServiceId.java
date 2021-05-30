package org.misty.smooth.api.vo;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public final class SmoothServiceId implements SmoothId<SmoothServiceId> {

    public static class Format {
        public static final String DESCRIPTION = "SmoothService(%s)(%s)";
    }

    private final String serviceKey;

    private final String serviceName;

    private final AtomicReference<Supplier<String>> description = new AtomicReference<>();

    public SmoothServiceId(String serviceKey, String serviceName) {
        this.serviceKey = serviceKey;
        this.serviceName = serviceName;
        this.description.set(() -> {
            String description = String.format(Format.DESCRIPTION, this.serviceKey, this.serviceName);
            this.description.set(() -> description);
            return description;
        });
    }

    @Override
    public String toString() {
        return this.description.get().get();
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

    @Override
    public String getDescription() {
        return description.get().get();
    }

}
