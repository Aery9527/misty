package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.context.api.SmoothModuleDomainCamp;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderFactory;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoader;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoaderCrosser;
import org.misty.smooth.core.domain.module.api.SmoothModuleDomain;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoader;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoaderCrosser;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.error.SmoothCloseException;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothManagerLoader;
import org.misty.smooth.manager.loader.SmoothModuleLoader;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.misty.util.fi.FiRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SmoothCoreContextPreset implements SmoothCoreContext {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SmoothManagerId coreId;

    private SmoothCoreEnvironment environment;

    private ExecutorService executorService;

    private SmoothModuleDomainCamp domainCamp;

    private SmoothDomainLoaderFactory domainLoaderFactory;

    public SmoothCoreContextPreset(String name, String version) {
        this.coreId = new SmoothManagerId(name, version);
    }

    @Override
    public String toString() {
        return getIdentifier();
    }

    @Override
    public String getIdentifier() {
        return this.coreId.toString();
    }

    @Override
    public Instant getLaunchInstant() {
        return this.coreId.getLaunchTime();
    }

    @Override
    public SmoothCoreEnvironment getEnvironment() {
        return this.environment;
    }

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return this.domainCamp.listModuleWithSet();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return this.domainCamp.listServiceWithSet(moduleName);
    }

    @Override
    public Future<SmoothServiceResponseResult> invokeService(
            String moduleName, String serviceKey, SmoothServiceRequest serviceRequest
    ) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        SmoothModuleDomain moduleDomain = this.domainCamp.getModuleDomain(moduleName);
        return moduleDomain.invokeService(serviceKey, new SmoothServiceRequestOrigin(this.coreId, serviceRequest));
    }

    @Override
    public void invokeService(
            String moduleName, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor
    ) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        SmoothModuleDomain moduleDomain = this.domainCamp.getModuleDomain(moduleName);
        SmoothServiceRequestOrigin requestOrigin = new SmoothServiceRequestOrigin(this.coreId, serviceRequest);
        moduleDomain.invokeService(serviceKey, requestOrigin, this.executorService, resultProcessor); // FIXME resultProcessor要crosser
    }

    @Override
    public SmoothManagerLoader loadSmoothManager(SmoothLoaderArgument loaderArgument, Collection<URL> sources) throws SmoothLoadException {
        loaderArgument.lock();

        SmoothManagerDomainLoader managerLoader = this.domainLoaderFactory.buildManagerLoader(loaderArgument, sources);
//        managerLoader.setLoadTypeController();
        // TODO
        managerLoader.launch();

        return new SmoothManagerDomainLoaderCrosser(managerLoader);
    }

    @Override
    public SmoothModuleLoader loadSmoothModule(SmoothLoaderArgument loaderArgument, Collection<URL> sources) throws SmoothLoadException {
        loaderArgument.lock();

        SmoothModuleDomainLoader moduleLoader = this.domainLoaderFactory.buildModuleLoader(loaderArgument, sources);
        moduleLoader.setLoadTypeController(this.domainCamp);
        // TODO
        moduleLoader.launch();

        return new SmoothModuleDomainLoaderCrosser(moduleLoader);
    }

    @Override
    public void close() throws SmoothCloseException {
        BiConsumer<String, FiRunnable> errorHandle = (name, action) -> {
            try {
                action.runOrThrow();
            } catch (Throwable t) {
                String msg = SmoothCoreContextPreset.class.getSimpleName() + "(" + this.coreId + ") close action \"" + name + "\" error.";
                this.logger.error(msg, t);
            }
        };

        errorHandle.accept("domainCamp.close", this.domainCamp::close);
        errorHandle.accept("executorService.shutdown", this.executorService::shutdown);
    }

    public void setEnvironment(SmoothCoreEnvironment environment) {
        this.environment = new SmoothCoreEnvironmentCrosser(environment);
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setDomainCamp(SmoothModuleDomainCamp domainCamp) {
        this.domainCamp = domainCamp;
    }

    public void setDomainLoaderFactory(SmoothDomainLoaderFactory domainLoaderFactory) {
        this.domainLoaderFactory = domainLoaderFactory;
    }

}
