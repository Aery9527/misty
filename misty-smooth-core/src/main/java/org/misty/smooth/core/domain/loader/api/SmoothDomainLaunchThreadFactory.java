package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.api.vo.SmoothUnscalableMap;

public interface SmoothDomainLaunchThreadFactory<SmoothIdType extends SmoothId<SmoothIdType>> {

    Thread build(SmoothUnscalableMap loaderArgument, SmoothIdType smoothId, Runnable runnable);

}
