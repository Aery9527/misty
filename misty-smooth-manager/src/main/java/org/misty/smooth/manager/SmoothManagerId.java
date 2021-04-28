package org.misty.smooth.manager;

import org.misty.smooth.api.vo.SmoothId;

import java.time.Instant;

public class SmoothManagerId implements SmoothId<SmoothManagerId> {

    public static class Format {
        public static final String DESCRIPTION = "SmoothManagerId(%s)(%s)";
        public static final String DESCRIPTION_WITH_LAUNCH_TIME = DESCRIPTION + "(%s)";
    }

    private final String managerName;

    private final String managerVersion;

    private final Instant launchTime;

    private final String description;

    private String descriptionWithLaunchTime;

    public SmoothManagerId(String managerName, String managerVersion, Instant launchTime) {
        this.managerName = managerName;
        this.managerVersion = managerVersion;
        this.launchTime = launchTime;
        this.description = String.format(SmoothManagerId.Format.DESCRIPTION, this.managerName, this.managerVersion);
    }

    @Override
    public String getTypeKey() {
        return this.managerName;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothManagerId) {
            return ((SmoothManagerId) obj).description.equals(this.description);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(SmoothManagerId smoothManagerId) {
        String self = toString();
        String comer = smoothManagerId.toString();
        return self.compareTo(comer);
    }

    public String toStringWithLaunchTime() {
        if (this.descriptionWithLaunchTime == null) {
            this.descriptionWithLaunchTime = String.format(SmoothManagerId.Format.DESCRIPTION_WITH_LAUNCH_TIME,
                    this.managerName, this.managerVersion, this.launchTime);
        }

        return this.descriptionWithLaunchTime;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerVersion() {
        return managerVersion;
    }

    public Instant getLaunchTime() {
        return launchTime;
    }

}
