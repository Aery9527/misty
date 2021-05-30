package org.misty.smooth.manager;

import org.misty.smooth.api.vo.SmoothId;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class SmoothManagerId implements SmoothId<SmoothManagerId> {

    public static class Format {
        public static final String DESCRIPTION = "SmoothManagerId(%s)(%s)";
        public static final String DESCRIPTION_WITH_LAUNCH_TIME = DESCRIPTION + "(%s)";
    }

    private final String managerName;

    private final String managerVersion;

    private final Instant launchTime;

    private final AtomicReference<Supplier<String>> description = new AtomicReference<>();

    private String descriptionWithLaunchTime;

    public SmoothManagerId(String managerName, String managerVersion) {
        this(managerName, managerVersion, Instant.now());
    }

    public SmoothManagerId(String managerName, String managerVersion, Instant launchTime) {
        this.managerName = managerName;
        this.managerVersion = managerVersion;
        this.launchTime = launchTime;
        this.description.set(() -> {
            String description = String.format(Format.DESCRIPTION, this.managerName, this.managerVersion);
            this.description.set(() -> description);
            return description;
        });
    }

    @Override
    public String toString() {
        return toStringWithLaunchTime();
    }

    @Override
    public int hashCode() {
        String desc = this.description.get().get();
        return desc.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmoothManagerId) {
            String desc1 = ((SmoothManagerId) obj).description.get().get();
            String desc2 = this.description.get().get();
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
        return description.get().get();
    }

    public Instant getLaunchTime() {
        return launchTime;
    }

}
