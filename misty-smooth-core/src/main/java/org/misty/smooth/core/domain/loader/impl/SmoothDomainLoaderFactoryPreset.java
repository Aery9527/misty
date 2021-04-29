package org.misty.smooth.core.domain.loader.impl;

import org.misty.smooth.api.cross.SmoothCrossObject;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.domain.classloader.SmoothDomainClassLoader;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderFactory;
import org.misty.smooth.core.domain.loader.preset.api.SmoothDomainClassLoaderFactory;
import org.misty.smooth.core.domain.loader.preset.api.SmoothDomainLifecycleFactory;
import org.misty.smooth.core.domain.loader.preset.api.SmoothDomainLifecycleThreadFactory;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoader;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoaderPreset;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoader;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoaderPreset;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class SmoothDomainLoaderFactoryPreset implements SmoothDomainLoaderFactory {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SmoothDomainClassLoaderFactory classLoaderFactory;

    private SmoothDomainLifecycleFactory lifecycleFactory;

    private SmoothDomainLifecycleThreadFactory lifecycleThreadFactory;

    @Override
    public SmoothManagerDomainLoader buildManagerLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources)
            throws SmoothLoadException {
        SmoothManagerDomainLoaderPreset loader = new SmoothManagerDomainLoaderPreset();
        // TODO
        return loader;
    }

    @Override
    public SmoothModuleDomainLoader buildModuleLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources)
            throws SmoothLoadException {
        SmoothDomainClassLoader cl = null;
        try {
            cl = this.classLoaderFactory.buildModuleClassLoader(loaderArgument, sources);

            SmoothCrossObject domainCrosser = new SmoothCrossObject(cl);

            SmoothModuleLifecycle domainLifecycle = this.lifecycleFactory.findModuleLifecycle(loaderArgument, cl);

            SmoothModuleId domainId = domainCrosser.wrap(() -> {
                String moduleName = domainLifecycle.getName();
                String moduleVersion = domainLifecycle.getVersion();
                return new SmoothModuleId(moduleName, moduleVersion);
            });

            cl.setSmoothId(domainId);

            SmoothLoadType loadType;

            SmoothModuleDomainLoaderPreset loader = new SmoothModuleDomainLoaderPreset();
            loader.setSmoothId(domainId);
            loader.setLoadType(loadType);
            loader.setLoaderArgument(loaderArgument);
            loader.setLifecycleThreadFactory(this.lifecycleThreadFactory);
            return loader;

        } catch (Throwable t) {
            if (cl != null) {
                try {
                    cl.close();
                } catch (IOException e) {
                    this.logger.error(SmoothDomainClassLoader.class.getSimpleName() + "(" + cl + ") close error.", e);
                }
            }

            throw new SmoothLoadException(t);
        }
    }

}
