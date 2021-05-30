package org.misty.smooth.api.service;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;

import java.util.concurrent.Future;

public interface SmoothServiceFutureInvoker {

    Future<SmoothServiceResponseResult> invoke(SmoothModuleId id, String serviceKey, SmoothServiceRequest serviceRequest)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException;

}
