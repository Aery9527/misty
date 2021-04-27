package org.misty.smooth.manager.context;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.manager.error.SmoothLoadException;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

public interface SmoothManagerContext extends SmoothContext {

    default void loadSmoothManager(URL... sources) throws SmoothLoadException {
        loadSmoothManager(Arrays.asList(sources));
    }

    void loadSmoothManager(Collection<URL> sources) throws SmoothLoadException;

    default void loadSmoothModule(URL... sources) throws SmoothLoadException {
        loadSmoothModule(Arrays.asList(sources));
    }

    void loadSmoothModule(Collection<URL> sources) throws SmoothLoadException;

}
