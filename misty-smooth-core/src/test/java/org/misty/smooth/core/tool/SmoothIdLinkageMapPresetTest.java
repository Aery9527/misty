package org.misty.smooth.core.tool;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.util.error.MistyException;
import org.misty.util.verify.ExaminerMessage;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

class SmoothIdLinkageMapPresetTest {

    public static class TestId implements SmoothId<TestId> {

        private final String key;

        public TestId(String key) {
            this.key = key;
        }

        @Override
        public String getTypeKey() {
            return this.key;
        }

        @Override
        public int compareTo(TestId o) {
            return 1;
        }
    }

    private SmoothIdLinkageMap<SmoothModuleId, String> gearingMap;

    @BeforeEach
    public void before() {
        this.gearingMap = new SmoothIdLinkageMap<>();
    }

    @Test
    public void put$normal() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());

        Optional<SmoothIdLinkageMap.OldTuple<SmoothModuleId, String>> oldValueOptional;
        oldValueOptional = this.gearingMap.put(moduleId, "9527");
        Assertions.assertThat(oldValueOptional).isEmpty();

        oldValueOptional = this.gearingMap.put(moduleId, "9487");
        Assertions.assertThat(oldValueOptional).isNotEmpty()
                .is(new Condition<Optional<SmoothIdLinkageMap.OldTuple<SmoothModuleId, String>>>() {
                    @Override
                    public boolean matches(Optional<SmoothIdLinkageMap.OldTuple<SmoothModuleId, String>> value) {
                        SmoothIdLinkageMap.OldTuple<SmoothModuleId, String> oldValue = value.get();
                        Assertions.assertThat(oldValue.getTypeKey()).isEqualTo(moduleId.getModuleName());
                        Assertions.assertThat(oldValue.getKey()).isEqualTo(moduleId);
                        Assertions.assertThat(oldValue.getValue()).isEqualTo("9527");
                        return true;
                    }
                });
    }

    @Test
    public void put$error() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "v1", Instant.now());
        Assertions.assertThatThrownBy(() -> this.gearingMap.put(null, "9527"))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key"));
        Assertions.assertThatThrownBy(() -> this.gearingMap.put(moduleId, null))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));

        SmoothIdLinkageMap<TestId, String> testMap = new SmoothIdLinkageMap<>();
        String typeKey = "1";
        TestId test1 = new TestId(typeKey);
        TestId test2 = new TestId(typeKey);
        testMap.put(test1, "qq");
        Assertions.assertThatThrownBy(() -> testMap.put(test2, "gg"))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(String.format(SmoothIdLinkageMap.ErrorMsgFormat.SMOOTH_ID_LINKAGE_ERROR, test1, test2));
    }

    @Test
    public void getKey2$normal() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());
        this.gearingMap.put(moduleId, "007");
        Assertions.assertThat(this.gearingMap.getKey(moduleId.getModuleName())).isEqualTo(moduleId);
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void getKey2$error(String typeKey) {
        Assertions.assertThatThrownBy(() -> this.gearingMap.getKey(typeKey))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("typeKey"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", " "})
    public void getValue1$normal(String typeKey) {
        SmoothModuleId moduleId = new SmoothModuleId(typeKey, "b", Instant.now());
        String value = "9527";

        this.gearingMap.put(moduleId, value);

        Assertions.assertThat(this.gearingMap.getValue(typeKey)).isEqualTo(value);
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void getValue1$error(String typeKey) {
        Assertions.assertThatThrownBy(() -> this.gearingMap.getValue(typeKey))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("typeKey"));
    }

    @Test
    public void getValue2$normal() {
        SmoothModuleId moduleId = new SmoothModuleId("a", "b", Instant.now());
        String value = "9527";

        this.gearingMap.put(moduleId, value);

        Assertions.assertThat(this.gearingMap.getValue(moduleId)).isEqualTo(value);
    }

    @Test
    public void getValue2$error() {
        Assertions.assertThatThrownBy(() -> this.gearingMap.getValue((SmoothModuleId) null))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key"));
    }

    @Test
    public void listKey2() {
        SmoothModuleId moduleId1 = new SmoothModuleId("1", "v1", Instant.now());
        SmoothModuleId moduleId2 = new SmoothModuleId("2", "v2", Instant.now());
        SmoothModuleId moduleId3 = new SmoothModuleId("3", "v3", Instant.now());

        this.gearingMap.put(moduleId1, "1");
        this.gearingMap.put(moduleId3, "3");
        this.gearingMap.put(moduleId2, "2");

        Set<SmoothModuleId> keys = this.gearingMap.listKey();

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

        Assertions.assertThatThrownBy(() -> this.gearingMap.put(moduleId, "123"))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> this.gearingMap.getKey("123"))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> this.gearingMap.getValue("123"))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> this.gearingMap.getValue(moduleId))
                .isInstanceOf(NullPointerException.class);
    }

}
