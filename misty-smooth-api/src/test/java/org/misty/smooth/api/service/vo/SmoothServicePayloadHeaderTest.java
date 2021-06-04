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
        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(true);
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothServicePayloadHeaderContent> map = getter.get();

        map.put("kerker", new SmoothServicePayloadHeaderContent());

        Assertions.assertThat(map.toString()).isEqualTo(header.toString());
    }

    @Test
    void getOrDefault_key() throws NoSuchFieldException {
        SmoothServicePayloadHeader header = new SmoothServicePayloadHeader(true);
        FieldExtractor fieldExtractor = new FieldExtractor(header);
        FieldObjectGetter<Map> getter = fieldExtractor.buildGetter("map", Map.class);
        Map<String, SmoothServicePayloadHeaderContent> map = getter.get();

        String key = "kerker";
        SmoothServicePayloadHeaderContent content = header.getOrDefault(key);

        Set<String> set = new FieldExtractor(content).buildGetter("set", Set.class).get();
        Assertions.assertThat(set).isInstanceOf(HashSet.class);
        Assertions.assertThat(map).containsEntry(key, content);
    }

    @Test
    void getOrDefault_sequential() {

    }

//    void size() {
//        AtomicBoolean checkPoint1 = new AtomicBoolean();
//
//        String arg1 = "kerker";
//
//        SmoothServicePayloadHeaderContent returned = 9527;
//
//        Mockito.doAnswer((invocationOnMock) -> {
//            checkPoint.set(true);
//            return returned;
//        }).when(this.set).size();
//
//        SmoothServicePayloadHeaderContent content = new SmoothServicePayloadHeaderContent(this.set);
//        Assertions.assertThat(content.size()).isEqualTo(returned);
//        Assertions.assertThat(checkPoint.get()).isTrue();
//    }


}
