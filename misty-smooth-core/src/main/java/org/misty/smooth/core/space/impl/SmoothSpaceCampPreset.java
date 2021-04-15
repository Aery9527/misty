package org.misty.smooth.core.space.impl;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.space.api.SmoothSpaceCamp;
import org.misty.smooth.core.space.module.api.SmoothModuleSpace;
import org.misty.smooth.core.tool.SmoothIdGearingMap;
import org.misty.util.ex.ReentrantLockEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SmoothSpaceCampPreset implements SmoothSpaceCamp {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SmoothIdGearingMap<SmoothModuleId, SmoothModuleSpace> moduleGearingMap = new SmoothIdGearingMap<>();

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return this.moduleGearingMap.listKey2();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        SmoothModuleId moduleId = this.moduleGearingMap.getKey2(moduleName);
        if (moduleId == null) {
            return Optional.empty();
        }

        SmoothModuleSpace moduleSpace = this.moduleGearingMap.getValue(moduleId);
        Set<SmoothServiceId> serviceIds = moduleSpace.listServices();
        return Optional.of(serviceIds);
    }

    @Override
    public SmoothModuleSpace getModuleSpace(String moduleName) throws SmoothModuleNotFoundException {
        SmoothModuleId moduleId = this.moduleGearingMap.getKey2(moduleName);
        if (moduleId == null) {
            throw new SmoothModuleNotFoundException(moduleName);
        }

        return this.moduleGearingMap.getValue(moduleId);
    }

    @Override
    public void close() {
        this.moduleGearingMap.lock(() -> {
            this.moduleGearingMap.foreach((moduleId, moduleSpace) -> {
                try {
                    moduleSpace.close();
                } catch (Throwable t) {
                    this.logger.error(moduleId + " close error.", t);
                }
            });

            this.moduleGearingMap.close();
        });
    }

}
