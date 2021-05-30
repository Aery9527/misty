package org.misty.smooth.core.context.api;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoadTypeController;
import org.misty.smooth.core.domain.module.api.SmoothModuleDomain;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface SmoothModuleDomainCamp extends SmoothDomainLoadTypeController<SmoothModuleId> {

    Set<SmoothModuleId> listModuleWithSet();

    Map<String, Set<String>> listModuleWithMap();

    Optional<String> getModulePresetVersion(String moduleName);

    Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName);

    Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName, String moduleVersion);

    Optional<Map<String, String>> listServiceWithMap(String moduleName);

    Optional<Map<String, String>> listServiceWithMap(String moduleName, String moduleVersion);

    SmoothModuleDomain getModuleDomain(String moduleName) throws SmoothModuleNotFoundException;

    SmoothModuleDomain getModuleDomain(String moduleName, String moduleVersion) throws SmoothModuleNotFoundException;

    void close();

}
