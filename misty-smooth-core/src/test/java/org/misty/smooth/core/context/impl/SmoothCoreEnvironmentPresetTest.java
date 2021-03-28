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

import java.util.Arrays;
import java.util.function.Consumer;

class SmoothCoreEnvironmentPresetTest {

    private SmoothCoreEnvironmentPreset environment = new SmoothCoreEnvironmentPreset();

    @BeforeEach
    void initialEnvironment() {
        this.environment = new SmoothCoreEnvironmentPreset();
    }

    // flag

    @ParameterizedTest
    @ValueSource(strings = {"a", " "})
    public void addFlag_normal(String flag) {
        this.environment.addFlag(flag);
        Assertions.assertThat(this.environment.containsFlag(flag)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void addFlag_error(String flag) {
        Assertions.assertThatThrownBy(() -> this.environment.addFlag(flag))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flag"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1", "e1,e2"})
    public void addFlags_normal(@ConvertWith(StringSplitter.class) String[] flags) {
        this.environment.addFlags(flags);
        Assertions.assertThat(this.environment.containsAllFlags(flags)).isTrue();

        initialEnvironment();
        this.environment.addFlags(Arrays.asList(flags));
        Assertions.assertThat(this.environment.containsAllFlags(flags)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"<empty>,e", "e,<empty>", "e,<null>", "<null>,e", "e1,<empty>,e2", "e1,<null>,e2"})
    public void addFlags_error(@ConvertWith(StringSplitter.class) String[] flags) {
        Consumer<String> test = (msg) -> {
            Assertions.assertThatThrownBy(() -> this.environment.addFlags(flags))
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(msg);
        };

        if (flags == null || flags.length == 0) {
            test.accept(ExaminerMessage.refuseNullAndEmpty("flags"));
        } else {
            String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "flags", Arrays.toString(flags));
            test.accept(msg);
        }
    }

    // argument key

    // arguments value

}
