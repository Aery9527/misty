package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.cross.SmoothCrosser;
import org.misty.smooth.api.passable.SmoothPassableSupplier;

import java.io.IOException;
import java.io.InputStream;

public class SmoothInputStreamCrosser extends InputStream {

    private final SmoothCrosser crosser;

    private final InputStream rawInputStream;

    public SmoothInputStreamCrosser(InputStream inputStream) {
        this.crosser = new SmoothCrosser(inputStream.getClass().getClassLoader());
        this.rawInputStream = inputStream;
    }

    @Override
    public int hashCode() {
        return this.crosser.wrap(this.rawInputStream::hashCode);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return this.crosser.wrap(() -> this.rawInputStream.equals(obj));
    }

    @Override
    public String toString() {
        return this.crosser.wrap(this.rawInputStream::toString);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.crosser.wrap(() -> this.rawInputStream.read(b));
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.crosser.wrap(() -> this.rawInputStream.read(b, off, len));
    }

    @Override
    public long skip(long n) throws IOException {
        return this.crosser.wrap(() -> this.rawInputStream.skip(n));
    }

    @Override
    public int available() throws IOException {
        return this.crosser.wrap(this.rawInputStream::available);
    }

    @Override
    public void close() throws IOException {
        this.crosser.wrap(this.rawInputStream::close);
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.crosser.wrap(() -> this.rawInputStream.mark(readlimit));
    }

    @Override
    public synchronized void reset() throws IOException {
        this.crosser.wrap(this.rawInputStream::reset);
    }

    @Override
    public boolean markSupported() {
        return this.crosser.wrap(this.rawInputStream::markSupported);
    }

    @Override
    public int read() throws IOException {
        return this.crosser.wrap((SmoothPassableSupplier<Integer>) this.rawInputStream::read);
    }
}
