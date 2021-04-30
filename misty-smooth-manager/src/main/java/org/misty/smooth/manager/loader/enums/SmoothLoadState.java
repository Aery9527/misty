package org.misty.smooth.manager.loader.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public enum SmoothLoadState {
    INITIAL,
    LOADING,
    OFFLINE,
    ONLINE,
    DESTROYING,
    DESTROYED;

    static {
        INITIAL.allowNextState = Collections.singleton(LOADING);
        LOADING.allowNextState = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(OFFLINE, DESTROYING)));
        OFFLINE.allowNextState = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ONLINE, DESTROYING)));
        ONLINE.allowNextState = Collections.singleton(DESTROYING);
        DESTROYING.allowNextState = Collections.singleton(DESTROYED);
        DESTROYED.allowNextState = Collections.emptySet();
    }

    private Set<SmoothLoadState> allowNextState;

    public SmoothLoadState toNext(SmoothLoadState nextState, Consumer<SmoothLoadState> denyAction) {
        if (this.allowNextState.contains(nextState)) {
            return nextState;
        } else {
            denyAction.accept(this);
            return null;
        }
    }

    public Set<SmoothLoadState> getAllowNextState() {
        return allowNextState;
    }

}
