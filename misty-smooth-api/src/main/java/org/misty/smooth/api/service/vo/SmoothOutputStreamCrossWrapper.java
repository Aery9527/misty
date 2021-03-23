package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.cross.SmoothCrossWrapper;

import java.io.IOException;
import java.io.OutputStream;

public class SmoothOutputStreamCrossWrapper extends OutputStream {

    private final SmoothCrossWrapper<OutputStream> wrapper;

    public SmoothOutputStreamCrossWrapper(OutputStream inputStream) {
        this.wrapper = new SmoothCrossWrapper<>(inputStream.getClass().getClassLoader(), inputStream);
    }

    public OutputStream getRawOutputStream() {
        return this.wrapper.getWrappedTarget();
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.wrapper.wrap(() -> {
            OutputStream target = this.wrapper.getWrappedTarget();
            target.write(b);
        });
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.wrapper.wrap(() -> {
            OutputStream target = this.wrapper.getWrappedTarget();
            target.write(b, off, len);
        });
    }

    @Override
    public void flush() throws IOException {
        this.wrapper.wrap(() -> {
            OutputStream target = this.wrapper.getWrappedTarget();
            target.flush();
        });
    }

    @Override
    public void close() throws IOException {
        this.wrapper.wrap(() -> {
            OutputStream target = this.wrapper.getWrappedTarget();
            target.close();
        });
    }

    @Override
    public void write(int b) throws IOException {
        this.wrapper.wrap(() -> {
            OutputStream target = this.wrapper.getWrappedTarget();
            target.write(b);
        });
    }
}
