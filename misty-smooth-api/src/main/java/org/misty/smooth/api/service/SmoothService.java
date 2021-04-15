package org.misty.smooth.api.service;

import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;

public interface SmoothService {

    String getServiceKey();

    String getServiceName();

    SmoothServiceResponse serve(SmoothServiceRequestOrigin requestOrigin);

}
