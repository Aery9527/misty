package org.misty.smooth.manager.context;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.mark.SmoothGuide;
import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.manager.error.SmoothCloseException;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothManagerLoader;
import org.misty.smooth.manager.loader.SmoothModuleLoader;

import java.net.URL;
import java.util.Collection;

@SmoothGuide(needCross = true,
        implementationBy = SmoothGuide.Domain.CORE,
        usedBy = SmoothGuide.Domain.MANAGER
)
public interface SmoothManagerContext extends SmoothContext {

    SmoothManagerLoader loadSmoothManager(URL... sources) throws SmoothLoadException;

    SmoothManagerLoader loadSmoothManager(SmoothUnscalableMap loaderArgument, URL... sources) throws SmoothLoadException;

    SmoothManagerLoader loadSmoothManager(Collection<URL> sources) throws SmoothLoadException;

    SmoothManagerLoader loadSmoothManager(SmoothUnscalableMap loaderArgument, Collection<URL> sources) throws SmoothLoadException;

    SmoothModuleLoader loadSmoothModule(URL... sources) throws SmoothLoadException;

    SmoothModuleLoader loadSmoothModule(SmoothUnscalableMap loaderArgument, URL... sources) throws SmoothLoadException;

    SmoothModuleLoader loadSmoothModule(Collection<URL> sources) throws SmoothLoadException;

    SmoothModuleLoader loadSmoothModule(SmoothUnscalableMap loaderArgument, Collection<URL> sources) throws SmoothLoadException;

    void close() throws SmoothCloseException;

}
