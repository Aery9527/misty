package org.misty.smooth.core.tool;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.misty.smooth.api.vo.SmoothModuleId;

import java.time.Instant;
import java.util.Optional;

class SmoothIdGearingMapTest {

    @Test
    public void put$normal() {
        SmoothIdGearingMap<SmoothModuleId, String> gearingMap = new SmoothIdGearingMap<>();

        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());

        Optional<SmoothIdGearingMap.OldValue<SmoothModuleId, String>> oldValueOptional = gearingMap.put(moduleId.getModuleName(), moduleId, "9527");
        Assertions.assertThat(oldValueOptional).isEmpty();

        oldValueOptional = gearingMap.put(moduleId.getModuleName(), moduleId, "9487");
        Assertions.assertThat(oldValueOptional).isNotEmpty()
                .is(new Condition<Optional<SmoothIdGearingMap.OldValue<SmoothModuleId, String>>>() {
                    @Override
                    public boolean matches(Optional<SmoothIdGearingMap.OldValue<SmoothModuleId, String>> value) {
                        SmoothIdGearingMap.OldValue<SmoothModuleId, String> oldValue = value.get();
                        Assertions.assertThat(oldValue.getKey1()).isEqualTo(moduleId.getModuleName());
                        Assertions.assertThat(oldValue.getKey2()).isEqualTo(moduleId);
                        Assertions.assertThat(oldValue.getValue()).isEqualTo("9527");
                        return true;
                    }
                });
    }


}
