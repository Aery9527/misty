package org.misty.smooth.api.service;

import org.misty.smooth.api.mark.NeedCross;
import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;

@NeedCross(
        implementation = NeedCross.Scope.MODULE,
        user = NeedCross.Scope.ANY
)
public interface SmoothService {

    String getServiceKey();

    String getServiceName();

    SmoothServiceResponse serve(SmoothServiceRequestOrigin requestOrigin);

}
