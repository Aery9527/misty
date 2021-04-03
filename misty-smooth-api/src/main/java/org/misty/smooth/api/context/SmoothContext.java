package org.misty.smooth.api.context;

import org.misty.smooth.api.service.SmoothServiceInvoker;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface SmoothContext {

    Instant getLaunchInstant();

    SmoothEnvironment getEnvironment();

    Set<SmoothModuleId> listModuleWithSet();

    default Map<String, String> listModuleWithMap() {
        Set<SmoothModuleId> set = listModuleWithSet();
        return set.stream().reduce(new HashMap<>(), (map, moduleId) -> {
            map.put(moduleId.getModuleName(), moduleId.getModuleVersion());
            return map;
        }, (m1, m2) -> {
            m1.putAll(m2);
            return m1;
        });
    }

    Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName);

    default Optional<Map<String, String>> listServiceWithMap(String moduleName) {
        Optional<Set<SmoothServiceId>> op = listServiceWithSet(moduleName);
        if (!op.isPresent()) {
            return Optional.empty();
        }

        Set<SmoothServiceId> set = op.get();
        return Optional.of(set.stream().reduce(new HashMap<>(), (map, serviceId) -> {
            map.put(serviceId.getServiceKey(), serviceId.getServiceName());
            return map;
        }, (m1, m2) -> {
            m1.putAll(m2);
            return m1;
        }));
    }

    Future<SmoothServiceResult> invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest);

    void invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResult> resultProcessor);

    default SmoothServiceInvoker buildServiceInvoker(String moduleName, String serviceKey) {
        return new SmoothServiceInvoker(moduleName, serviceKey, this);
    }

}
