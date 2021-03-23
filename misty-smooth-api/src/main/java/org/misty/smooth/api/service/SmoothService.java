package org.misty.smooth.api.service;

import org.misty.smooth.api.service.vo.SmoothServiceOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;

public interface SmoothService {

    String getServiceKey();

    String getServiceName();

    SmoothServiceResponse serve(SmoothServiceOrigin serviceOrigin);

}
