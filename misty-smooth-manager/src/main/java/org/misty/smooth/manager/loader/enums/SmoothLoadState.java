package org.misty.smooth.manager.loader.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public enum SmoothLoadState {
    INITIAL,
    LOADING,
    LOAD_FAILED,
    WAITING_ONLINE,
    GOING_ONLINE,
    ONLINE,
    ONLINE_FAILED,
    DESTROYING,
    DESTROYED;

    static {
        INITIAL.allowedNextState = collect(LOADING);
        LOADING.allowedNextState = collect(WAITING_ONLINE, LOAD_FAILED);
        LOAD_FAILED.allowedNextState = collect(LOADING, DESTROYING);
        WAITING_ONLINE.allowedNextState = collect(GOING_ONLINE, DESTROYING);
        GOING_ONLINE.allowedNextState = collect(ONLINE, ONLINE_FAILED);
        ONLINE_FAILED.allowedNextState = collect(GOING_ONLINE, DESTROYING);
        ONLINE.allowedNextState = collect(DESTROYING);
        DESTROYING.allowedNextState = collect(DESTROYED);
        DESTROYED.allowedNextState = collect();
    }

    private static Set<SmoothLoadState> collect(SmoothLoadState... states) {
        if (states.length == 0) {
            return Collections.emptySet();
        } else if (states.length == 1) {
            return Collections.singleton(states[0]);
        } else {
            return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(states)));
        }
    }

    private Set<SmoothLoadState> allowedNextState;

    public SmoothLoadState toNext(SmoothLoadState nextState, Consumer<SmoothLoadState> denyAction) {
        if (this.allowedNextState.contains(nextState)) {
            return nextState;
        } else {
            denyAction.accept(this);
            return this;
        }
    }

    public Set<SmoothLoadState> getAllowedNextStates() {
        return allowedNextState;
    }

}
