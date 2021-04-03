package org.misty.smooth.core.init;

import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.context.impl.SmoothCoreContextPreset;
import org.misty.util.verify.Examiner;
import org.misty.util.verify.Judge;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;

public class SmoothCoreContextBuilder {

    private final String[] args;

    private SmoothCoreEnvironment coreEnvironment;

    private Function<SmoothCoreEnvironment, ExecutorService> executorServiceBuilder = new SmoothCoreExecutorServiceBuilder();

    public SmoothCoreContextBuilder() {
        this.args = new String[]{};
    }

    public SmoothCoreContextBuilder(String[] args) {
        this.args = args;
    }

    public SmoothCoreContext build() {
        SmoothCoreContextPreset smoothCoreContextPreset = new SmoothCoreContextPreset();

        setup(this.coreEnvironment, smoothCoreContextPreset::setEnvironment);

        SmoothCoreEnvironment usedEnvironment = smoothCoreContextPreset.getEnvironment();
        usedEnvironment.parseArgument(this.args);

        ExecutorService executorService = this.executorServiceBuilder.apply(usedEnvironment);
        Examiner.refuseNullAndEmpty("executorService", executorService);
        smoothCoreContextPreset.setExecutorService(executorService);

        return smoothCoreContextPreset;
    }

    private <FieldType> void setup(FieldType field, Consumer<FieldType> setter) {
        if (Judge.notNullAndEmpty(field)) {
            setter.accept(field);
        }
    }

    public SmoothCoreEnvironment getCoreEnvironment() {
        return coreEnvironment;
    }

    public void setCoreEnvironment(SmoothCoreEnvironment coreEnvironment) {
        this.coreEnvironment = coreEnvironment;
    }

    public Function<SmoothCoreEnvironment, ExecutorService> getExecutorServiceBuilder() {
        return executorServiceBuilder;
    }

    public void setExecutorServiceBuilder(Function<SmoothCoreEnvironment, ExecutorService> executorServiceBuilder) {
        Examiner.refuseNullAndEmpty("executorServiceBuilder", executorServiceBuilder);
        this.executorServiceBuilder = executorServiceBuilder;
    }

}
