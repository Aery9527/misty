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
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Consumer;

@NeedCross(
        implementation = NeedCross.Scope.CORE,
        user = NeedCross.Scope.ANY
)
public interface SmoothContext {

    String getIdentifier();

    Instant getLaunchInstant();

    SmoothEnvironment getEnvironment();

    Set<SmoothModuleId> listModuleWithSet();

    Map<String, Set<String>> listModuleWithMap();

    Optional<String> getModulePresetVersion(String moduleName);

    Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName);

    Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName, String moduleVersion);

    Optional<Map<String, String>> listServiceWithMap(String moduleName);

    Optional<Map<String, String>> listServiceWithMap(String moduleName, String moduleVersion);

    Future<SmoothServiceResponseResult> invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException;

    void invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException;

    SmoothServiceInvoker buildServiceInvoker(String moduleName, String serviceKey);

}
