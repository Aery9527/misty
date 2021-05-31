package org.misty.smooth.api.context;

import org.misty.smooth.api.mark.SmoothGuide;
import org.misty.smooth.api.service.SmoothServiceCallbackInvoker;
import org.misty.smooth.api.service.SmoothServiceFutureInvoker;
import org.misty.smooth.api.service.SmoothServiceInvoker;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@SmoothGuide(needCross = true,
        implementationBy = SmoothGuide.Domain.CORE,
        usedBy = SmoothGuide.Domain.ANY
)
public interface SmoothContext extends SmoothServiceFutureInvoker, SmoothServiceCallbackInvoker {

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

    SmoothServiceInvoker buildServiceInvoker(String moduleName, String serviceKey);

    SmoothServiceInvoker buildServiceInvoker(String moduleName, String moduleVersion, String serviceKey);

}
