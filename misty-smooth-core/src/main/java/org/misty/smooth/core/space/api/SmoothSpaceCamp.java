package org.misty.smooth.core.space.api;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.space.module.api.SmoothModuleSpace;

import java.util.Optional;
import java.util.Set;

public interface SmoothSpaceCamp {

    Set<SmoothModuleId> listModuleWithSet();

    Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName);

    SmoothModuleSpace getModuleSpace(String moduleName) throws SmoothModuleNotFoundException;

    void close();

}
