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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@SuppressWarnings({"unchecked", "ComparatorMethodParameterNotUsed", "ConstantConditions", "SuspiciousMethodCalls", "rawtypes"})
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
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).contains(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.contains(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void iterator() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        Iterator<String> returned = Mockito.mock(Iterator.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).iterator();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.iterator()).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void toArray() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        Object[] returned = {};

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).toArray();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.toArray()).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    void toArray_a() {
        AtomicReference<Object[]> checkPoint1 = new AtomicReference<>();

        Object[] a = {};

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return a;
        }).when(this.list).toArray(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.toArray(a)).isEqualTo(a);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(a);
    }

    @Test
    void add() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).add(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.add(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void remove() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).remove(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.remove(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void containsAll() {
        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        Collection<String> arg1 = new HashSet<>();

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).containsAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.containsAll(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void addAll() {
        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        Collection<String> arg1 = new HashSet<>();

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).addAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.addAll(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void addAll_index() {
        AtomicInteger checkPoint1 = new AtomicInteger();
        AtomicReference<Collection<String>> checkPoint2 = new AtomicReference<>();

        int arg1 = 9527;
        Collection<String> arg2 = new HashSet<>();

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.list).addAll(Mockito.anyInt(), Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.addAll(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void removeAll() {
        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        Collection<String> arg1 = new HashSet<>();

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).removeAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.removeAll(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void retainAll() {
        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        Collection<String> arg1 = new HashSet<>();

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).retainAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.retainAll(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
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
        AtomicInteger checkPoint1 = new AtomicInteger();

        int arg1 = 9527;

        String returned = "9527";

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).get(Mockito.anyInt());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.get(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void set() {
        AtomicInteger checkPoint1 = new AtomicInteger();
        AtomicReference<String> checkPoint2 = new AtomicReference<>();

        int arg1 = 9527;
        String arg2 = "kerker";

        String returned = "9527";

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.list).set(Mockito.anyInt(), Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.set(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void add_index() {
        AtomicInteger checkPoint1 = new AtomicInteger();
        AtomicReference<String> checkPoint2 = new AtomicReference<>();

        int arg1 = 9527;
        String arg2 = "kerker";

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.list).add(Mockito.anyInt(), Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        content.add(arg1, arg2);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void remove_int() {
        AtomicInteger checkPoint1 = new AtomicInteger();

        int arg1 = 9527;

        String returned = "9527";

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).remove(Mockito.anyInt());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.remove(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void indexOf() {
        AtomicReference<Object> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        int returned = 999;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).indexOf(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.indexOf(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void lastIndexOf() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        int returned = 999;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).lastIndexOf(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.lastIndexOf(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
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
        AtomicInteger checkPoint1 = new AtomicInteger();

        int arg1 = 9527;

        ListIterator<String> returned = Mockito.mock(ListIterator.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).listIterator(Mockito.anyInt());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.listIterator(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void subList() {
        AtomicInteger checkPoint1 = new AtomicInteger();
        AtomicInteger checkPoint2 = new AtomicInteger();

        int arg1 = 9527;
        int arg2 = 5566;

        List<String> returned = Mockito.mock(List.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.list).subList(Mockito.anyInt(), Mockito.anyInt());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.subList(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    public void replaceAll() {
        AtomicReference<UnaryOperator<String>> checkPoint1 = new AtomicReference<>();

        UnaryOperator<String> arg1 = (e) -> e;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.list).replaceAll(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        content.replaceAll(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    public void sort() {
        AtomicReference<Comparator<String>> checkPoint1 = new AtomicReference<>();

        Comparator<String> arg1 = (e1, e2) -> 1;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.list).sort(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        content.sort(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    public void spliterator() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        Spliterator<String> returned = Mockito.mock(Spliterator.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).spliterator();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.spliterator()).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    public void removeIf() {
        AtomicReference<Predicate<String>> checkPoint1 = new AtomicReference<>();

        Predicate<String> arg1 = (e) -> true;

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).removeIf(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.removeIf(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    public void stream() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        Stream<String> returned = Mockito.mock(Stream.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).stream();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.stream()).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    public void parallelStream() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        Stream<String> returned = Mockito.mock(Stream.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.list).parallelStream();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        Assertions.assertThat(content.parallelStream()).isEqualTo(returned);
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    public void forEach() {
        AtomicReference<Consumer<String>> checkPoint1 = new AtomicReference<>();

        Consumer<String> arg1 = (e) -> {
        };

        Stream<String> returned = Mockito.mock(Stream.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.list).forEach(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.list);
        content.forEach(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

}
