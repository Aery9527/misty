package org.misty.smooth.core.domain.loader.preset.api;

import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public interface SmoothDomainLifecycleThreadFactory {

    Thread buildLaunchThread(SmoothLoaderArgument loaderArgument, SmoothManagerId managerId);

    Thread buildDestroyThread(SmoothLoaderArgument loaderArgument, SmoothManagerId managerId);

    Thread buildLaunchThread(SmoothLoaderArgument loaderArgument, SmoothModuleId moduleId);

    Thread buildDestroyThread(SmoothLoaderArgument loaderArgument, SmoothModuleId moduleId);

}
