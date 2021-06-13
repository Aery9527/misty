package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoader;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoader;
import org.misty.smooth.manager.error.SmoothLoadException;

import java.net.URL;
import java.util.Collection;

public interface SmoothDomainLoaderFactory {

    SmoothManagerDomainLoader buildManagerLoader(SmoothUnscalableMap loaderArgument, Collection<URL> sources)
            throws SmoothLoadException;

    SmoothModuleDomainLoader buildModuleLoader(SmoothUnscalableMap loaderArgument, Collection<URL> sources)
            throws SmoothLoadException;

}
