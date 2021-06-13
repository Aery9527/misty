package org.misty.smooth.api.context;

public enum SmoothLoadType {
    /**
     * No same module is load, so this time is all brand new loading.
     */
    NEW,
    /**
     * There is same module loaded, so this time is replace loading.
     */
    REPLACE,
    /**
     * The same module is being loaded at the same time, so this time not execute load.
     */
    IGNORE;

    public boolean isNew() {
        return this.equals(NEW);
    }

    public boolean isReplace() {
        return this.equals(REPLACE);
    }

    public boolean isIgnore() {
        return this.equals(IGNORE);
    }

}
