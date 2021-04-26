package org.misty.smooth.core.space.impl;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.space.api.SmoothSpaceCamp;
import org.misty.smooth.core.space.module.api.SmoothModuleSpace;
import org.misty.smooth.core.tool.SmoothIdLinkageMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;

public class SmoothSpaceCampPreset implements SmoothSpaceCamp {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SmoothIdLinkageMap<SmoothModuleId, SmoothModuleSpace> moduleLinkageMap = new SmoothIdLinkageMap<>();

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return this.moduleLinkageMap.listKey();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        SmoothModuleId moduleId = this.moduleLinkageMap.getKey(moduleName);
        if (moduleId == null) {
            return Optional.empty();
        }

        SmoothModuleSpace moduleSpace = this.moduleLinkageMap.getValue(moduleId);
        Set<SmoothServiceId> serviceIds = moduleSpace.listServices();
        return Optional.of(serviceIds);
    }

    @Override
    public SmoothModuleSpace getModuleSpace(String moduleName) throws SmoothModuleNotFoundException {
        SmoothModuleId moduleId = this.moduleLinkageMap.getKey(moduleName);
        if (moduleId == null) {
            throw new SmoothModuleNotFoundException(moduleName);
        }

        return this.moduleLinkageMap.getValue(moduleId);
    }

    @Override
    public void close() {
        this.moduleLinkageMap.atomic(() -> {
            this.moduleLinkageMap.foreach((moduleId, moduleSpace) -> {
                try {
                    moduleSpace.close();
                } catch (Throwable t) {
                    this.logger.error(moduleId + " close error.", t);
                }
            });

            this.moduleLinkageMap.close();
        });
    }

}
