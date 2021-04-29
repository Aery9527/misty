package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoader;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoader;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.net.URL;
import java.util.Collection;

public interface SmoothDomainLoaderFactory {

    SmoothManagerDomainLoader buildManagerLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources)
            throws SmoothLoadException;

    SmoothModuleDomainLoader buildModuleLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources)
            throws SmoothLoadException;

}
