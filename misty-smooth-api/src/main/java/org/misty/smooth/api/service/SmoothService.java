package org.misty.smooth.api.service;

import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;

public interface SmoothService {

    String getServiceId();

    String getServiceName();

    SmoothServiceResponse serve(SmoothServiceRequest request);

}
