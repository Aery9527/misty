package org.misty.smooth.api.vo;

import org.misty.smooth.api.tool.LazyInitializer;

import java.time.Instant;

public final class SmoothModuleId implements SmoothId<SmoothModuleId> {

    public static class Format {
        public static final String DESCRIPTION = "SmoothModule(%s)(%s)";
        public static final String DESCRIPTION_WITH_LAUNCH_TIME = DESCRIPTION + "(%s)";
    }

    public static final String PRESET_VERSION = "<PRESET_VERSION>";

    private final String moduleName;

    private final String moduleVersion;

    private final Instant launchTime;

    private final LazyInitializer<String> description;

    private String descriptionWithLaunchTime;

    public SmoothModuleId(String moduleName, String moduleVersion) {
        this(moduleName, moduleVersion, Instant.now());
    }

    @SuppressWarnings("CodeBlock2Expr")
    public SmoothModuleId(String moduleName, String moduleVersion, Instant launchTime) {
        this.moduleName = moduleName;
        this.moduleVersion = moduleVersion;
        this.launchTime = launchTime;
        this.description = new LazyInitializer<>(() -> {
            return String.format(Format.DESCRIPTION, this.moduleName, this.moduleVersion);
        }, false);
    }

    @Override
    public String toString() {
        return toStringWithLaunchTime();
    }

    @Override
    public int hashCode() {
        String desc = this.description.get();
        return desc.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothModuleId) {
            String desc1 = ((SmoothModuleId) obj).description.get();
            String desc2 = this.description.get();
            return desc1.equals(desc2);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(SmoothModuleId smoothModuleId) {
        String self = toString();
        String comer = smoothModuleId.toString();
        return self.compareTo(comer);
    }

    public String toStringWithLaunchTime() {
        if (this.descriptionWithLaunchTime == null) {
            this.descriptionWithLaunchTime = String.format(Format.DESCRIPTION_WITH_LAUNCH_TIME,
                    this.moduleName, this.moduleVersion, this.launchTime);
        }

        return this.descriptionWithLaunchTime;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    @Override
    public String getDescription() {
        return description.get();
    }

    public Instant getLaunchTime() {
        return launchTime;
    }

}
