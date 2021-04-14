package org.misty.smooth.core.init;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.core.MistyDescription$SmoothCore;
import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.context.impl.SmoothCoreContextPreset;
import org.misty.smooth.core.context.impl.SmoothCoreEnvironmentPreset;
import org.misty.smooth.core.space.api.SmoothSpaceCamp;
import org.misty.smooth.core.space.impl.SmoothSpaceCampPreset;
import org.misty.util.verify.Examiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
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
            Examiner.refuseNullAndEmpty(term, target);
            setter.accept(target);
        }
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String[] args;

    private String name;

    private String version;

    private SmoothCoreEnvironment coreEnvironment;

    private Function<SmoothEnvironment, ExecutorService> executorServiceFactory = new SmoothCoreExecutorServiceFactory();

    private BiFunction<SmoothEnvironment, ExecutorService, SmoothSpaceCamp> spaceCampFactory;

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
        Examiner.refuseNullAndEmpty("name", this.name);
        Examiner.refuseNullAndEmpty("version", this.version);

        SmoothCoreContextPreset context = new SmoothCoreContextPreset(this.name, this.version);

        SmoothCoreEnvironment environment = setupEnvironment(context);
        ExecutorService executorService = setupExecutorService(context, environment);

        ContextSetter contextSetter = new ContextSetter(environment, executorService);
        contextSetter.set("smoothSpaceCamp", this.spaceCampFactory, SmoothSpaceCampPreset::new, context::setSpaceCamp);

        return context;
    }

    //

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
        Examiner.refuseNullAndEmpty("executorService", executorService);
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
        Examiner.refuseNullAndEmpty("executorServiceBuilder", executorServiceFactory);
        this.executorServiceFactory = executorServiceFactory;
    }

    public BiFunction<SmoothEnvironment, ExecutorService, SmoothSpaceCamp> getSpaceCampFactory() {
        return spaceCampFactory;
    }

    public void setSpaceCampFactory(BiFunction<SmoothEnvironment, ExecutorService, SmoothSpaceCamp> spaceCampFactory) {
        this.spaceCampFactory = spaceCampFactory;
    }
}
