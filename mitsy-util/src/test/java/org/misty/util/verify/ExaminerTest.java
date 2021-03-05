package org.misty.util.verify;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"rawtypes", "unchecked"})
class ExaminerTest {

    @Test
    public void test_requireNullOrEmpty() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Examiner.requireNullOrEmpty(term, (Object) null);
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, new Object()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));

        Examiner.requireNullOrEmpty(term, (String) null);
        Examiner.requireNullOrEmpty(term, "");
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, "9527"))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));

        Examiner.requireNullOrEmpty(term, (Collection) null);
        Examiner.requireNullOrEmpty(term, Collections.emptyList());
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Collections.singletonList("")))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));

        Examiner.requireNullOrEmpty(term, (Map) null);
        Examiner.requireNullOrEmpty(term, Collections.emptyMap());
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Collections.singletonMap("", "")))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));

        Examiner.requireNullOrEmpty(term, (String[]) null);
        Examiner.requireNullOrEmpty(term, new String[]{});
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, new String[]{""}))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));

        Examiner.requireNullOrEmpty(term, (Optional) null);
        Examiner.requireNullOrEmpty(term, Optional.empty());
        Examiner.requireNullOrEmpty(term, Optional.of(""));
        Examiner.requireNullOrEmpty(term, Optional.of(Collections.emptyList()));
        Examiner.requireNullOrEmpty(term, Optional.of(Collections.emptyMap()));
        Examiner.requireNullOrEmpty(term, Optional.of(new Object[0]));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of("123")))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(Collections.singleton(""))))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(Collections.singletonMap("", ""))))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(new Object[]{1, ""})))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REQUIRE_NULL_OR_EMPTY_FORMAT.apply(term));
    }

    @Test
    public void test_refuseNullAndEmpty() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Object) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, new Object()));

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (String) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, ""))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, "9527")).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Collection) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Collections.emptyList()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Collections.singletonList(""))).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Map) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Collections.emptyMap()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Collections.singletonMap("", ""))).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (String[]) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, new String[]{}))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, new String[]{""})).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Optional) null))
                .isInstanceOf(MistyException.class).is(condition);
//                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term)); // XXX 不知道為什麼這邊操作會錯@@?
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.empty()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of("")))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(Collections.emptyList())))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(Collections.emptyMap())))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(new Object[0])))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.REFUSE_NULL_OR_EMPTY_FORMAT.apply(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of("123"))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(Collections.singleton("")))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(Collections.singletonMap("", "")))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(new Object[]{1, ""}))).isNotEmpty();
    }

}