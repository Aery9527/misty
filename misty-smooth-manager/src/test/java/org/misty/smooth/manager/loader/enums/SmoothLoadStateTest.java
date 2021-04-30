package org.misty.smooth.manager.loader.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

class SmoothLoadStateTest {

    @Test
    public void check_all_enums_are_test() {
        Set<SmoothLoadState> testSet = new HashSet<>();
        testSet.add(SmoothLoadState.INITIAL);
        testSet.add(SmoothLoadState.LOADING);
        testSet.add(SmoothLoadState.LOAD_FAILED);
        testSet.add(SmoothLoadState.WAITING_ONLINE);
        testSet.add(SmoothLoadState.GOING_ONLINE);
        testSet.add(SmoothLoadState.ONLINE);
        testSet.add(SmoothLoadState.ONLINE_FAILED);
        testSet.add(SmoothLoadState.DESTROYING);
        testSet.add(SmoothLoadState.DESTROYED);

        Set<SmoothLoadState> allSet = new HashSet<>(Arrays.asList(SmoothLoadState.values()));
        allSet.removeAll(testSet);

        Assertions.assertThat(allSet).isEmpty();
    }

    @Test
    public void INITIAL_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.INITIAL.getAllowedNextStates())
                .containsExactlyInAnyOrder(SmoothLoadState.LOADING);
    }

    @Test
    public void LOADING_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.LOADING.getAllowedNextStates())
                .containsExactlyInAnyOrder(SmoothLoadState.WAITING_ONLINE, SmoothLoadState.LOAD_FAILED);
    }

    @Test
    void LOAD_FAILED_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.LOAD_FAILED.getAllowedNextStates())
                .containsExactlyInAnyOrder(SmoothLoadState.LOADING, SmoothLoadState.DESTROYING);
    }

    @Test
    public void WAITING_ONLINE_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.WAITING_ONLINE.getAllowedNextStates())
                .containsExactlyInAnyOrder(SmoothLoadState.GOING_ONLINE, SmoothLoadState.DESTROYING);
    }

    @Test
    public void GOING_ONLINE_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.GOING_ONLINE.getAllowedNextStates())
                .containsExactlyInAnyOrder(SmoothLoadState.ONLINE, SmoothLoadState.ONLINE_FAILED);
    }

    @Test
    public void ONLINE_FAILED_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.ONLINE_FAILED.getAllowedNextStates())
                .containsExactlyInAnyOrder(SmoothLoadState.GOING_ONLINE, SmoothLoadState.DESTROYING);
    }

    @Test
    public void ONLINE_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.ONLINE.getAllowedNextStates())
                .containsExactlyInAnyOrder(SmoothLoadState.DESTROYING);
    }

    @Test
    public void DESTROYING_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.DESTROYING.getAllowedNextStates())
                .containsExactlyInAnyOrder(SmoothLoadState.DESTROYED);
    }

    @Test
    public void DESTROYED_AllowNextState() {
        Assertions.assertThat(SmoothLoadState.DESTROYED.getAllowedNextStates()).isEmpty();
    }

    @Test
    public void toNext() {
        Arrays.asList(SmoothLoadState.values()).forEach((state) -> {
            Set<SmoothLoadState> set = new HashSet<>(Arrays.asList(SmoothLoadState.values()));
            Set<SmoothLoadState> allowedNextStates = state.getAllowedNextStates();

            String testMsg = "test";
            Consumer<SmoothLoadState> denyAction = (currentState) -> {
                Assertions.assertThat(currentState).isEqualTo(state);
                throw new RuntimeException(testMsg);
            };

            for (SmoothLoadState allowedNextState : allowedNextStates) {
                state.toNext(allowedNextState, denyAction);
                set.remove(allowedNextState);
            }

            for (SmoothLoadState deniedNextState : set) {
                Assertions.assertThatThrownBy(() -> state.toNext(deniedNextState, denyAction))
                        .isInstanceOf(RuntimeException.class)
                        .hasMessage(testMsg);
            }
        });
    }

}
