package org.misty.smooth.core.domain.loader.preset.api;

import org.misty.smooth.api.cross.SmoothCrosser;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;

public interface SmoothDomainLifecycleFactory {

    SmoothManagerLifecycle findManagerLifecycle(SmoothUnscalableMap loaderArgument, SmoothCrosser domainCrosser);

    SmoothModuleLifecycle findModuleLifecycle(SmoothUnscalableMap loaderArgument, SmoothCrosser domainCrosser);

}
