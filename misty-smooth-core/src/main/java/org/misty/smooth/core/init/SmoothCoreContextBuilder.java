package org.misty.smooth.core.init;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.core.MistyDescription$SmoothCore;
import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.context.api.SmoothModuleDomainCamp;
import org.misty.smooth.core.context.impl.SmoothCoreContextCrosser;
import org.misty.smooth.core.context.impl.SmoothCoreContextPreset;
import org.misty.smooth.core.context.impl.SmoothCoreEnvironmentPreset;
import org.misty.smooth.core.context.impl.SmoothModuleDomainCampPreset;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderFactory;
import org.misty.smooth.core.domain.loader.impl.SmoothDomainLoaderFactoryPreset;
import org.misty.smooth.core.error.SmoothCoreError;
import org.misty.util.verify.Examiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SmoothCoreContextBuilder {

    private static class ContextSetter {
        private final SmoothCoreEnvironment environment;

        private final ExecutorService executorService;

        public ContextSetter(SmoothCoreEnvironment environment, ExecutorService executorService) {
            this.environment = environment;
            this.executorService = executorService;
        }

        private <TargetType> void set(String term, BiFunction<SmoothEnvironment, ExecutorService, TargetType> factory,
                                      Supplier<TargetType> presetSupplier, Consumer<TargetType> setter) {
            TargetType target = factory == null ? presetSupplier.get() : factory.apply(this.environment, this.executorService);
            Examiner.refuseNullAndEmpty(term, target, SmoothCoreError.ARGUMENT_ERROR);
            setter.accept(target);
        }
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String[] args;

    private String name;

    private String version;

    private SmoothCoreEnvironment coreEnvironment;

    private Function<SmoothEnvironment, ExecutorService> executorServiceFactory = new SmoothCoreExecutorServiceFactory();

    private BiFunction<SmoothEnvironment, ExecutorService, SmoothModuleDomainCamp> domainCampFactory;

    private BiFunction<SmoothEnvironment, ExecutorService, SmoothDomainLoaderFactory> domainLoaderFactoryFactory;

    public SmoothCoreContextBuilder() {
        this(new String[]{});
    }

    public SmoothCoreContextBuilder(String[] args) {
        this.args = args;

        MistyDescription$SmoothCore smoothCoreDescription = new MistyDescription$SmoothCore();
        this.name = smoothCoreDescription.getName();
        this.version = smoothCoreDescription.getVersion();
    }

    public SmoothCoreContext build() {
        Examiner.refuseNullAndEmpty("name", this.name, SmoothCoreError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty("version", this.version, SmoothCoreError.ARGUMENT_ERROR);

        SmoothCoreContextPreset context = supplySmoothCoreContextPreset();

        SmoothCoreEnvironment environment = setupEnvironment(context);
        ExecutorService executorService = setupExecutorService(context, environment);

        ContextSetter contextSetter = new ContextSetter(environment, executorService);
        contextSetter.set("domainCampFactory", this.domainCampFactory, SmoothModuleDomainCampPreset::new, context::setDomainCamp);
        contextSetter.set("domainLoaderFactoryFactory", this.domainLoaderFactoryFactory, SmoothDomainLoaderFactoryPreset::new,
                context::setDomainLoaderFactory);

        return new SmoothCoreContextCrosser(context);
    }

    //

    protected SmoothCoreContextPreset supplySmoothCoreContextPreset() {
        return new SmoothCoreContextPreset(this.name, this.version);
    }

    protected SmoothCoreEnvironment setupEnvironment(SmoothCoreContextPreset context) {
        SmoothCoreEnvironment usedEnvironment = this.coreEnvironment;
        if (usedEnvironment == null) {
            usedEnvironment = new SmoothCoreEnvironmentPreset();
        }

        usedEnvironment.parseArgument(this.args);
        context.setEnvironment(usedEnvironment);

        showEnvironment(usedEnvironment);

        return usedEnvironment;
    }

    protected void showEnvironment(SmoothCoreEnvironment environment) {
        String prefix = SmoothEnvironment.class.getSimpleName();
        this.logger.info(prefix + " flags : " + environment.getFlags());
        this.logger.info(prefix + " arguments : " + environment.getArguments());
    }

    protected ExecutorService setupExecutorService(SmoothCoreContextPreset context, SmoothCoreEnvironment environment) {
        ExecutorService executorService = this.executorServiceFactory.apply(environment);
        Examiner.refuseNullAndEmpty("executorService", executorService, SmoothCoreError.ARGUMENT_ERROR);
        context.setExecutorService(executorService);
        return executorService;
    }

    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public SmoothCoreEnvironment getCoreEnvironment() {
        return coreEnvironment;
    }

    public void setCoreEnvironment(SmoothCoreEnvironment coreEnvironment) {
        this.coreEnvironment = coreEnvironment;
    }

    public Function<SmoothEnvironment, ExecutorService> getExecutorServiceFactory() {
        return executorServiceFactory;
    }

    public void setExecutorServiceFactory(Function<SmoothEnvironment, ExecutorService> executorServiceFactory) {
        Examiner.refuseNullAndEmpty("executorServiceBuilder", executorServiceFactory, SmoothCoreError.ARGUMENT_ERROR);
        this.executorServiceFactory = executorServiceFactory;
    }

    public BiFunction<SmoothEnvironment, ExecutorService, SmoothModuleDomainCamp> getDomainCampFactory() {
        return domainCampFactory;
    }

    public void setDomainCampFactory(BiFunction<SmoothEnvironment, ExecutorService, SmoothModuleDomainCamp> domainCampFactory) {
        this.domainCampFactory = domainCampFactory;
    }

    public BiFunction<SmoothEnvironment, ExecutorService, SmoothDomainLoaderFactory> getDomainLoaderFactoryFactory() {
        return domainLoaderFactoryFactory;
    }

    public void setDomainLoaderFactoryFactory(BiFunction<SmoothEnvironment, ExecutorService, SmoothDomainLoaderFactory> domainLoaderFactoryFactory) {
        this.domainLoaderFactoryFactory = domainLoaderFactoryFactory;
    }

}
