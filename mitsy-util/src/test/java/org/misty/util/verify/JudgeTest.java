package org.misty.util.verify;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

class JudgeTest {

    @Test
    public void test_Object() {
        Assertions.assertThat(Judge.isNullOrEmpty((Object) null)).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) null)).isEqualTo(false);
    }

    @Test
    public void test_String() {
        Assertions.assertThat(Judge.isNullOrEmpty((String) null)).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((String) null)).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty("")).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty("")).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty("123")).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty("123")).isEqualTo(true);

        Assertions.assertThat(Judge.isNullOrEmpty((Object) "")).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) "")).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty((Object) "123")).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) "123")).isEqualTo(true);
    }

    @Test
    public void test_Collection() {
        Assertions.assertThat(Judge.isNullOrEmpty((Collection) null)).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Collection) null)).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Collections.emptyList())).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty(Collections.emptyList())).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Collections.singleton(""))).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty(Collections.singleton(""))).isEqualTo(true);

        Assertions.assertThat(Judge.isNullOrEmpty((Object) Collections.emptyList())).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) Collections.emptyList())).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty((Object) Collections.singleton(""))).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) Collections.singleton(""))).isEqualTo(true);
    }

    @Test
    public void test_Map() {
        Assertions.assertThat(Judge.isNullOrEmpty((Map) null)).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Map) null)).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Collections.emptyMap())).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty(Collections.emptyMap())).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Collections.singletonMap("", ""))).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty(Collections.singletonMap("", ""))).isEqualTo(true);

        Assertions.assertThat(Judge.isNullOrEmpty((Object) Collections.emptyMap())).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) Collections.emptyMap())).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty((Object) Collections.singletonMap("", ""))).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) Collections.singletonMap("", ""))).isEqualTo(true);
    }

    @Test
    public void test_Array() {
        Assertions.assertThat(Judge.isNullOrEmpty((Object[]) null)).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Object[]) null)).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(new Object[]{})).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty(new Object[]{})).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(new Object[]{1})).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty(new Object[]{1})).isEqualTo(true);

        Assertions.assertThat(Judge.isNullOrEmpty((Object) new Object[]{})).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) new Object[]{})).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty((Object) new Object[]{1})).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty((Object) new Object[]{1})).isEqualTo(true);
    }

    @Test
    public void test_Optional() {
        Assertions.assertThat(Judge.isNullOrEmpty((Optional) null)).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty((Optional) null)).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Optional.empty())).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.empty())).isEqualTo(false);

        Assertions.assertThat(Judge.isNullOrEmpty(Optional.of(""))).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.of(""))).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Optional.of("1"))).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.of("1"))).isEqualTo(true);

        Assertions.assertThat(Judge.isNullOrEmpty(Optional.of(Collections.emptyList()))).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.of(Collections.emptyList()))).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Optional.of(Collections.singleton("")))).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.of(Collections.singleton("")))).isEqualTo(true);

        Assertions.assertThat(Judge.isNullOrEmpty(Optional.of(Collections.emptyMap()))).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.of(Collections.emptyMap()))).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Optional.of(Collections.singletonMap("", "")))).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.of(Collections.singletonMap("", "")))).isEqualTo(true);

        Assertions.assertThat(Judge.isNullOrEmpty(Optional.of(new Object[]{}))).isEqualTo(true);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.of(new Object[]{}))).isEqualTo(false);
        Assertions.assertThat(Judge.isNullOrEmpty(Optional.of(new Object[]{1}))).isEqualTo(false);
        Assertions.assertThat(Judge.notNullAndEmpty(Optional.of(new Object[]{1}))).isEqualTo(true);
    }

}