package org.misty.util.verify;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;

import java.lang.ref.Reference;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings({"rawtypes", "unchecked"})
class ExaminerTest {

    @Test
    public void test_requireNullOrEmpty() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Examiner.requireNullOrEmpty(term, (Object) null);
        AtomicReference<Object> arg1 = new AtomicReference<>(new Object());
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg1.get()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, arg1.get().toString()));

        Examiner.requireNullOrEmpty(term, (String) null);
        Examiner.requireNullOrEmpty(term, "");
        AtomicReference<String> arg2 = new AtomicReference<>("9527");
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg2.get()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, arg2.get()));

        Examiner.requireNullOrEmpty(term, (Collection) null);
        Examiner.requireNullOrEmpty(term, Collections.emptyList());
        AtomicReference<Collection> arg3 = new AtomicReference<>(Collections.singletonList(""));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg3.get()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, arg3.get().toString()));

        Examiner.requireNullOrEmpty(term, (Map) null);
        Examiner.requireNullOrEmpty(term, Collections.emptyMap());
        AtomicReference<Map> arg4 = new AtomicReference<>(Collections.singletonMap("", ""));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg4.get()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, arg4.get().toString()));

        Examiner.requireNullOrEmpty(term, (String[]) null);
        Examiner.requireNullOrEmpty(term, new String[]{});
        AtomicReference<String[]> arg5 = new AtomicReference<>(new String[]{""});
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, arg5.get()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, Arrays.toString(arg5.get())));

        Examiner.requireNullOrEmpty(term, (Optional) null);
        Examiner.requireNullOrEmpty(term, Optional.empty());
        Examiner.requireNullOrEmpty(term, Optional.of(""));
        Examiner.requireNullOrEmpty(term, Optional.of(Collections.emptyList()));
        Examiner.requireNullOrEmpty(term, Optional.of(Collections.emptyMap()));
        Examiner.requireNullOrEmpty(term, Optional.of(new Object[0]));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(arg2.get())))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, arg2.get()));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(arg3.get())))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, arg3.get().toString()));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(arg4.get())))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, arg4.get().toString()));
        Assertions.assertThatThrownBy(() -> Examiner.requireNullOrEmpty(term, Optional.of(arg5.get())))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireNullOrEmpty(term, Arrays.toString(arg5.get())));
    }

    @Test
    public void test_refuseNullAndEmpty() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Object) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, new Object()));

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (String) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, ""))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, "9527")).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Collection) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Collections.emptyList()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Collections.singletonList(""))).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Map) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Collections.emptyMap()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Collections.singletonMap("", ""))).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (String[]) null))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, new String[]{}))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, new String[]{""})).isNotEmpty();

        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, (Optional) null))
                .isInstanceOf(MistyException.class).is(condition);
