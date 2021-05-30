package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.mark.Guide;

import java.io.IOException;
import java.io.InputStream;

@Guide(
        implementationBy = {Guide.Scope.CORE, Guide.Scope.MANAGER},
        usedBy = Guide.Scope.MODULE
)
public class SmoothInputStreamCrosser extends InputStream {

    private final SmoothCrossWrapper<InputStream> wrapper;

    public SmoothInputStreamCrosser(InputStream inputStream) {
        this.wrapper = new SmoothCrossWrapper<>(inputStream.getClass().getClassLoader(), inputStream);
    }

    public InputStream getRawInputStream() {
        return this.wrapper.getWrappedTarget();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().read(b));
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().read(b, off, len));
    }

    @Override
    public long skip(long n) throws IOException {
        return this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().skip(n));
    }

    @Override
    public int available() throws IOException {
        return this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().available());
    }

    @Override
    public void close() throws IOException {
        this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().close());
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().mark(readlimit));
    }

    @Override
    public synchronized void reset() throws IOException {
        this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().reset());
    }

    @Override
    public boolean markSupported() {
        return this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().markSupported());
    }

    @Override
    public int read() throws IOException {
        return this.wrapper.wrap(() -> this.wrapper.getWrappedTarget().read());
    }
}
