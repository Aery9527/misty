package org.misty.smooth.api.service;

import org.misty.smooth.api.mark.Guide;
import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;

@Guide(
        implementationBy = Guide.Scope.MODULE,
        usedBy = Guide.Scope.ANY
)
public interface SmoothService {

    String getServiceKey();

    String getServiceName();

    SmoothServiceResponse serve(SmoothServiceRequestOrigin requestOrigin);

}
