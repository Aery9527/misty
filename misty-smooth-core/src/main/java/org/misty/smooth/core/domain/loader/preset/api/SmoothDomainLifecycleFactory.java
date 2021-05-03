package org.misty.smooth.core.domain.loader.preset.api;

import org.misty.smooth.api.cross.SmoothCrosser;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public interface SmoothDomainLifecycleFactory {

    SmoothManagerLifecycle findManagerLifecycle(SmoothLoaderArgument loaderArgument, SmoothCrosser domainCrosser);

    SmoothModuleLifecycle findModuleLifecycle(SmoothLoaderArgument loaderArgument, SmoothCrosser domainCrosser);

}
