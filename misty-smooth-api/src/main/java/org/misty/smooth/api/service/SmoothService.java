package org.misty.smooth.api.service;

import org.misty.smooth.api.mark.SmoothGuide;
import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;

@SmoothGuide(
        implementationBy = SmoothGuide.Domain.MODULE,
        usedBy = SmoothGuide.Domain.ANY
)
public interface SmoothService {

    String getServiceKey();

    String getServiceName();

    SmoothServiceResponse serve(SmoothServiceRequestOrigin requestOrigin);

}
