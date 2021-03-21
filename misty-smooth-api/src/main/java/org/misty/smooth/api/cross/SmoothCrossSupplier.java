package org.misty.smooth.api.cross;

public interface SmoothCrossSupplier<T> {

    T get() throws Exception;

}
