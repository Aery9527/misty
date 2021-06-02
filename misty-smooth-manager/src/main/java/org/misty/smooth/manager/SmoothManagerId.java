package org.misty.smooth.manager;

import org.misty.smooth.api.tool.LazyInitializer;
import org.misty.smooth.api.vo.SmoothId;

import java.time.Instant;

public class SmoothManagerId implements SmoothId<SmoothManagerId> {

    public static class Format {
        public static final String DESCRIPTION = "SmoothManager(%s)(%s)";
        public static final String DESCRIPTION_WITH_LAUNCH_TIME = DESCRIPTION + "(%s)";
    }

    private final String managerName;

    private final String managerVersion;

    private final Instant launchTime;

    private final LazyInitializer<String> description;

    private String descriptionWithLaunchTime;

    public SmoothManagerId(String managerName, String managerVersion) {
        this(managerName, managerVersion, Instant.now());
    }

    @SuppressWarnings("CodeBlock2Expr")
    public SmoothManagerId(String managerName, String managerVersion, Instant launchTime) {
        this.managerName = managerName;
        this.managerVersion = managerVersion;
        this.launchTime = launchTime;
        this.description = new LazyInitializer<>(() -> {
            return String.format(Format.DESCRIPTION, this.managerName, this.managerVersion);
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
        if (obj instanceof SmoothManagerId) {
            String desc1 = ((SmoothManagerId) obj).description.get();
            String desc2 = this.description.get();
            return desc1.equals(desc2);
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
            this.descriptionWithLaunchTime = String.format(Format.DESCRIPTION_WITH_LAUNCH_TIME,
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

    @Override
    public String getDescription() {
        return description.get();
    }

    public Instant getLaunchTime() {
        return launchTime;
    }

}
