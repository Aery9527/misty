package org.misty.smooth.core.domain.loader.impl;

import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderFactory;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoader;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoader;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoaderPreset;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoaderPreset;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.net.URL;
import java.util.Collection;

public class SmoothDomainLoaderFactoryPreset implements SmoothDomainLoaderFactory {

    @Override
    public SmoothManagerDomainLoader buildManagerLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources) {
        SmoothManagerDomainLoaderPreset loader = new SmoothManagerDomainLoaderPreset();
        // TODO
        return loader;
    }

    @Override
    public SmoothModuleDomainLoader buildModuleLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources) {
        // classloader factory
        // find SmoothModuleLifecycle
        // define SmoothModuleId, SmoothLoadType

        SmoothModuleDomainLoaderPreset loader = new SmoothModuleDomainLoaderPreset();
        // TODO
        return loader;
    }

}
