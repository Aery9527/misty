package org.misty.smooth.core.context.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.misty.ut.common.StringSplitter;
import org.misty.util.error.MistyException;
import org.misty.util.verify.ExaminerMessage;

import java.util.*;

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
    @ValueSource(strings = {"e1", "e1,e2"})
    public void containsExactlyFlags$normal(@ConvertWith(StringSplitter.class) String[] flags) {
        Assertions.assertThat(this.environment.containsExactlyFlags(flags)).isFalse();

        this.environment.addFlags(flags);

        Assertions.assertThat(this.environment.containsExactlyFlags(flags)).isTrue();

        List<String> l = new ArrayList<>(Arrays.asList(flags));
        l.add("123");
        Assertions.assertThat(this.environment.containsExactlyFlags(l)).isFalse();

        l.remove(0);
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

    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void getFlags(@ConvertWith(StringSplitter.class) String[] flags) {
        this.environment.addFlags(flags);
        Assertions.assertThat(this.environment.getFlags()).containsExactlyInAnyOrder(flags);
    }

    // argument key

    @ParameterizedTest
    @ValueSource(strings = {"k", " "})
    public void addArgument$key$normal(String key) {
        Assertions.assertThat(this.environment.containsKey(key)).isFalse();
        this.environment.addArgument(key, "9527");
        Assertions.assertThat(this.environment.containsKey(key)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void addArgument$key$error(String key) {
        Assertions.assertThatThrownBy(() -> this.environment.addArgument(key, "9527"))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"k", " "})
    public void addArguments$key$normal(String key) {
        Map<String, String> map = new HashMap<>();
        map.put(key, "9527");

        Assertions.assertThat(this.environment.containsKey(key)).isFalse();
        this.environment.addArguments(map);
        Assertions.assertThat(this.environment.containsKey(key)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void addArguments$key$error(String key) {
        String value = "9527";

        Map<String, String> map = new HashMap<>();
        map.put(key, value);

        String msg = String.format(SmoothCoreEnvironmentPreset.ADD_ARGUMENTS_ERROR_THROW_ACTION_FORMAT, key, value);
        Assertions.assertThatThrownBy(() -> this.environment.addArguments(map))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"k", " "})
    public void containsKey$normal(String key) {
        Assertions.assertThat(this.environment.containsKey(key)).isFalse();
        this.environment.addArgument(key, "9527");
        Assertions.assertThat(this.environment.containsKey(key)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void containsKey$error(String key) {
        Assertions.assertThatThrownBy(() -> this.environment.containsKey(key))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("key"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"k1", "k1,k2"})
    public void containsExactlyKeys$normal(@ConvertWith(StringSplitter.class) String[] keys) {
        Assertions.assertThat(this.environment.containsExactlyKeys(keys)).isFalse();

        for (String key : keys) {
            this.environment.addArgument(key, "9527");
        }

        Assertions.assertThat(this.environment.containsExactlyKeys(keys)).isTrue();

        List<String> l = new ArrayList<>(Arrays.asList(keys));
        l.add("123");
        Assertions.assertThat(this.environment.containsExactlyKeys(l)).isFalse();

        l.remove(0);
        Assertions.assertThat(this.environment.containsExactlyKeys(l)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void containsExactlyKey$error_null_empty(String[] keys) {
        Assertions.assertThatThrownBy(() -> this.environment.containsExactlyKeys(keys))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("keys"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void containsExactlyKeys$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] keys) {
        Assertions.assertThat(this.environment.containsExactlyKeys(keys)).isFalse();

        for (int i = 0; i < keys.length; i++) {
            String k = i + "";
            this.environment.addArgument(k, "9527");
        }

        String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "keys", Arrays.toString(keys));
        Assertions.assertThatThrownBy(() -> this.environment.containsExactlyKeys(keys))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void containsAllKeys$normal(@ConvertWith(StringSplitter.class) String[] keys) {
        for (String key : keys) {
            this.environment.addArgument(key, "9527");
        }

        Assertions.assertThat(this.environment.containsAllKeys(keys)).isTrue();

        List<String> l = new ArrayList<>(Arrays.asList(keys));
        l.remove(0);
        Assertions.assertThat(this.environment.containsAllKeys(l)).isTrue();

        l.add("123");
        Assertions.assertThat(this.environment.containsAllKeys(l)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void containsAllKeys$error_null_empty(String[] keys) {
        Assertions.assertThatThrownBy(() -> this.environment.containsAllKeys(keys))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("keys"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void containsAllKeys$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] keys) {
        String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "keys", Arrays.toString(keys));
        Assertions.assertThatThrownBy(() -> this.environment.containsAllKeys(keys))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void containsAnyKeys$normal(@ConvertWith(StringSplitter.class) String[] keys) {
        for (String key : keys) {
            this.environment.addArgument(key, "9527");
        }

        Assertions.assertThat(this.environment.containsAnyKeys(keys)).isTrue();

        List<String> l = new ArrayList<>(Arrays.asList(keys));
        l.remove(0);
        Assertions.assertThat(this.environment.containsAnyKeys(l)).isTrue();

        l.add("123");
        Assertions.assertThat(this.environment.containsAnyKeys(l)).isTrue();

        l = new ArrayList<>();
        l.add("9527");
        Assertions.assertThat(this.environment.containsAnyKeys(l)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void containsAnyFKeys$error_null_empty(String[] keys) {
        Assertions.assertThatThrownBy(() -> this.environment.containsAnyKeys(keys))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("keys"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void containsAnyKeys$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] keys) {
        String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "keys", Arrays.toString(keys));
        Assertions.assertThatThrownBy(() -> this.environment.containsAnyKeys(keys))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void getKeys(@ConvertWith(StringSplitter.class) String[] keys) {
        for (String key : keys) {
            this.environment.addArgument(key, "9527");
        }
        Assertions.assertThat(this.environment.getKeys()).containsExactlyInAnyOrder(keys);
    }

    // arguments value

    @ParameterizedTest
    @ValueSource(strings = {"v", " "})
    public void addArgument$value$normal(String value) {
        String key = "key";
        Assertions.assertThat(this.environment.equalsValue(key, value)).isFalse();
        this.environment.addArgument(key, value);
        Assertions.assertThat(this.environment.equalsValue(key, value)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void addArgument$value$error(String value) {
        Assertions.assertThatThrownBy(() -> this.environment.addArgument("key", value))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"v1", " "})
    public void addArguments$value$normal(String value) {
        String key = "key";

        Map<String, String> map = new HashMap<>();
        map.put(key, value);

        Assertions.assertThat(this.environment.equalsValue(key, value)).isFalse();
        this.environment.addArguments(map);
        Assertions.assertThat(this.environment.equalsValue(key, value)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void addArguments$value$error_null_empty(String value) {
        String key = "key";

        Map<String, String> map = new HashMap<>();
        map.put(key, value);

        String msg = String.format(SmoothCoreEnvironmentPreset.ADD_ARGUMENTS_ERROR_THROW_ACTION_FORMAT, key, value);
        Assertions.assertThatThrownBy(() -> this.environment.addArguments(map))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void addArguments$value$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] values) {
        String key = null;
        String value = null;

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String k = i + "";
            String v = values[i];
            map.put(k, v);
            if (v == null || v.isEmpty()) {
                key = k;
                value = v;
            }
        }

        String msg = String.format(SmoothCoreEnvironmentPreset.ADD_ARGUMENTS_ERROR_THROW_ACTION_FORMAT, key, value);
        Assertions.assertThatThrownBy(() -> this.environment.addArguments(map))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1", " "})
    public void equalsValue$normal(String value) {
        String key = "key";
        this.environment.addArgument(key, value);

        Assertions.assertThat(this.environment.equalsValue(key, value)).isTrue();
        Assertions.assertThat(this.environment.equalsValue(key, "9527")).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void equalsValue$error(String value) {
        Assertions.assertThatThrownBy(() -> this.environment.equalsValue("key", value))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("value"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void equalsAnyValues$normal(@ConvertWith(StringSplitter.class) String[] values) {
        String key = "key";
        String value = values[0];

        this.environment.addArgument(key, value);

        Assertions.assertThat(this.environment.equalsAnyValues(key, values)).isTrue();

        List<String> l = new ArrayList<>(Arrays.asList(values));
        l.remove(0);
        Assertions.assertThat(this.environment.equalsAnyValues(key, l)).isFalse();

        l.add(values[values.length - 1]);
        Assertions.assertThat(this.environment.equalsAnyValues(key, values)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void equalsAnyValues$error_null_empty(String[] values) {
        Assertions.assertThatThrownBy(() -> this.environment.equalsAnyValues("key", values))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(ExaminerMessage.refuseNullAndEmpty("values"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"'',e", "e,''", "e,'null'", "'null',e", "e1,'',e2", "e1,'null',e2"})
    public void equalsAnyValues$error_element_null_empty(@ConvertWith(StringSplitter.class) String[] values) {
        String msg = String.format(SmoothCoreEnvironmentPreset.ELEMENT_ERROR_THROW_ACTION_FORMAT, "values", Arrays.toString(values));
        Assertions.assertThatThrownBy(() -> this.environment.equalsAnyValues("key", values))
                .isInstanceOf(MistyException.class)
                .hasMessageContaining(msg);
    }

    @SuppressWarnings("unchecked")
    @ParameterizedTest
    @ValueSource(strings = {"e1,e2", "e1,e2,e3"})
    public void getArguments(@ConvertWith(StringSplitter.class) String[] values) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String k = i + "";
            String v = values[i];
            map.put(k, v);
            this.environment.addArgument(k, v);
        }
        Assertions.assertThat(this.environment.getArguments()).contains(map.entrySet().toArray(new Map.Entry[values.length]));
    }

    // parseArgument

    @Test
    public void parseArgument$normal() {
        String flag1 = "f1";
        String flag2 = "f2";
        String k1 = "k1";
        String k2 = "k2";
        String v1 = "v1";
        String v2 = "v2";
        String u1 = "aaa";
        String u2 = "-123";

        String[] args = {
                "--" + flag1,
                "--" + flag2,
                "-" + k1 + "=" + v1,
                "-" + k2 + "=" + v2,
                u1, u2
        };

        Set<String> unrecognized = this.environment.parseArgument(args);

        Assertions.assertThat(unrecognized).containsExactlyInAnyOrder(u1, u2);
        Assertions.assertThat(this.environment.containsExactlyFlags(flag1, flag2)).isTrue();
        Assertions.assertThat(this.environment.containsExactlyKeys(k1, k2)).isTrue();
        Assertions.assertThat(this.environment.equalsValue(k1, v1)).isTrue();
        Assertions.assertThat(this.environment.equalsValue(k2, v2)).isTrue();
    }

}
