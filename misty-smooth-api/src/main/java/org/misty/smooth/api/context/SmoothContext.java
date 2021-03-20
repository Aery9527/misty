package org.misty.smooth.api.context;

import org.misty.smooth.api.service.SmoothServiceInvoker;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface SmoothContext {

    Optional<Set<SmoothModuleId>> listModuleWithSet();

    Optional<Map<String, String>> listModuleWithMap();

    Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName);

    Optional<Map<String, String>> listServiceWithMap(String moduleName);

    Future<SmoothServiceResult> invokeService(String moduleName, String serviceId, SmoothServiceRequest serviceRequest);

    void invokeService(String moduleName, String serviceId, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResult> resultProcessor);

    default SmoothServiceInvoker buildServiceInvoker(String moduleName, String serviceId) {
        return new SmoothServiceInvoker(moduleName, serviceId, this);
    }

}
