package org.misty.smooth.api.vo;

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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class SmoothUnscalableMapTest {

    @Mock
    private Map<String, SmoothUnscalableSet> map;

    @BeforeEach
    void setUp() {
        Mockito.reset(this.map);
    }

    @Test
    void constructor() throws NoSuchFieldException {
        SmoothUnscalableMap header = new SmoothUnscalableMap();
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothUnscalableSet> map = getter.get();
        Assertions.assertThat(map).isInstanceOf(HashMap.class);
    }

    @Test
    void constructor_true() throws NoSuchFieldException {
        SmoothUnscalableMap header = new SmoothUnscalableMap(true);
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothUnscalableSet> map = getter.get();
        Assertions.assertThat(map).isInstanceOf(TreeMap.class);
    }

    @Test
    void testToString() throws NoSuchFieldException {
        SmoothUnscalableMap header = new SmoothUnscalableMap();
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothUnscalableSet> map = getter.get();

        map.put("kerker", new SmoothUnscalableSet());

        Assertions.assertThat(map.toString()).isEqualTo(header.toString());
    }

    @Test
    void getOrPutDefault_key() throws NoSuchFieldException {
        SmoothUnscalableMap header = new SmoothUnscalableMap(true);
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothUnscalableSet> map = getter.get();

        String key = "kerker";
        SmoothUnscalableSet content = header.getOrPutDefault(key);

        Set<String> set = new FieldExtractor(content).buildGetter("set", Set.class).get();
        Assertions.assertThat(set).isInstanceOf(HashSet.class);
        Assertions.assertThat(map).containsEntry(key, content);
    }

    @Test
    void getOrPutDefault_sequential() throws NoSuchFieldException {
        SmoothUnscalableMap header = new SmoothUnscalableMap(true);
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothUnscalableSet> map = getter.get();

        String key = "kerker";
        SmoothUnscalableSet content = header.getOrPutDefault(key, true);

        Set<String> set = new FieldExtractor(content).buildGetter("set", Set.class).get();
        Assertions.assertThat(set).isInstanceOf(TreeSet.class);
        Assertions.assertThat(map).containsEntry(key, content);
    }

    @Test
    void size() {
        AtomicBoolean checkPoint1 = new AtomicBoolean();

        int returned = 9527;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(true);
            return returned;
        }).when(this.map).size();

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.size()).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void isEmpty() {
        AtomicBoolean checkPoint1 = new AtomicBoolean();

        boolean returned = false;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(true);
            return returned;
        }).when(this.map).isEmpty();

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.isEmpty()).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void containsKey() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        boolean returned = true;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.map).containsKey(Mockito.anyString());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.containsKey(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void containsValue() {
        AtomicReference<SmoothUnscalableSet> checkPoint1 = new AtomicReference<>();

        SmoothUnscalableSet arg1 = new SmoothUnscalableSet();

        boolean returned = true;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.map).containsValue(Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.containsValue(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void get() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.map).get(Mockito.anyString());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.get(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void put() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothUnscalableSet> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothUnscalableSet arg2 = new SmoothUnscalableSet();

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).put(Mockito.anyString(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.put(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void remove() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.map).remove(Mockito.anyString());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.remove(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void putAll() {
        AtomicReference<Map<String, SmoothUnscalableSet>> checkPoint1 = new AtomicReference<>();

        Map<String, SmoothUnscalableSet> arg1 = new HashMap<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.map).putAll(Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        header.putAll(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void clear() {
        AtomicBoolean checkPoint1 = new AtomicBoolean();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(true);
            return null;
        }).when(this.map).clear();

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        header.clear();
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void keySet() {
        AtomicBoolean checkPoint1 = new AtomicBoolean();

        Set<String> returned = new HashSet<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(true);
            return returned;
        }).when(this.map).keySet();

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.keySet()).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void values() {
        AtomicBoolean checkPoint1 = new AtomicBoolean();

        Collection<SmoothUnscalableSet> returned = new ArrayList<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(true);
            return returned;
        }).when(this.map).values();

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.values()).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void entrySet() {
        AtomicBoolean checkPoint1 = new AtomicBoolean();

        Set<Map.Entry<String, SmoothUnscalableSet>> returned = new HashSet<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(true);
            return returned;
        }).when(this.map).entrySet();

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.entrySet()).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void getOrDefault() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothUnscalableSet> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothUnscalableSet arg2 = new SmoothUnscalableSet();

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).getOrDefault(Mockito.anyString(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.getOrDefault(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void forEach() {
        AtomicReference<BiConsumer<String, SmoothUnscalableSet>> checkPoint1 = new AtomicReference<>();

        BiConsumer<String, SmoothUnscalableSet> arg1 = Mockito.mock(BiConsumer.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.map).forEach(Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        header.forEach(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void replaceAll() {
        AtomicReference<BiFunction<String, SmoothUnscalableSet, SmoothUnscalableSet>> checkPoint1 = new AtomicReference<>();

        BiFunction<String, SmoothUnscalableSet, SmoothUnscalableSet> arg1 = Mockito.mock(BiFunction.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.map).replaceAll(Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        header.replaceAll(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void putIfAbsent() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothUnscalableSet> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothUnscalableSet arg2 = new SmoothUnscalableSet();

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).putIfAbsent(Mockito.anyString(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.putIfAbsent(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void remove_key_value() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothUnscalableSet> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothUnscalableSet arg2 = new SmoothUnscalableSet();

        boolean returned = true;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).remove(Mockito.anyString(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.remove(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void replace_key_oldValue_newValue() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothUnscalableSet> checkPoint2 = new AtomicReference<>();
        AtomicReference<SmoothUnscalableSet> checkPoint3 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothUnscalableSet arg2 = new SmoothUnscalableSet();
        SmoothUnscalableSet arg3 = new SmoothUnscalableSet();

        boolean returned = true;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            checkPoint3.set(invocationOnMock.getArgument(2));
            return returned;
        }).when(this.map).replace(Mockito.anyString(), Mockito.any(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.replace(arg1, arg2, arg3)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
        Assertions.assertThat(checkPoint3.get()).isEqualTo(arg3);
    }

    @Test
    void replace_key_Value() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothUnscalableSet> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothUnscalableSet arg2 = new SmoothUnscalableSet();

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).replace(Mockito.anyString(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.replace(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void computeIfAbsent() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<Function<String, SmoothUnscalableSet>> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        Function<String, SmoothUnscalableSet> arg2 = Mockito.mock(Function.class);

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).computeIfAbsent(Mockito.anyString(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.computeIfAbsent(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void computeIfPresent() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<BiFunction<String, SmoothUnscalableSet, SmoothUnscalableSet>> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        BiFunction<String, SmoothUnscalableSet, SmoothUnscalableSet> arg2 = Mockito.mock(BiFunction.class);

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).computeIfPresent(Mockito.anyString(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.computeIfPresent(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void compute() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<BiFunction<String, SmoothUnscalableSet, SmoothUnscalableSet>> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        BiFunction<String, SmoothUnscalableSet, SmoothUnscalableSet> arg2 = Mockito.mock(BiFunction.class);

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).compute(Mockito.anyString(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.compute(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void merge() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothUnscalableSet> checkPoint2 = new AtomicReference<>();
        AtomicReference<BiFunction<SmoothUnscalableSet, SmoothUnscalableSet, SmoothUnscalableSet>> checkPoint3 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothUnscalableSet arg2 = new SmoothUnscalableSet();
        BiFunction<SmoothUnscalableSet, SmoothUnscalableSet, SmoothUnscalableSet> arg3 = Mockito.mock(BiFunction.class);

        SmoothUnscalableSet returned = new SmoothUnscalableSet();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            checkPoint3.set(invocationOnMock.getArgument(2));
            return returned;
        }).when(this.map).merge(Mockito.anyString(), Mockito.any(), Mockito.any());

        SmoothUnscalableMap header = new SmoothUnscalableMap(this.map);
        Assertions.assertThat(header.merge(arg1, arg2, arg3)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
        Assertions.assertThat(checkPoint3.get()).isEqualTo(arg3);
    }

}
