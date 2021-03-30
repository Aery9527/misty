package org.misty.smooth.core.context.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.misty.ut.common.StringSplitter;
import org.misty.util.error.MistyException;
import org.misty.util.verify.ExaminerMessage;

import java.util.*;
import java.util.function.Consumer;

class SmoothCoreEnvironmentPresetTest {

    private SmoothCoreEnvironmentPreset environment = new SmoothCoreEnvironmentPreset();

    @BeforeEach
    void initialEnvironment() {
        this.environment = new SmoothCoreEnvironmentPreset();
    }

    // flag

    @ParameterizedTest
    @ValueSource(strings = {"e", " "})
    public void addFlag$normal(String flag) {
        this.environment.addFlag(flag);
        Assertions.assertThat(this.environment.containsFlag(flag)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void addFlag$error(String flag) {
        Assertions.assertThatThrownBy(() -> this.environment.addFlag(flag))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flag"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1", "e1,e2"})
    public void addFlags$normal(@ConvertWith(StringSplitter.class) String[] flags) {
        this.environment.addFlags(flags);
        Assertions.assertThat(this.environment.containsAllFlags(flags)).isTrue();

        initialEnvironment();
        this.environment.addFlags(Arrays.asList(flags));
        Assertions.assertThat(this.environment.containsAllFlags(flags)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void addFlags$error_null_empty(String[] flags) {
        Assertions.assertThatThrownBy(() -> this.environment.addFlags(flags))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flags"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void addFlags$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] flags) {
        String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "flags", Arrays.toString(flags));
        Assertions.assertThatThrownBy(() -> this.environment.addFlags(flags))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1", " "})
    public void containsFlag$normal(String flag) {
        this.environment.addFlag(flag);

        Assertions.assertThat(this.environment.containsFlag(flag)).isTrue();
        Assertions.assertThat(this.environment.containsFlag("9527")).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void containsFlag$error(String flag) {
        Assertions.assertThatThrownBy(() -> this.environment.containsFlag(flag))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flag"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void containsExactlyFlags$normal(@ConvertWith(StringSplitter.class) String[] flags) {
        this.environment.addFlags(flags);

        Assertions.assertThat(this.environment.containsExactlyFlags(flags)).isTrue();

        List<String> l = new ArrayList<>(Arrays.asList(flags));
        l.remove(0);
        Assertions.assertThat(this.environment.containsExactlyFlags(l)).isFalse();

        l.add("123");
        Assertions.assertThat(this.environment.containsExactlyFlags(l)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void containsExactlyFlags$error_null_empty(String[] flags) {
        Assertions.assertThatThrownBy(() -> this.environment.containsExactlyFlags(flags))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flags"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void containsExactlyFlags$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] flags) {
        Assertions.assertThat(this.environment.containsExactlyFlags(flags)).isFalse();

        List<String> l = new ArrayList<>();
        for (int i = 0; i < flags.length; i++) {
            l.add(i + "");
        }
        this.environment.addFlags(l);

        String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "flags", Arrays.toString(flags));
        Assertions.assertThatThrownBy(() -> this.environment.containsExactlyFlags(flags))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void containsAllFlags$normal(@ConvertWith(StringSplitter.class) String[] flags) {
        this.environment.addFlags(flags);

        Assertions.assertThat(this.environment.containsAllFlags(flags)).isTrue();

        List<String> l = new ArrayList<>(Arrays.asList(flags));
        l.remove(0);
        Assertions.assertThat(this.environment.containsAllFlags(l)).isTrue();

        l.add("123");
        Assertions.assertThat(this.environment.containsAllFlags(l)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void containsAllFlags$error_null_empty(String[] flags) {
        Assertions.assertThatThrownBy(() -> this.environment.containsAllFlags(flags))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flags"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void containsAllFlags$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] flags) {
        String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "flags", Arrays.toString(flags));
        Assertions.assertThatThrownBy(() -> this.environment.containsAllFlags(flags))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void containsAnyFlags$normal(@ConvertWith(StringSplitter.class) String[] flags) {
        this.environment.addFlags(flags);

        Assertions.assertThat(this.environment.containsAnyFlags(flags)).isTrue();

        List<String> l = new ArrayList<>(Arrays.asList(flags));
        l.remove(0);
        Assertions.assertThat(this.environment.containsAnyFlags(l)).isTrue();

        l.add("123");
        Assertions.assertThat(this.environment.containsAnyFlags(l)).isTrue();

        l = new ArrayList<>();
        l.add("9527");
        Assertions.assertThat(this.environment.containsAnyFlags(l)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void containsAnyFlags$error_null_empty(String[] flags) {
        Assertions.assertThatThrownBy(() -> this.environment.containsAnyFlags(flags))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flags"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void containsAnyFlags$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] flags) {
        String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "flags", Arrays.toString(flags));
        Assertions.assertThatThrownBy(() -> this.environment.containsAnyFlags(flags))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    // argument key

    // arguments value

}
