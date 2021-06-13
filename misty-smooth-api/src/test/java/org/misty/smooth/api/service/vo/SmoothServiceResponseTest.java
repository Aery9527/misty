package org.misty.smooth.api.service.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.OutputStream;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class SmoothServiceResponseTest extends SmoothServicePayloadTest {

    @Test
    void constructor() {
        Consumer<SmoothServiceResponse> attachmentEmptyTest = (response) -> {
            Assertions.assertThat(response.getAttachment()).isEmpty();
        };
        BiConsumer<SmoothServiceResponse, OutputStream> attachmentNotEmptyTest = (response, attachment) -> {
            Assertions.assertThat(response.getAttachment())
                    .isNotEmpty().get()
                    .isEqualTo(attachment);
        };

        super.test(SmoothServiceResponse::new, (body, response) -> attachmentEmptyTest.accept(response));

        OutputStream attachment = Mockito.mock(OutputStream.class);
        super.test((body) -> {
            return new SmoothServiceResponse(body, attachment);
        }, (body, response) -> {
            attachmentNotEmptyTest.accept(response, attachment);
        });

        attachmentEmptyTest.accept(new SmoothServiceResponse());

        attachmentNotEmptyTest.accept(new SmoothServiceResponse(attachment), attachment);
    }

}
