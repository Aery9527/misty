package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.api.vo.SmoothUnscalableMap;

public interface SmoothDomainLoadTypeController<SmoothIdType extends SmoothId<SmoothIdType>> {

    SmoothLoadType prepareLoading(SmoothUnscalableMap loaderArgument, SmoothIdType smoothId);

    boolean releaseWaitForOnline(SmoothIdType smoothId);

}
