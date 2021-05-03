package org.misty.smooth.core.context.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.ut.common.CrosserTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 * only test {@link SmoothEnvironment} method
 */
@ExtendWith(MockitoExtension.class)
class SmoothCoreEnvironmentCrosserTest {

    private static final ClassLoader CL = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());

    @Mock
    private SmoothCoreEnvironment environment;

    @BeforeEach
    void setUp() {
        Mockito.reset(this.environment);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void containsFlag() {
        String flag = "kerker";
        boolean returned = true;

        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }, this.environment).containsFlag(Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.containsFlag(flag)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(flag);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    void containsExactlyFlags() {
        Collection<String> flags = new ArrayList<>();
        boolean returned = true;

        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }, this.environment).containsExactlyFlags((Collection<String>) Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.containsExactlyFlags(flags)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(flags);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    void containsAllFlags() {
        Collection<String> flags = new ArrayList<>();
        boolean returned = true;

        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }, this.environment).containsAllFlags((Collection<String>) Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.containsAllFlags(flags)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(flags);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    void containsAnyFlags() {
        Collection<String> flags = new ArrayList<>();
        boolean returned = true;

        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }, this.environment).containsAnyFlags((Collection<String>) Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.containsAnyFlags(flags)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(flags);
    }

    @Test
    void getFlags() {
        Set<String> flags = new HashSet<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> flags, this.environment).getFlags();

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.getFlags()).isEqualTo(flags);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void containsKey() {
        String key = "9527";
        boolean returned = true;

        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }, this.environment).containsKey(Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.containsKey(key)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(key);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    void containsExactlyKeys() {
        Collection<String> keys = new ArrayList<>();
        boolean returned = true;

        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }, this.environment).containsExactlyKeys((Collection<String>) Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.containsExactlyKeys(keys)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(keys);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    void containsAllKeys() {
        Collection<String> keys = new ArrayList<>();
        boolean returned = true;

        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }, this.environment).containsAllKeys((Collection<String>) Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.containsAllKeys(keys)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(keys);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    void containsAnyKeys() {
        Collection<String> keys = new ArrayList<>();
        boolean returned = true;

        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }, this.environment).containsAnyKeys((Collection<String>) Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.containsAnyKeys(keys)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(keys);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void equalsValue() {
        String key = "9527";
        String value = "5566";
        boolean returned = true;

        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<String> checkPoint2 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }, this.environment).equalsValue(Mockito.any(), Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.equalsValue(key, value)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(key);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(value);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    void equalsAnyValues() {
        String key = "9527";
        Collection<String> values = new ArrayList<>();
        boolean returned = true;

        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<Collection<String>> checkPoint2 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }, this.environment).equalsAnyValues(Mockito.any(), (Collection<String>) Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.equalsAnyValues(key, values)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(key);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(values);
    }

    @SuppressWarnings("unchecked")
    @Test
    void getValue() {
        String key = "9527";
        Function<String, String> transformer = Mockito.mock(Function.class);
        String returned = "9487";

        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<Function<String, String>> checkPoint2 = new AtomicReference<>();


        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }, this.environment).getValue(Mockito.any(), (Function<String, String>) Mockito.any());

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.getValue(key, transformer)).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(key);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(transformer);
    }

    @Test
    void getKeys() {
        Set<String> returned = new HashSet<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> returned, this.environment).getKeys();

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.getKeys()).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    void getArguments() {
        Map<String, String> returned = new HashMap<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> returned, this.environment).getArguments();

        SmoothCoreEnvironmentCrosser crosser = new SmoothCoreEnvironmentCrosser(CL, this.environment);
        Assertions.assertThat(crosser.getArguments()).isEqualTo(returned);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

}
