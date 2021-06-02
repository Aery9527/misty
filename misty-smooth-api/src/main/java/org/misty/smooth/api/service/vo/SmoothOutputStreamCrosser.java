package org.misty.smooth.api.service.vo;

import org.misty.smooth.api.cross.SmoothCrosser;

import java.io.IOException;
import java.io.OutputStream;

public class SmoothOutputStreamCrosser extends OutputStream {

    private final SmoothCrosser crosser;

    private final OutputStream rawOutputStream;

    public SmoothOutputStreamCrosser(OutputStream outputStream) {
        this.crosser = new SmoothCrosser(outputStream.getClass().getClassLoader());
        this.rawOutputStream = outputStream;
    }

    @Override
    public int hashCode() {
        return this.crosser.wrap(this.rawOutputStream::hashCode);
    }

    @Override
    public boolean equals(Object obj) {
        return this.crosser.wrap(() -> this.rawOutputStream.equals(obj));
    }

    @Override
    public String toString() {
        return this.crosser.wrap(this.rawOutputStream::toString);
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.crosser.wrap(() -> this.rawOutputStream.write(b));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.crosser.wrap(() -> this.rawOutputStream.write(b, off, len));
    }

    @Override
    public void flush() throws IOException {
        this.crosser.wrap(this.rawOutputStream::flush);
    }

    @Override
    public void close() throws IOException {
        this.crosser.wrap(this.rawOutputStream::close);
    }

    @Override
    public void write(int b) throws IOException {
        this.crosser.wrap(() -> this.rawOutputStream.write(b));
    }
}
