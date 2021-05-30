package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.mark.Guide;

import java.io.IOException;
import java.io.OutputStream;

public class SmoothOutputStreamCrosser extends OutputStream {

    private final SmoothCrossWrapper<OutputStream> wrapper;

    public SmoothOutputStreamCrosser(
            @Guide(implementationBy = Guide.Scope.ANY, usedBy = Guide.Scope.MODULE) OutputStream outputStream
    ) {
        this.wrapper = new SmoothCrossWrapper<>(outputStream.getClass().getClassLoader(), outputStream);
    }

    public OutputStream getRawOutputStream() {
        return this.wrapper.getWrappedTarget();
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().write(b));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().write(b, off, len));
    }

    @Override
    public void flush() throws IOException {
        this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().flush());
    }

    @Override
    public void close() throws IOException {
        this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().close());
    }

    @Override
    public void write(int b) throws IOException {
        this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().write(b));
    }
}
