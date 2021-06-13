package org.misty.smooth.manager.loader.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class SmoothLoadFinishStateTest {

    @Test
    void fromLoadState() {
        Set<SmoothLoadState> canBeFinishStateSet = new HashSet<>();
        canBeFinishStateSet.add(SmoothLoadState.LOAD_IGNORE);
        canBeFinishStateSet.add(SmoothLoadState.LOAD_FAILED);
        canBeFinishStateSet.add(SmoothLoadState.WAITING_ONLINE);

        for (SmoothLoadState value : SmoothLoadState.values()) {
            Optional<SmoothLoadFinishState> finishState = SmoothLoadFinishState.fromLoadState(value);

            if (canBeFinishStateSet.contains(value)) {
                SmoothLoadFinishState matchState = SmoothLoadFinishState.valueOf(value.name());
                Assertions.assertThat(finishState).isNotEmpty().get().isEqualTo(matchState);
            } else {
                Assertions.assertThat(finishState).isEmpty();
            }
        }
    }

    @Test
    void isIgnore() {
        Assertions.assertThat(SmoothLoadFinishState.LOAD_IGNORE.isIgnore()).isTrue();
        Assertions.assertThat(SmoothLoadFinishState.LOAD_IGNORE.isFailed()).isFalse();
        Assertions.assertThat(SmoothLoadFinishState.LOAD_IGNORE.isWaitingOnline()).isFalse();
    }

    @Test
    void isFailed() {
        Assertions.assertThat(SmoothLoadFinishState.LOAD_FAILED.isIgnore()).isFalse();
        Assertions.assertThat(SmoothLoadFinishState.LOAD_FAILED.isFailed()).isTrue();
        Assertions.assertThat(SmoothLoadFinishState.LOAD_FAILED.isWaitingOnline()).isFalse();
    }

    @Test
    void isWaitingOnline() {
        Assertions.assertThat(SmoothLoadFinishState.WAITING_ONLINE.isIgnore()).isFalse();
        Assertions.assertThat(SmoothLoadFinishState.WAITING_ONLINE.isFailed()).isFalse();
        Assertions.assertThat(SmoothLoadFinishState.WAITING_ONLINE.isWaitingOnline()).isTrue();
    }

}
