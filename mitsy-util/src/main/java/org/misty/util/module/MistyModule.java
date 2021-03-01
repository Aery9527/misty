package org.misty.util.module;

import java.util.Objects;

public abstract class MistyModule {

    public static final String FULL_NAME_FORMAT = "MistyModule(%s)(%s)";

    public static final String FULL_NAME_WITH_CLASS_FORMAT = FULL_NAME_FORMAT + "(%s)";

    private final String name;

    private final String version;

    private final String description;

    private final String fullName;

    private final String fullNameWithClass;

    public MistyModule(String name, String version) {
        this(name, version, "");
    }

    public MistyModule(String name, String version, String description) {
        // TODO 要做檢查
        this.name = name;
        this.version = version;
        this.description = description;
        this.fullName = String.format(FULL_NAME_FORMAT, name, version);
        this.fullNameWithClass = String.format(FULL_NAME_WITH_CLASS_FORMAT, name, version, getClass().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.version);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MistyModule) {
            MistyModule mistyModule = (MistyModule) obj;
            return mistyModule.name.equals(this.name) && mistyModule.version.equals(this.version);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.fullNameWithClass;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFullNameWithClass() {
        return fullNameWithClass;
    }
}
