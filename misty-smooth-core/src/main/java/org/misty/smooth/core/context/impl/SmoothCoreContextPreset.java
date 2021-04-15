package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.space.api.SmoothSpaceCamp;
import org.misty.smooth.core.space.module.api.SmoothModuleSpace;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.util.fi.FiRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SmoothCoreContextPreset implements SmoothCoreContext {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Instant launchInstant = Instant.now();

    private final SmoothModuleId coreModuleId;

    private SmoothCoreEnvironment environment;

    private ExecutorService executorService;

    private SmoothSpaceCamp spaceCamp;

    public SmoothCoreContextPreset(String name, String version) {
        this.coreModuleId = new SmoothModuleId(name, version, this.launchInstant);
    }

    @Override
    public String identifier() {
        return this.coreModuleId.toString();
    }

    @Override
    public Instant getLaunchInstant() {
        return this.launchInstant;
    }

    @Override
    public SmoothCoreEnvironment getEnvironment() {
        return this.environment;
    }

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return this.spaceCamp.listModuleWithSet();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return this.spaceCamp.listServiceWithSet(moduleName);
    }

    @Override
    public Future<SmoothServiceResponseResult> invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        SmoothModuleSpace moduleSpace = this.spaceCamp.getModuleSpace(moduleName);
        return moduleSpace.invokeService(serviceKey, new SmoothServiceRequestOrigin(this.coreModuleId, serviceRequest));
    }

    @Override
    public void invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        SmoothModuleSpace moduleSpace = this.spaceCamp.getModuleSpace(moduleName);
        SmoothServiceRequestOrigin requestOrigin = new SmoothServiceRequestOrigin(this.coreModuleId, serviceRequest);
        moduleSpace.invokeService(serviceKey, requestOrigin, resultProcessor, this.executorService);
    }

    @Override
    public void loadSmoothManager(Collection<URL> sources) throws SmoothLoadException {
        // FIXME
    }

    @Override
    public void loadSmoothModule(Collection<URL> sources) throws SmoothLoadException {
        // FIXME
    }

    @Override
    public void close() {
        BiConsumer<String, FiRunnable> errorHandle = (name, action) -> {
            try {
                action.runOrThrow();
            } catch (Throwable t) {
                String msg = SmoothCoreContextPreset.class.getSimpleName() + "(" + this.coreModuleId + ") close action \"" + name + "\" error.";
                this.logger.error(msg, t);
            }
        };

        errorHandle.accept("spaceCamp.close", this.spaceCamp::close);
        errorHandle.accept("executorService.shutdown", this.executorService::shutdown);
    }

    public void setEnvironment(SmoothCoreEnvironment environment) {
        this.environment = environment;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setSpaceCamp(SmoothSpaceCamp spaceCamp) {
        this.spaceCamp = spaceCamp;
    }

}
