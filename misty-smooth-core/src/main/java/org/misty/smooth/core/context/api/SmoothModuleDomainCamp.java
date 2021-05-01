package org.misty.smooth.core.context.api;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoadTypeController;
import org.misty.smooth.core.domain.module.api.SmoothModuleDomain;

import java.util.Optional;
import java.util.Set;

public interface SmoothModuleDomainCamp extends SmoothDomainLoadTypeController<SmoothModuleId> {

    Set<SmoothModuleId> listModuleWithSet();

    Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName);

    SmoothModuleDomain getModuleDomain(String moduleName) throws SmoothModuleNotFoundException;

    void close();

}
