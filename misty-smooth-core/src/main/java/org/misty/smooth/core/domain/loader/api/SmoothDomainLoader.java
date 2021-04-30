package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.vo.SmoothId;

public interface SmoothDomainLoader<SmoothIdType extends SmoothId<SmoothIdType>> {

    void setLoadTypeController(SmoothDomainLoadTypeController<SmoothIdType> loadTypeController);

    void launch();

}
