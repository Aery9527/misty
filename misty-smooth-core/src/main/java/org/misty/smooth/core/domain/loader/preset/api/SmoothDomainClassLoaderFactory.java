package org.misty.smooth.core.domain.loader.preset.api;

import org.misty.smooth.core.domain.classloader.SmoothDomainClassLoader;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.net.URL;
import java.util.Collection;

public interface SmoothDomainClassLoaderFactory {

    SmoothDomainClassLoader buildManagerClassLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources);

    SmoothDomainClassLoader buildModuleClassLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources);

}
