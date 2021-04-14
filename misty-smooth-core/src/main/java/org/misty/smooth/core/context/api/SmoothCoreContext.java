package org.misty.smooth.core.context.api;

import org.misty.smooth.manager.context.SmoothManagerContext;

import java.io.Closeable;
import java.io.IOException;

public interface SmoothCoreContext extends SmoothManagerContext {

    void close();

}
