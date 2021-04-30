package org.misty.smooth.core.domain.loader.preset.api;

import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public interface SmoothDomainLifecycleThreadFactory {

    Thread buildLaunchThread(SmoothLoaderArgument loaderArgument, SmoothManagerId managerId, Runnable runnable);

    Thread buildDestroyThread(SmoothLoaderArgument loaderArgument, SmoothManagerId managerId, Runnable runnable);

    Thread buildLaunchThread(SmoothLoaderArgument loaderArgument, SmoothModuleId moduleId, Runnable runnable);

    Thread buildDestroyThread(SmoothLoaderArgument loaderArgument, SmoothModuleId moduleId, Runnable runnable);

}