//                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term)); // XXX 不知道為什麼這邊操作會錯@@?
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.empty()))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of("")))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(Collections.emptyList())))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(Collections.emptyMap())))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThatThrownBy(() -> Examiner.refuseNullAndEmpty(term, Optional.of(new Object[0])))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.refuseNullAndEmpty(term));
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of("123"))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(Collections.singleton("")))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(Collections.singletonMap("", "")))).isNotEmpty();
        Assertions.assertThat(Examiner.refuseNullAndEmpty(term, Optional.of(new Object[]{1, ""}))).isNotEmpty();
    }

    @Test
    public void test_requireInRange_short() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        short arg = 1;
        short _0 = 0;
        short _1 = 1;
        short _2 = 2;
        Assertions.assertThat(Examiner.requireInRange(term, arg, _0, _2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, _1, _2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, _1, _1)).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, _2, _2))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, _2, _2));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, _1, _0))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, _1, _0));
    }

    @Test
    public void test_requireInRange_int() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        int arg = 1;
        Assertions.assertThat(Examiner.requireInRange(term, arg, 0, 2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1, 2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1, 1)).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 2, 2))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 2, 2));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 1, 0))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 1, 0));
    }

    @Test
    public void test_requireInRange_long() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        long arg = 1;
        Assertions.assertThat(Examiner.requireInRange(term, arg, 0, 2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1, 2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1, 1)).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 2, 2))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 2, 2));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 1, 0))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 1, 0));
    }

    @Test
    public void test_requireInRange_float() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        float arg = 1.3f;
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1.2f, 1.4f)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1.3f, 1.4f)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1.3f, 1.3f)).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 1.4f, 1.4f))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 1.4f, 1.4f));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 1.3f, 1.2f))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 1.3f, 1.2f));
    }

    @Test
    public void test_requireInRange_double() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        double arg = 1.3d;
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1.2d, 1.4d)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1.3d, 1.4d)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 1.3d, 1.3d)).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 1.4d, 1.4d))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 1.4d, 1.4d));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 1.3d, 1.2d))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 1.3d, 1.2d));
    }

    @Test
    public void test_requireInRange_char() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        char arg = 'b';
        Assertions.assertThat(Examiner.requireInRange(term, arg, 'a', 'c')).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 'b', 'c')).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, 'b', 'b')).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 'c', 'c'))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 'c', 'c'));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, 'b', 'a'))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, 'b', 'a'));
    }

    @Test
    public void test_requireInRange_byte() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        byte arg = 1;
        byte _0 = 0;
        byte _1 = 1;
        byte _2 = 2;
        Assertions.assertThat(Examiner.requireInRange(term, arg, _0, _2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, _1, _2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, _1, _1)).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, _2, _2))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, _2, _2));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, _1, _0))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, _1, _0));
    }

    @Test
    public void test_requireInRange_Character() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Character arg = 'b';
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Character('a'), new Character('c'))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Character('b'), new Character('c'))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Character('b'), new Character('b'))).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Character('c'), new Character('c')))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Character('c'), new Character('c')));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Character('b'), new Character('a')))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Character('b'), new Character('a')));
    }

    @Test
    public void test_requireInRange_Short() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Short i = 1;
        Assertions.assertThat(Examiner.requireInRange(term, i, new Short((short) 0), new Short((short) 2))).isEqualTo(i);
        Assertions.assertThat(Examiner.requireInRange(term, i, new Short((short) 1), new Short((short) 2))).isEqualTo(i);
        Assertions.assertThat(Examiner.requireInRange(term, i, new Short((short) 1), new Short((short) 1))).isEqualTo(i);
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, i, new Short((short) 2), new Short((short) 2)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, i, new Short((short) 2), new Short((short) 2)));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, i, new Short((short) 1), new Short((short) 0)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, i, new Short((short) 1), new Short((short) 0)));
    }

    @Test
    public void test_requireInRange_Integer() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Integer arg = 1;
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Integer(0), new Integer(2))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Integer(1), new Integer(2))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Integer(1), new Integer(1))).isEqualTo(arg);
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Integer(2), new Integer(2)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Integer(2), new Integer(2)));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Integer(1), new Integer(0)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Integer(1), new Integer(0)));
    }

    @Test
    public void test_requireInRange_Long() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Long arg = 1L;
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Long(0), new Long(2))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Long(1), new Long(2))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Long(1), new Long(1))).isEqualTo(arg);
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Long(2), new Long(2)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Long(2), new Long(2)));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Long(1), new Long(0)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Long(1), new Long(0)));
    }

    @Test
    public void test_requireInRange_Float() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Float arg = 1.3f;
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Float(1.2f), new Float(1.4f))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Float(1.3f), new Float(1.4f))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Float(1.3f), new Float(1.3f))).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Float(1.4f), new Float(1.4f)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Float(1.4f), new Float(1.4f)));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Float(1.3f), new Float(1.2f)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Float(1.3f), new Float(1.2f)));
    }

    @Test
    public void test_requireInRange_Double() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Double arg = 1.3d;
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Double(1.2d), new Double(1.4d))).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, new Double(1.3d), new Double(1.4d))).isEqualTo(arg);
//        Assertions.assertThat(Examiner.requireInRange(term, arg, new Double(1.3f), new Double(1.3f))).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Double(1.4d), new Double(1.4d)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Double(1.4d), new Double(1.4d)));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, new Double(1.3d), new Double(1.2d)))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, new Double(1.3d), new Double(1.2d)));
    }

    @Test
    public void test_requireInRange_Byte() {
        String term = "kerker";
        Condition<Throwable> condition = new Condition<>(MistyError.ARGUMENT_ERROR::isSame,
                "MistyErrorDefinition must be MistyError." + MistyError.ARGUMENT_ERROR);

        Byte arg = 1;
        Byte _0 = 0;
        Byte _1 = 1;
        Byte _2 = 2;
        Assertions.assertThat(Examiner.requireInRange(term, arg, _0, _2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, _1, _2)).isEqualTo(arg);
        Assertions.assertThat(Examiner.requireInRange(term, arg, _1, _1)).isEqualTo(arg);

        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, _2, _2))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, _2, _2));
        Assertions.assertThatThrownBy(() -> Examiner.requireInRange(term, arg, _1, _0))
                .isInstanceOf(MistyException.class).is(condition)
                .hasMessage(ExaminerMessage.requireInRange(term, arg, _1, _0));
    }


}