package org.misty.smooth.core.domain.loader.preset.api;

import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.manager.SmoothManagerId;

public interface SmoothDomainLifecycleThreadFactory {

    Thread buildLaunchThread(SmoothUnscalableMap loaderArgument, SmoothManagerId managerId, Runnable runnable);

    Thread buildDestroyThread(SmoothUnscalableMap loaderArgument, SmoothManagerId managerId, Runnable runnable);

    Thread buildLaunchThread(SmoothUnscalableMap loaderArgument, SmoothModuleId moduleId, Runnable runnable);

    Thread buildDestroyThread(SmoothUnscalableMap loaderArgument, SmoothModuleId moduleId, Runnable runnable);

}
