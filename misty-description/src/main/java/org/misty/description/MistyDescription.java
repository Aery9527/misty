package org.misty.description;

import java.util.Objects;

public class MistyDescription {

    public static final String FULL_NAME_FORMAT = MistyDescription.class.getSimpleName() + "(%s)(%s)";

    public static final String FULL_NAME_WITH_CLASS_FORMAT = FULL_NAME_FORMAT + "(%s)";

    private final String name;

    private final String version;

    private final String description;

    private final String fullName;

    private final String fullNameWithClass;

    public MistyDescription(String name, String version) {
        this(name, version, "");
    }

    public MistyDescription(String name, String version, String description) {
        this.name = name;
        this.version = version;
        this.fullName = String.format(FULL_NAME_FORMAT, name, version);
        this.fullNameWithClass = String.format(FULL_NAME_WITH_CLASS_FORMAT, name, version, getClass().getName());
        this.description = description == null ? "" : description;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name, this.version);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof MistyDescription) {
            MistyDescription mistyDescription = (MistyDescription) obj;
            return mistyDescription.name.equals(this.name) && mistyDescription.version.equals(this.version);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.fullName;
    }

    public final String getName() {
        return name;
    }

    public final String getVersion() {
        return version;
    }

    public final String getFullName() {
        return fullName;
    }

    public final String getFullNameWithClass() {
        return fullNameWithClass;
    }

    public String getDescription() {
        return description;
    }

}
