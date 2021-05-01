package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public class SmoothDomainLaunchThreadFactoryCrossWrapper<SmoothIdType extends SmoothId<SmoothIdType>>
        extends SmoothCrossWrapper<SmoothDomainLaunchThreadFactory<SmoothIdType>>
        implements SmoothDomainLaunchThreadFactory<SmoothIdType> {

    public SmoothDomainLaunchThreadFactoryCrossWrapper(ClassLoader wrapClassLoader, SmoothDomainLaunchThreadFactory<SmoothIdType> launchThreadFactory) {
        super(wrapClassLoader, launchThreadFactory);
    }

    @Override
    public Thread build(SmoothLoaderArgument loaderArgument, SmoothIdType smoothId, Runnable runnable) {
        return super.wrap(() -> {
            SmoothDomainLaunchThreadFactory<SmoothIdType> raw = super.getWrappedTarget();
            return raw.build(loaderArgument, smoothId, runnable);
        });
    }

}
