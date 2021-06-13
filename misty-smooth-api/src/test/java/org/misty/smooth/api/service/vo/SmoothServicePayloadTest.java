package org.misty.smooth.api.service.vo;

import org.assertj.core.api.Assertions;
import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.api.vo.SmoothUnscalableSet;

import java.util.function.BiConsumer;
import java.util.function.Function;

class SmoothServicePayloadTest {

    <Type extends SmoothServicePayload> void test(Function<String, Type> payloadFactory) {
        test(payloadFactory, (body, payload) -> {
        });
    }

    <Type extends SmoothServicePayload> void test(Function<String, Type> payloadFactory, BiConsumer<String, Type> consumer) {
        Type payload = payloadFactory.apply(null);
        Assertions.assertThat(payload.getHeader()).isEmpty();
        Assertions.assertThat(payload.getBody()).isEmpty();
        Assertions.assertThat(payload.containsHeader("")).isFalse();
        Assertions.assertThat(payload.containsHeader("", "")).isFalse();

        SmoothUnscalableMap header = payload.getHeaderOrCreate();
        Assertions.assertThat(payload.getHeader()).isNotEmpty().get().isEqualTo(header);

        SmoothUnscalableSet content = new SmoothUnscalableSet();
        content.add("9527");
        header.put("aaa", content);
        Assertions.assertThat(payload.containsHeader("aaa")).isTrue();
        Assertions.assertThat(payload.containsHeader("aaa", "9527")).isTrue();

        consumer.accept(null, payload);

        String body = "kerker";
        payload = payloadFactory.apply(body);
        Assertions.assertThat(payload.getBody()).isNotEmpty().get().isEqualTo(body);

        consumer.accept(body, payload);
    }

}
