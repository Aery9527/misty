package org.misty.smooth.core.context.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.util.error.MistyException;
import org.misty.util.verify.ExaminerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class SmoothCoreEnvironmentPresetTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test_flag() {
        String f1 = "Aery";
        String f2 = "aery"; // sensitive
        String f3 = "Rion";
        String f4 = "kerker";

        SmoothCoreEnvironmentPreset environment = new SmoothCoreEnvironmentPreset();
        Assertions.assertThat(environment.getFlags()).isEmpty();

        environment.addFlag(f1);
        Assertions.assertThat(environment.containsFlag(f1)).isEqualTo(true);
        Assertions.assertThat(environment.containsAllFlags(f1)).isEqualTo(true);
        Assertions.assertThat(environment.containsAllFlags(f1, f2)).isEqualTo(false);
        Assertions.assertThat(environment.containsAnyFlags(f1, f2)).isEqualTo(true);
        Assertions.assertThat(environment.containsAnyFlags(f2)).isEqualTo(false);
        Assertions.assertThat(environment.getFlags()).containsAll(Collections.singletonList(f1));

        environment.addFlags(f2, f3);
        Assertions.assertThat(environment.containsFlag(f1)).isEqualTo(true);
        Assertions.assertThat(environment.containsAllFlags(f1, f2, f3)).isEqualTo(true);
        Assertions.assertThat(environment.containsAllFlags(f1, f2, f3, f4)).isEqualTo(false);
        Assertions.assertThat(environment.containsAnyFlags(f1, f2, f3, f4)).isEqualTo(true);
        Assertions.assertThat(environment.containsAnyFlags(f4)).isEqualTo(false);
        Assertions.assertThat(environment.getFlags()).containsAll(Arrays.asList(f1, f2, f3));

        // test add exception
        Consumer<Runnable> handleAddFlagError = (runnable) -> {
            Assertions.assertThatThrownBy(runnable::run)
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flag"));
        };
        handleAddFlagError.accept(() -> environment.addFlag(null));
        handleAddFlagError.accept(() -> environment.addFlag(""));

        Consumer<Runnable> handleAddFlagsErrorWithCollection = (runnable) -> {
            Assertions.assertThatThrownBy(runnable::run)
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flags"));
        };
        handleAddFlagsErrorWithCollection.accept(() -> environment.addFlags(Collections.emptyList()));
        handleAddFlagsErrorWithCollection.accept(() -> environment.addFlags((Collection<String>) null));

        BiConsumer<String[], Consumer<String[]>> handleAddFlagsErrorWithArray = (args, consumer) -> {
            String format = SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT;
            Assertions.assertThatThrownBy(() -> consumer.accept(args))
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(String.format(format, "flags", Arrays.asList(args)));
        };
        handleAddFlagsErrorWithArray.accept(new String[]{"aaa", ""}, environment::addFlags);
        handleAddFlagsErrorWithArray.accept(new String[]{"aaa", null}, environment::addFlags);

        // test contains exception
        Consumer<Runnable> handleNullOrEmptyError = (runnable) -> {
            Assertions.assertThatThrownBy(runnable::run)
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flag"));
        };
        handleNullOrEmptyError.accept(() -> environment.containsFlag(null));
        handleNullOrEmptyError.accept(() -> environment.containsFlag(""));

        BiConsumer<String[], Consumer<String[]>> handleNullOrEmptyErrorWithArray = (args, consumer) -> {
            Assertions.assertThatThrownBy(() -> consumer.accept(args))
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flags"));
        };
        handleNullOrEmptyErrorWithArray.accept(null, environment::containsAllFlags);
        handleNullOrEmptyErrorWithArray.accept(new String[0], environment::containsAllFlags);
        handleNullOrEmptyErrorWithArray.accept(null, environment::containsAnyFlags);
        handleNullOrEmptyErrorWithArray.accept(new String[0], environment::containsAnyFlags);

        BiConsumer<Collection<String>, Consumer<Collection<String>>> handleNullOrEmptyErrorWitCollection = (args, consumer) -> {
            Assertions.assertThatThrownBy(() -> consumer.accept(args))
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("flags"));
        };
        handleNullOrEmptyErrorWitCollection.accept(null, environment::containsAllFlags);
        handleNullOrEmptyErrorWitCollection.accept(Collections.emptyList(), environment::containsAllFlags);
        handleNullOrEmptyErrorWitCollection.accept(null, environment::containsAnyFlags);
        handleNullOrEmptyErrorWitCollection.accept(Collections.emptyList(), environment::containsAnyFlags);

        BiConsumer<String[], Consumer<String[]>> handleElementNullOrEmptyErrorWithArray = (args, consumer) -> {
            String format = SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT;
            Assertions.assertThatThrownBy(() -> consumer.accept(args))
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(String.format(format, "flags", Arrays.asList(args)));
        };
        handleElementNullOrEmptyErrorWithArray.accept(new String[]{"123", null}, environment::containsAllFlags);
        handleElementNullOrEmptyErrorWithArray.accept(new String[]{"123", ""}, environment::containsAllFlags);
        handleElementNullOrEmptyErrorWithArray.accept(new String[]{"123", null}, environment::containsAnyFlags);
        handleElementNullOrEmptyErrorWithArray.accept(new String[]{"123", ""}, environment::containsAnyFlags);

        BiConsumer<Collection<String>, Consumer<Collection<String>>> handleElementNullOrEmptyErrorWitCollection = (args, consumer) -> {
            String format = SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT;
            Assertions.assertThatThrownBy(() -> consumer.accept(args))
                    .isInstanceOf(MistyException.class)
                    .hasMessageContaining(String.format(format, "flags", args));
        };
        handleElementNullOrEmptyErrorWitCollection.accept(Arrays.asList("123", null), environment::containsAllFlags);
        handleElementNullOrEmptyErrorWitCollection.accept(Arrays.asList("123", ""), environment::containsAllFlags);
        handleElementNullOrEmptyErrorWitCollection.accept(Arrays.asList("123", null), environment::containsAnyFlags);
        handleElementNullOrEmptyErrorWitCollection.accept(Arrays.asList("123", ""), environment::containsAnyFlags);
    }

    @Test
    public void test_argument_key() {
        String k1 = "Aery";
        String k2 = "aery"; // sensitive
        String k3 = "Rion";
        String k4 = "kerker";

        SmoothCoreEnvironmentPreset environment = new SmoothCoreEnvironmentPreset();
        Assertions.assertThat(environment.getKeys()).isEmpty();

        environment.addArgument(k1, "123");
        Assertions.assertThat(environment.containsKey(k1)).isEqualTo(true);
        Assertions.assertThat(environment.containsAllKeys(k1)).isEqualTo(true);
        Assertions.assertThat(environment.containsAllKeys(k1, k2)).isEqualTo(false);
        Assertions.assertThat(environment.containsAnyKeys(k1, k2)).isEqualTo(true);
        Assertions.assertThat(environment.containsAnyKeys(k2)).isEqualTo(false);
        Assertions.assertThat(environment.getKeys()).containsAll(Collections.singletonList(k1));

        environment.addArgument(k2, "123");
        environment.addArgument(k3, "123");
        Assertions.assertThat(environment.containsKey(k1)).isEqualTo(true);
        Assertions.assertThat(environment.containsAllKeys(k1, k2, k3)).isEqualTo(true);
        Assertions.assertThat(environment.containsAllKeys(k1, k2, k3, k4)).isEqualTo(false);
        Assertions.assertThat(environment.containsAnyKeys(k1, k2, k3, k4)).isEqualTo(true);
        Assertions.assertThat(environment.containsAnyKeys(k4)).isEqualTo(false);
        Assertions.assertThat(environment.getKeys()).containsAll(Arrays.asList(k1, k2, k3));


    }


}