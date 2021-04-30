package org.misty.smooth.core.domain.loader.preset.api;

import org.misty.smooth.api.cross.SmoothCrossObject;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.core.domain.classloader.SmoothDomainClassLoader;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public interface SmoothDomainLifecycleFactory {

    SmoothManagerLifecycle findManagerLifecycle(SmoothLoaderArgument loaderArgument, SmoothCrossObject domainCrosser);

    SmoothModuleLifecycle findModuleLifecycle(SmoothLoaderArgument loaderArgument, SmoothCrossObject domainCrosser);

}
