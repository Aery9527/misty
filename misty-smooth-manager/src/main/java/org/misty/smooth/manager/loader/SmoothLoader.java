package org.misty.smooth.manager.loader;

import org.misty.smooth.api.vo.SmoothId;

public interface SmoothLoader<SmoothIdType extends SmoothId<SmoothIdType>> {

    SmoothIdType getSmoothId();

}
