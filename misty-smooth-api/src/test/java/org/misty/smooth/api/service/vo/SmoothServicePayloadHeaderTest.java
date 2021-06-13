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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings({"unchecked", "rawtypes"})
@ExtendWith(MockitoExtension.class)
public class SmoothServicePayloadHeaderTest {

    @Mock
    private Map<String, SmoothServicePayloadHeaderContent> map;

    @BeforeEach
    void setUp() {
        Mockito.reset(this.map);
    }

    @Test
    void constructor() throws NoSuchFieldException {
        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader();
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothServicePayloadHeaderContent> map = getter.get();
        Assertions.assertThat(map).isInstanceOf(HashMap.class);
    }

    @Test
    void constructor_true() throws NoSuchFieldException {
        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(true);
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothServicePayloadHeaderContent> map = getter.get();
        Assertions.assertThat(map).isInstanceOf(TreeMap.class);
    }

    @Test
    void testToString() throws NoSuchFieldException {
        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader();
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothServicePayloadHeaderContent> map = getter.get();

        map.put("kerker", new SmoothServicePayloadHeaderContent());

        Assertions.assertThat(map.toString()).isEqualTo(header.toString());
    }

    @Test
    void getOrPutDefault_key() throws NoSuchFieldException {
        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(true);
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothServicePayloadHeaderContent> map = getter.get();

        String key = "kerker";
        SmoothServicePayloadHeaderContent content = header.getOrPutDefault(key);

        Set<String> set = new FieldExtractor(content).buildGetter("set", Set.class).get();
        Assertions.assertThat(set).isInstanceOf(HashSet.class);
        Assertions.assertThat(map).containsEntry(key, content);
    }

    @Test
    void getOrPutDefault_sequential() throws NoSuchFieldException {
        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(true);
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothServicePayloadHeaderContent> map = getter.get();

        String key = "kerker";
        SmoothServicePayloadHeaderContent content = header.getOrPutDefault(key, true);

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

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
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

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
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

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.containsKey(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void containsValue() {
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint1 = new AtomicReference<>();

        SmoothServicePayloadHeaderContent arg1 = new SmoothServicePayloadHeaderContent();

        boolean returned = true;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.map).containsValue(Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.containsValue(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void get() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.map).get(Mockito.anyString());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.get(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void put() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothServicePayloadHeaderContent arg2 = new SmoothServicePayloadHeaderContent();

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).put(Mockito.anyString(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.put(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void remove() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        String arg1 = "kerker";

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return returned;
        }).when(this.map).remove(Mockito.anyString());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.remove(arg1)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void putAll() {
        AtomicReference<Map<String, SmoothServicePayloadHeaderContent>> checkPoint1 = new AtomicReference<>();

        Map<String, SmoothServicePayloadHeaderContent> arg1 = new HashMap<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.map).putAll(Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
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

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
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

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.keySet()).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void values() {
        AtomicBoolean checkPoint1 = new AtomicBoolean();

        Collection<SmoothServicePayloadHeaderContent> returned = new ArrayList<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(true);
            return returned;
        }).when(this.map).values();

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.values()).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void entrySet() {
        AtomicBoolean checkPoint1 = new AtomicBoolean();

        Set<Map.Entry<String, SmoothServicePayloadHeaderContent>> returned = new HashSet<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(true);
            return returned;
        }).when(this.map).entrySet();

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.entrySet()).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isTrue();
    }

    @Test
    void getOrDefault() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothServicePayloadHeaderContent arg2 = new SmoothServicePayloadHeaderContent();

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).getOrDefault(Mockito.anyString(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.getOrDefault(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void forEach() {
        AtomicReference<BiConsumer<String, SmoothServicePayloadHeaderContent>> checkPoint1 = new AtomicReference<>();

        BiConsumer<String, SmoothServicePayloadHeaderContent> arg1 = Mockito.mock(BiConsumer.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.map).forEach(Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        header.forEach(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void replaceAll() {
        AtomicReference<BiFunction<String, SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent>> checkPoint1 = new AtomicReference<>();

        BiFunction<String, SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent> arg1 = Mockito.mock(BiFunction.class);

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.map).replaceAll(Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        header.replaceAll(arg1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
    }

    @Test
    void putIfAbsent() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothServicePayloadHeaderContent arg2 = new SmoothServicePayloadHeaderContent();

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).putIfAbsent(Mockito.anyString(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.putIfAbsent(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void remove_key_value() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothServicePayloadHeaderContent arg2 = new SmoothServicePayloadHeaderContent();

        boolean returned = true;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).remove(Mockito.anyString(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.remove(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void replace_key_oldValue_newValue() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint2 = new AtomicReference<>();
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint3 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothServicePayloadHeaderContent arg2 = new SmoothServicePayloadHeaderContent();
        SmoothServicePayloadHeaderContent arg3 = new SmoothServicePayloadHeaderContent();

        boolean returned = true;

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            checkPoint3.set(invocationOnMock.getArgument(2));
            return returned;
        }).when(this.map).replace(Mockito.anyString(), Mockito.any(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.replace(arg1, arg2, arg3)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
        Assertions.assertThat(checkPoint3.get()).isEqualTo(arg3);
    }

    @Test
    void replace_key_Value() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothServicePayloadHeaderContent arg2 = new SmoothServicePayloadHeaderContent();

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).replace(Mockito.anyString(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.replace(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void computeIfAbsent() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<Function<String, SmoothServicePayloadHeaderContent>> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        Function<String, SmoothServicePayloadHeaderContent> arg2 = Mockito.mock(Function.class);

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).computeIfAbsent(Mockito.anyString(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.computeIfAbsent(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void computeIfPresent() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<BiFunction<String, SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent>> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        BiFunction<String, SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent> arg2 = Mockito.mock(BiFunction.class);

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).computeIfPresent(Mockito.anyString(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.computeIfPresent(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void compute() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<BiFunction<String, SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent>> checkPoint2 = new AtomicReference<>();

        String arg1 = "kerker";
        BiFunction<String, SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent> arg2 = Mockito.mock(BiFunction.class);

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return returned;
        }).when(this.map).compute(Mockito.anyString(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.compute(arg1, arg2)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
    }

    @Test
    void merge() {
        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothServicePayloadHeaderContent> checkPoint2 = new AtomicReference<>();
        AtomicReference<BiFunction<SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent>> checkPoint3 = new AtomicReference<>();

        String arg1 = "kerker";
        SmoothServicePayloadHeaderContent arg2 = new SmoothServicePayloadHeaderContent();
        BiFunction<SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent, SmoothServicePayloadHeaderContent> arg3 = Mockito.mock(BiFunction.class);

        SmoothServicePayloadHeaderContent returned = new SmoothServicePayloadHeaderContent();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            checkPoint3.set(invocationOnMock.getArgument(2));
            return returned;
        }).when(this.map).merge(Mockito.anyString(), Mockito.any(), Mockito.any());

        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(this.map);
        Assertions.assertThat(header.merge(arg1, arg2, arg3)).isEqualTo(returned);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(arg1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(arg2);
        Assertions.assertThat(checkPoint3.get()).isEqualTo(arg3);
    }

}
