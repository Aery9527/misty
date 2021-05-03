package org.misty.smooth.core.domain.loader.preset.impl;

import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.cross.SmoothCrosser;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.core.domain.loader.preset.api.SmoothDomainLifecycleFactory;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public class SmoothDomainLifecycleFactoryWrapper
        extends SmoothCrossWrapper<SmoothDomainLifecycleFactory>
        implements SmoothDomainLifecycleFactory {

    public SmoothDomainLifecycleFactoryWrapper(SmoothDomainLifecycleFactory smoothDomainLifecycleFactory) {
        super(SmoothDomainLifecycleFactoryWrapper.class.getClassLoader(), smoothDomainLifecycleFactory);
    }

    @Override
    public SmoothManagerLifecycle findManagerLifecycle(SmoothLoaderArgument loaderArgument, SmoothCrosser domainCrosser) {
        return null;
    }

    @Override
    public SmoothModuleLifecycle findModuleLifecycle(SmoothLoaderArgument loaderArgument, SmoothCrosser domainCrosser) {
        return null;
    }

}
