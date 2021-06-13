package org.misty.smooth.api.service.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class SmoothServiceRequestTest extends SmoothServicePayloadTest {

    @Test
    void constructor() {
        Consumer<SmoothServiceRequest> attachmentEmptyTest = (request) -> {
            Assertions.assertThat(request.getAttachment()).isEmpty();
        };
        BiConsumer<SmoothServiceRequest, InputStream> attachmentNotEmptyTest = (request, attachment) -> {
            Assertions.assertThat(request.getAttachment())
                    .isNotEmpty().get()
                    .isEqualTo(attachment);
        };

        super.test(SmoothServiceRequest::new, (body, request) -> attachmentEmptyTest.accept(request));

        InputStream attachment = Mockito.mock(InputStream.class);
        super.test((body) -> {
            return new SmoothServiceRequest(body, attachment);
        }, (body, request) -> {
            attachmentNotEmptyTest.accept(request, attachment);
        });

        attachmentEmptyTest.accept(new SmoothServiceRequest());

        attachmentNotEmptyTest.accept(new SmoothServiceRequest(attachment), attachment);
    }

}
