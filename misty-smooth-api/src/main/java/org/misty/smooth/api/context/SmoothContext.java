package org.misty.smooth.api.context;

import org.misty.smooth.api.vo.SmoothRequest;
import org.misty.smooth.api.vo.SmoothResponse;

public interface SmoothContext {

    SmoothResponse invokeService(String moduleName, String serviceId, SmoothRequest request);

}
