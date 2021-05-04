package org.misty.smooth.api.context;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.mark.NeedCross;
import org.misty.smooth.api.service.SmoothServiceInvoker;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Consumer;

@NeedCross(
        implementation = NeedCross.Scope.ANY,
        usedBy = NeedCross.Scope.ANY
)
public interface SmoothContext {

    String getIdentifier();

    Instant getLaunchInstant();

    SmoothEnvironment getEnvironment();

    Set<SmoothModuleId> listModuleWithSet();

    default Map<String, String> listModuleWithMap() {
        Set<SmoothModuleId> set = listModuleWithSet();
        return set.stream().reduce(new TreeMap<>(), (map, moduleId) -> {
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

    Future<SmoothServiceResponseResult> invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException;

    void invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException;

    default SmoothServiceInvoker buildServiceInvoker(String moduleName, String serviceKey) {
        return new SmoothServiceInvoker(moduleName, serviceKey, this);
    }

}
