package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.cross.SmoothCrossWrapper;

import java.io.IOException;
import java.io.InputStream;

public class SmoothInputStreamCrossWrapper extends InputStream {

    private final SmoothCrossWrapper<InputStream> wrapper;

    public SmoothInputStreamCrossWrapper(InputStream inputStream) {
        this.wrapper = new SmoothCrossWrapper<>(inputStream.getClass().getClassLoader(), inputStream);
    }

    public InputStream getRawInputStream() {
        return this.wrapper.getWrappedTarget();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            return target.read(b);
        });
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            return target.read(b, off, len);
        });
    }

    @Override
    public long skip(long n) throws IOException {
        return this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            return target.skip(n);
        });
    }

    @Override
    public int available() throws IOException {
        return this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            return target.available();
        });
    }

    @Override
    public void close() throws IOException {
        this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            target.close();
        });
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            target.mark(readlimit);
        });
    }

    @Override
    public synchronized void reset() throws IOException {
        this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            target.reset();
        });
    }

    @Override
    public boolean markSupported() {
        return this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            return target.markSupported();
        });
    }

    @Override
    public int read() throws IOException {
        return this.wrapper.wrap(() -> {
            InputStream target = this.wrapper.getWrappedTarget();
            return target.read();
        });
    }
}
