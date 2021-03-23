package org.misty.smooth.core.init;

import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.context.impl.SmoothCoreContextPreset;
import org.misty.util.verify.Judge;

import java.util.function.Consumer;

public class SmoothCoreContextBuilder {

    private final String[] args;

    private SmoothCoreEnvironment coreEnvironment;

    public SmoothCoreContextBuilder() {
        this.args = new String[]{};
    }

    public SmoothCoreContextBuilder(String[] args) {
        this.args = args;
    }

    public SmoothCoreContext build() {
        SmoothCoreContextPreset smoothCoreContextPreset = new SmoothCoreContextPreset();

        setup(this.coreEnvironment, smoothCoreContextPreset::setEnvironment);

        smoothCoreContextPreset.getEnvironment().parseArgument(this.args);

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
}
