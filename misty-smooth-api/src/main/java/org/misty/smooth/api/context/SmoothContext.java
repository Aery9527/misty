package org.misty.smooth.api.context;

import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;

import java.util.Map;
import java.util.Set;

public interface SmoothContext {

    Set<SmoothModuleId> listAllModuleWithSet();

    Map<String, String> listAllModuleWithMap();

    SmoothServiceResult invokeService(String moduleName, String serviceId, SmoothServiceRequest serviceRequest);

}
