package org.misty.smooth.manager.loader.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.misty.smooth.core.context.impl.SmoothCoreEnvironmentPreset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class SmoothLoaderArgumentTest {

    private SmoothLoaderArgument smoothLoaderArgument;

    @BeforeEach
    public void before() {
        this.smoothLoaderArgument = new SmoothLoaderArgument();
    }

    @Test
    public void putValue_kv() {
        String key = "k", value = "v";

        Assertions.assertThat(this.smoothLoaderArgument.putValue(key, value)).isTrue();
        Assertions.assertThat(this.smoothLoaderArgument.getValue(key)).isNotEmpty().contains(value);
        Assertions.assertThat(this.smoothLoaderArgument.putValue(key, value)).isFalse();
    }

    @Test
    public void putValue_map() {
        String k1 = "k1", v1 = "v1";
        String k2 = "k2", v2 = "v2";

        Map<String, String> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);

        this.smoothLoaderArgument.putValue(map);
        Assertions.assertThat(this.smoothLoaderArgument.getValue(k1)).isNotEmpty().contains(v1);
        Assertions.assertThat(this.smoothLoaderArgument.getValue(k2)).isNotEmpty().contains(v2);
    }

    @Test
    public void getValue() {
        String key = "k", value = "v";

        Assertions.assertThat(this.smoothLoaderArgument.getValue(key)).isEmpty();
        this.smoothLoaderArgument.putValue(key, value);
        Assertions.assertThat(this.smoothLoaderArgument.getValue(key)).isNotEmpty().contains(value);
    }

    @Test
    public void getValue_preset() {
        String key = "k", preset = "kerker";

        Assertions.assertThat(this.smoothLoaderArgument.getValue(key, preset)).isEqualTo(preset);
    }

    @Test
    public void getValue_converter() {
        String key = "k", value = "9527";
        int preset = 9487;

        Assertions.assertThat(this.smoothLoaderArgument.getValue(key, Integer::valueOf, () -> preset))
                .isEqualTo(preset);
        this.smoothLoaderArgument.putValue(key, value);
        Assertions.assertThat(this.smoothLoaderArgument.getValue(key, Integer::valueOf, () -> preset))
                .isEqualTo(Integer.valueOf(value));
    }

    @Test
    public void lock() {
        String key = "k", value = "9527";

        this.smoothLoaderArgument.putValue(key, value);
        this.smoothLoaderArgument.lock();

        Assertions.assertThatThrownBy(() -> this.smoothLoaderArgument.putValue("gg", "ff"))
                .isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> this.smoothLoaderArgument.get("gg").add("ff"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void mergeEnvironment() {
        String f1 = "f1", f2 = "f2";
        String k1 = "k1", v1 = "v1";
        String k2 = "k2", v2 = "v2";

        SmoothCoreEnvironmentPreset environment = new SmoothCoreEnvironmentPreset();
        environment.addFlags(f1, f2);
        environment.addArgument(k1, v1);
        environment.addArgument(k2, v2);

        this.smoothLoaderArgument.mergeEnvironment(environment);

        Assertions.assertThat(this.smoothLoaderArgument.getEnvironmentFlags()).isNotEmpty()
                .containsExactlyInAnyOrder(f1, f2);

        Assertions.assertThat(this.smoothLoaderArgument.get(k1)).isNotEmpty()
                .containsExactlyInAnyOrder(v1);
        Assertions.assertThat(this.smoothLoaderArgument.get(k2)).isNotEmpty()
                .containsExactlyInAnyOrder(v2);
    }

    @Test
    public void get() {
        Assertions.assertThat(this.smoothLoaderArgument.get("gg")).isNotNull().isEmpty();
    }

    @Test
    public void put() {
        String key = "k", v1 = "v1", v2 = "v2";

        this.smoothLoaderArgument.putValue(key, v1);

        Set<String> set = new HashSet<>();
        set.add(v1);

        Assertions.assertThat(this.smoothLoaderArgument.put(key, set)).isEmpty();
        Assertions.assertThat(this.smoothLoaderArgument.get(key)).isNotEmpty()
                .containsExactlyInAnyOrder(v1);

        set.add(v2);
        Assertions.assertThat(this.smoothLoaderArgument.put(key, set)).isNotEmpty()
                .containsExactlyInAnyOrder(v2);
        Assertions.assertThat(this.smoothLoaderArgument.get(key)).isNotEmpty()
                .containsExactlyInAnyOrder(v1, v2);
    }

    @Test
    public void putAll() {
        String k1 = "k1", v11 = "v11", v12 = "v12";
        String k2 = "k2", v21 = "v21", v22 = "v22";

        Map<String, Set<String>> map = new HashMap<>();

        Set<String> set1 = new HashSet<>();
        set1.add(v11);
        set1.add(v12);
        map.put(k1, set1);

        Set<String> set2 = new HashSet<>();
        set2.add(v21);
        set2.add(v22);
        map.put(k2, set2);

        this.smoothLoaderArgument.putAll(map);

        Assertions.assertThat(this.smoothLoaderArgument.get(k1)).isNotEmpty()
                .containsExactlyInAnyOrder(v11, v12);
        Assertions.assertThat(this.smoothLoaderArgument.get(k2)).isNotEmpty()
                .containsExactlyInAnyOrder(v21, v22);
    }

}
