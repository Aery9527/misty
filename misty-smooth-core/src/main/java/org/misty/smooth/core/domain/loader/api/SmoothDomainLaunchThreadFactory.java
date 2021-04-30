package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public interface SmoothDomainLaunchThreadFactory<SmoothIdType extends SmoothId<SmoothIdType>> {

    Thread build(SmoothLoaderArgument loaderArgument, SmoothIdType smoothId, Runnable runnable);

}
