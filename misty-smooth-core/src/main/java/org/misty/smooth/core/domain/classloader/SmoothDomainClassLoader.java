package org.misty.smooth.core.domain.classloader;

import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.core.error.SmoothCoreError;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.atomic.AtomicReference;

public class SmoothDomainClassLoader extends URLClassLoader {

    private final AtomicReference<SmoothId<?>> smoothId = new AtomicReference<>();

    public SmoothDomainClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public String toString() {
        return SmoothDomainClassLoader.class.getSimpleName() + "-" + this.smoothId.get();
    }

    public SmoothId<?> getSmoothId() {
        return this.smoothId.get();
    }

    public void setSmoothId(SmoothId<?> smoothId) {
        boolean success = this.smoothId.compareAndSet(null, smoothId);
        if (!success) {
            SmoothCoreError.CLASSLOADER_BUILD_ERROR.thrown("can't set smoothId again.");
        }
    }


}
