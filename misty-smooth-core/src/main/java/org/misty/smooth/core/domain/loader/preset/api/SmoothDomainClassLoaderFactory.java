package org.misty.smooth.core.domain.loader.preset.api;

import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.core.domain.classloader.SmoothDomainClassLoader;

import java.net.URL;
import java.util.Collection;

public interface SmoothDomainClassLoaderFactory {

    SmoothDomainClassLoader buildManagerClassLoader(SmoothUnscalableMap loaderArgument, Collection<URL> sources);

    SmoothDomainClassLoader buildModuleClassLoader(SmoothUnscalableMap loaderArgument, Collection<URL> sources);

}
