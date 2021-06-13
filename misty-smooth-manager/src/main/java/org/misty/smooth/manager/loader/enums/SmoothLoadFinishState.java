package org.misty.smooth.manager.loader.enums;

import java.util.Optional;

public enum SmoothLoadFinishState {
    LOAD_IGNORE,
    LOAD_FAILED,
    WAITING_ONLINE;

    public static Optional<SmoothLoadFinishState> fromLoadState(SmoothLoadState loadState) {
        if (SmoothLoadState.LOAD_IGNORE.equals(loadState)) {
            return Optional.of(LOAD_IGNORE);
        } else if (SmoothLoadState.LOAD_FAILED.equals(loadState)) {
            return Optional.of(LOAD_FAILED);
        } else if (SmoothLoadState.WAITING_ONLINE.equals(loadState)) {
            return Optional.of(WAITING_ONLINE);
        } else {
            return Optional.empty();
        }
    }

    public boolean isIgnore() {
        return this.equals(LOAD_IGNORE);
    }

    public boolean isFailed() {
        return this.equals(LOAD_FAILED);
    }

    public boolean isWaitingOnline() {
        return this.equals(WAITING_ONLINE);
    }

}
