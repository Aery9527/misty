package org.misty.smooth.manager.loader.enums;

public enum SmoothLoadType {
    /**
     * 指當前無相同的moduleName被載入, 所以該次為全新載入的module.
     */
    NEW,
    /**
     * 指當前已有相同的moduleName被載入, 所以該次為替換載入的module.
     */
    SWITCH;
}
