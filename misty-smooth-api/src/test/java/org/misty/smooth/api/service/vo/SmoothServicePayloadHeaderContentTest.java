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
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SuppressWarnings({"unchecked", "ConstantConditions", "SuspiciousMethodCalls", "rawtypes"})
@ExtendWith(MockitoExtension.class)
class SmoothServicePayloadHeaderContentTest {

    @Mock
    private Set<String> set;

    @BeforeEach
    void setUp() {
        Mockito.reset(this.set);
    }

    @Test
    void constructor() throws NoSuchFieldException {
        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent();
        FieldExtractor fieldExtractor = new FieldExtractor(content);
        FieldObjectGetter<Set> getter = fieldExtractor.buildGetter("set", Set.class);
        Set<String> set = getter.get();
        Assertions.assertThat(set).isInstanceOf(HashSet.class);
    }

    @Test
    void constructor_true() throws NoSuchFieldException {
        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(true);
        FieldExtractor fieldExtractor = new FieldExtractor(content);
        FieldObjectGetter<Set> getter = fieldExtractor.buildGetter("set", Set.class);
        Set<String> set = getter.get();
        Assertions.assertThat(set).isInstanceOf(TreeSet.class);
    }

    @Test
    void testToString() throws NoSuchFieldException {
        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(true);
        FieldExtractor fieldExtractor = new FieldExtractor(content);
        FieldObjectGetter<Set> getter = fieldExtractor.buildGetter("set", Set.class);
        Set<String> set = getter.get();

        set.add("kerker");

        Assertions.assertThat(set.toString()).isEqualTo(content.toString());
    }

    @Test
    void size() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        int returned = 9527;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.set).size();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).isEmpty();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).contains(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).iterator();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).toArray();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).toArray(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).add(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).remove(Mockito.anyString());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).containsAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).addAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
        Assertions.assertThat(content.addAll(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void removeAll() {
        AtomicReference<Collection<String>> checkPoint1 = new AtomicReference<>();

        Collection<String> arg1 = new HashSet<>();

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.set).removeAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).retainAll(Mockito.anyCollection());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
        Assertions.assertThat(content.retainAll(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void clear() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return null;
        }).when(this.set).clear();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
        content.clear();
        Assertions.assertThat(checkPoint.get()).isTrue();
    }

    @Test
    public void spliterator() {
        AtomicBoolean checkPoint = new AtomicBoolean();

        Spliterator<String> returned = Mockito.mock(Spliterator.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint.set(true);
            return returned;
        }).when(this.set).spliterator();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).removeIf(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).stream();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).parallelStream();

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
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
        }).when(this.set).forEach(Mockito.any());

        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
        content.forEach(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

}
