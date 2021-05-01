package org.misty.smooth.manager.loader.enums;


public enum SmoothLoadType {
    /**
     * no same module is load, so this time is all brand new loading.
     */
    NEW,
    /**
     * there is same module loaded, so this time is replace loading.
     */
    SWITCH,
    /**
     * there is same module loaded, but not allow replace.
     */
    DUPLICATE;
}
