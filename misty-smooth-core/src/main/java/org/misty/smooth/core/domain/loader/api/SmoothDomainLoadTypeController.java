package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public interface SmoothDomainLoadTypeController<SmoothIdType extends SmoothId<SmoothIdType>> {

    SmoothLoadType prepareLoading(SmoothLoaderArgument loaderArgument, SmoothIdType smoothId);

    boolean releaseWaitForOnline(SmoothIdType smoothId);

}
