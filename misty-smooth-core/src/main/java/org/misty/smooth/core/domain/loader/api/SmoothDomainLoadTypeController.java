package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.util.Optional;

public interface SmoothDomainLoadTypeController<SmoothIdType extends SmoothId<SmoothIdType>> {

    Optional<SmoothLoadType> checkType(SmoothLoaderArgument loaderArgument, SmoothIdType smoothId);

}
