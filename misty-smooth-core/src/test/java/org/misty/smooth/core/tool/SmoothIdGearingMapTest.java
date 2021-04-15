package org.misty.smooth.core.tool;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.util.error.MistyException;
import org.misty.util.verify.ExaminerMessage;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

class SmoothIdGearingMapTest {

    private SmoothIdGearingMap<SmoothModuleId, String> gearingMap;

    @BeforeEach
    public void before() {
        this.gearingMap = new SmoothIdGearingMap<>();
    }

    @Test
    public void put$normal() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());

        Optional<SmoothIdGearingMap.OldValue<SmoothModuleId, String>> oldValueOptional;
        oldValueOptional = this.gearingMap.put(moduleId.getModuleName(), moduleId, "9527");
        Assertions.assertThat(oldValueOptional).isEmpty();

        oldValueOptional = this.gearingMap.put(moduleId.getModuleName(), moduleId, "9487");
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

    @Test
    public void put$error() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());

        Assertions.assertThatThrownBy(() -> this.gearingMap.put(null, moduleId, "9527"))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key1"));
        Assertions.assertThatThrownBy(() -> this.gearingMap.put(moduleId.getModuleName(), null, "9527"))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key2"));
        Assertions.assertThatThrownBy(() -> this.gearingMap.put(moduleId.getModuleName(), moduleId, null))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));

        // TODO
    }

    @Test
    public void getKey2$normal() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());
        this.gearingMap.put(moduleId.getModuleName(), moduleId, "007");
        Assertions.assertThat(this.gearingMap.getKey2(moduleId.getModuleName())).isEqualTo(moduleId);
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void getKey2$error(String key1) {
        Assertions.assertThatThrownBy(() -> this.gearingMap.getKey2(key1))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", " "})
    public void getValue1$normal(String key1) {
        SmoothModuleId moduleId = new SmoothModuleId(key1, "b", Instant.now());
        String value = "9527";

        this.gearingMap.put(key1, moduleId, value);

        Assertions.assertThat(this.gearingMap.getValue(key1)).isEqualTo(value);
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void getValue1$error(String key1) {
        Assertions.assertThatThrownBy(() -> this.gearingMap.getValue(key1))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key1"));
    }

    @Test
    public void getValue2$normal() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());
        String value = "9527";

        this.gearingMap.put(moduleId.getModuleName(), moduleId, value);

        Assertions.assertThat(this.gearingMap.getValue(moduleId)).isEqualTo(value);
    }

    @Test
    public void getValue2$error() {
        Assertions.assertThatThrownBy(() -> this.gearingMap.getValue((SmoothModuleId) null))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key2"));
    }

    @Test
    public void listKey2() {
        SmoothModuleId moduleId1 = new SmoothModuleId("1", "v1", Instant.now());
        SmoothModuleId moduleId2 = new SmoothModuleId("2", "v2", Instant.now());
        SmoothModuleId moduleId3 = new SmoothModuleId("3", "v3", Instant.now());

        this.gearingMap.put(moduleId1.getModuleName(), moduleId1, "1");
        this.gearingMap.put(moduleId3.getModuleName(), moduleId3, "3");
        this.gearingMap.put(moduleId2.getModuleName(), moduleId2, "2");

        Set<SmoothModuleId> keys = this.gearingMap.listKey2();

        Assertions.assertThatThrownBy(() -> keys.add(new SmoothModuleId("a", "b", Instant.now())))
                .isInstanceOf(UnsupportedOperationException.class);

        Assertions.assertThat(keys).containsExactly(moduleId1, moduleId2, moduleId3);
    }

    @Test
    public void lock() {

    }

    @Test
    public void foreach() {

    }

    @Test
    public void close() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());

        this.gearingMap.close();

        Assertions.assertThatThrownBy(() -> this.gearingMap.put(moduleId.getModuleName(), moduleId, "123"))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> this.gearingMap.getKey2("123"))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> this.gearingMap.getValue("123"))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> this.gearingMap.getValue(moduleId))
                .isInstanceOf(NullPointerException.class);
    }

}
