package org.misty.smooth.core.space.impl;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.space.api.SmoothSpaceCamp;
import org.misty.smooth.core.space.module.api.SmoothModuleSpace;
import org.misty.util.ex.ReentrantLockEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SmoothSpaceCampPreset implements SmoothSpaceCamp {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ReentrantLockEx moduleMaintainLock = new ReentrantLockEx();

    private Map<String, SmoothModuleId> moduleNameMapId = new TreeMap<>();

    private Map<SmoothModuleId, SmoothModuleSpace> moduleIdMapSpace = new TreeMap<>();

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return Collections.unmodifiableSet(this.moduleIdMapSpace.keySet());
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        SmoothModuleId moduleId = this.moduleNameMapId.get(moduleName);
        if (moduleId == null) {
            return Optional.empty();
        }

        SmoothModuleSpace moduleSpace = this.moduleIdMapSpace.get(moduleId);
        Set<SmoothServiceId> serviceIds = moduleSpace.listServices();
        return Optional.of(serviceIds);
    }

    @Override
    public SmoothModuleSpace getModuleSpace(String moduleName) throws SmoothModuleNotFoundException {
        SmoothModuleId moduleId = this.moduleNameMapId.get(moduleName);
        if (moduleId == null) {
            throw new SmoothModuleNotFoundException(moduleName);
        }

        return this.moduleIdMapSpace.get(moduleId);
    }

    @Override
    public void close() {
        this.moduleMaintainLock.use(() -> {
            this.moduleIdMapSpace.forEach((moduleId, moduleSpace) -> {
                try {
                    moduleSpace.close();
                } catch (Throwable t) {
                    this.logger.error(moduleId + " close error.", t);
                }
            });

            this.moduleNameMapId = null;
            this.moduleIdMapSpace = null;
        });
    }

}
