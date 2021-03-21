package org.misty.smooth.manager.context;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.manager.error.SmoothLoadException;

import java.io.Closeable;
import java.net.URL;
import java.util.Collection;

public interface SmoothManagerContext extends SmoothContext, Closeable {

    void loadSmoothManager(Collection<URL> sources) throws SmoothLoadException;

    void loadSmoothModule(Collection<URL> sources) throws SmoothLoadException;

}
