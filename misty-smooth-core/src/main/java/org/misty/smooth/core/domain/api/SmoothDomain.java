package org.misty.smooth.core.domain.api;

import org.misty.smooth.api.vo.SmoothId;

public interface SmoothDomain<SmoothIdType extends SmoothId<SmoothIdType>> {

    SmoothIdType getSmoothId();

    void close();

}
