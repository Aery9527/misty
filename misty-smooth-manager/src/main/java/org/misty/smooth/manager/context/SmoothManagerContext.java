package org.misty.smooth.manager.context;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.manager.error.SmoothCloseException;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothManagerLoader;
import org.misty.smooth.manager.loader.SmoothModuleLoader;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

public interface SmoothManagerContext extends SmoothContext {

    default SmoothManagerLoader loadSmoothManager(URL... sources) throws SmoothLoadException {
        return loadSmoothManager(new SmoothLoaderArgument(), sources);
    }

    default SmoothManagerLoader loadSmoothManager(SmoothLoaderArgument loaderArgument, URL... sources) throws SmoothLoadException {
        return loadSmoothManager(loaderArgument, Arrays.asList(sources));
    }

    default SmoothManagerLoader loadSmoothManager(Collection<URL> sources) throws SmoothLoadException {
        return loadSmoothManager(new SmoothLoaderArgument(), sources);
    }

    SmoothManagerLoader loadSmoothManager(SmoothLoaderArgument loaderArgument, Collection<URL> sources) throws SmoothLoadException;

    //

    default SmoothModuleLoader loadSmoothModule(URL... sources) throws SmoothLoadException {
        return loadSmoothModule(new SmoothLoaderArgument(), sources);
    }

    default SmoothModuleLoader loadSmoothModule(SmoothLoaderArgument loaderArgument, URL... sources) throws SmoothLoadException {
        return loadSmoothModule(loaderArgument, Arrays.asList(sources));
    }

    default SmoothModuleLoader loadSmoothModule(Collection<URL> sources) throws SmoothLoadException {
        return loadSmoothModule(new SmoothLoaderArgument(), sources);
    }

    SmoothModuleLoader loadSmoothModule(SmoothLoaderArgument loaderArgument, Collection<URL> sources) throws SmoothLoadException;

    //

    void close() throws SmoothCloseException;

}
