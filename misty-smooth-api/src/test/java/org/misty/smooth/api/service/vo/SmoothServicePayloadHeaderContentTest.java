package org.misty.smooth.api.service.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.util.reflect.field.FieldExtractor;
import org.misty.util.reflect.field.FieldObjectGetter;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@ExtendWith(MockitoExtension.class)
class SmoothServicePayloadHeaderContentTest {

    @Mock
    private List<String> list;

    @BeforeEach
    void setUp() {
        Mockito.reset(this.list);
    }

    @Test
    void constructor() throws NoSuchFieldException {
        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent();
        FieldExtractor fieldExtractor = new FieldExtractor(content);
        FieldObjectGetter<List> getter = fieldExtractor.buildGetter("list", List.class);
        List<String> list = getter.get();
        Assertions.assertThat(list).isInstanceOf(ArrayList.class);
    }

    @Test
    void constructor_Collection() throws NoSuchFieldException {
        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(new HashSet<>());
        FieldExtractor fieldExtractor = new FieldExtractor(content);
        FieldObjectGetter<List> getter = fieldExtractor.buildGetter("list", List.class);
        List<String> list = getter.get();
        Assertions.assertThat(list).isInstanceOf(ArrayList.class);
    }

    @Test
    void constructor_initialCapacity() throws NoSuchFieldException {
        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(3);
        FieldExtractor fieldExtractor = new FieldExtractor(content);
        FieldObjectGetter<List> getter = fieldExtractor.buildGetter("list", List.class);
        ArrayList<String> list = (ArrayList<String>) getter.get();

        Object[] elementData = new FieldExtractor(list).buildGetter("elementData", Object[].class).get();
        Assertions.assertThat(elementData.length).isEqualTo(3);
    }

    @Test
    void size() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        int returned = 9527;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).size();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.size()).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void isEmpty() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).isEmpty();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.isEmpty()).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void contains() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).contains(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.contains("")).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void iterator() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        Iterator<String> i = Mockito.mock(Iterator.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return i;
        }).when(this.list).iterator();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.iterator()).isEqualTo(i);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void toArray() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        Object[] a = {};

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return a;
        }).when(this.list).toArray();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.toArray()).isEqualTo(a);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void toArray_a() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        Object[] a = {};

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return a;
        }).when(this.list).toArray(a);

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.toArray(a)).isEqualTo(a);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void add() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).add(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.add("")).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void remove() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).remove(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.remove("")).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void containsAll() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).containsAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.containsAll(Collections.emptyList())).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void addAll() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).addAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.addAll(Collections.emptyList())).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void addAll_index() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).addAll(Mockito.anyInt(), Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.addAll(0, Collections.emptyList())).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void removeAll() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).removeAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.removeAll(Collections.emptyList())).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void retainAll() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).retainAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.retainAll(Collections.emptyList())).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void clear() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return null;
        }).when(this.list).clear();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        content.clear();
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void get() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        String returned = "9527";

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).get(Mockito.anyInt());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.get(Mockito.anyInt())).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void set() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        String returned = "9527";

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).set(Mockito.anyInt(), Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.set(0, "123")).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void add_index() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).add(Mockito.anyInt(), Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        content.add(0, "");
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void remove_int() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        String returned = "9527";

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).remove(Mockito.anyInt());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.remove(0)).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void indexOf() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        int returned = 999;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).indexOf(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.indexOf("")).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void lastIndexOf() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        int returned = 999;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).lastIndexOf(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.lastIndexOf("")).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void listIterator() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        ListIterator<String> returned = Mockito.mock(ListIterator.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).listIterator();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.listIterator()).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void listIterator_index() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        ListIterator<String> returned = Mockito.mock(ListIterator.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).listIterator(Mockito.anyInt());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.listIterator(1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void subList() {
        AtomicBoolean checkPoint = new AtomicBoolean();
        List<String> returned = Mockito.mock(List.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).subList(Mockito.anyInt(), Mockito.anyInt());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.subList(1, 2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

}
