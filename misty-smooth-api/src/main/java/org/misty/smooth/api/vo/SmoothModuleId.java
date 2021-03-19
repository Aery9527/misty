package org.misty.smooth.api.vo;

import java.time.Instant;

public final class SmoothModuleId {

    public static final String FORMAT = "SmoothModule(%s)(%s)";

    private final String moduleName;

    private final String moduleVersion;

    private final Instant launchTime;

    private final String toString;

    public SmoothModuleId(String moduleName, String moduleVersion, Instant launchTime) {
        this.moduleName = moduleName;
        this.moduleVersion = moduleVersion;
        this.launchTime = launchTime;
        this.toString = String.format(FORMAT, this.moduleName, this.moduleVersion);
    }

    public String toStringWithLaunchTime() {
        return this.toString + "(" + this.launchTime + ")";
    }

    @Override
    public String toString() {
        return this.toString;
    }

    @Override
    public int hashCode() {
        return this.toString.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothModuleId) {
            return ((SmoothModuleId) obj).toString.equals(this.toString);
        } else {
            return false;
        }
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public Instant getLaunchTime() {
        return launchTime;
    }

}
