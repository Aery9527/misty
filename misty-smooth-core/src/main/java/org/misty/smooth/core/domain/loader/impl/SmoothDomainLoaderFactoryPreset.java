package org.misty.smooth.core.domain.loader.impl;

import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderFactory;
import org.misty.smooth.core.domain.loader.preset.StepExecutor;
import org.misty.smooth.core.domain.loader.preset.api.SmoothDomainClassLoaderFactory;
import org.misty.smooth.core.domain.loader.preset.api.SmoothDomainLifecycleFactory;
import org.misty.smooth.core.domain.loader.preset.api.SmoothDomainLifecycleThreadFactory;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoader;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoaderPreset;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoader;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoaderPreset;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Collection;

public class SmoothDomainLoaderFactoryPreset implements SmoothDomainLoaderFactory {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SmoothDomainClassLoaderFactory classLoaderFactory;

    private SmoothDomainLifecycleFactory lifecycleFactory;

    private SmoothDomainLifecycleThreadFactory lifecycleThreadFactory;

    @SuppressWarnings("DuplicatedCode")
    @Override
    public SmoothManagerDomainLoader buildManagerLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources)
            throws SmoothLoadException {
        StepExecutor<SmoothManagerLifecycle, SmoothManagerId, SmoothManagerDomainLoaderPreset> stepExecutor;
        stepExecutor = new StepExecutor<>();
        stepExecutor.setClassLoaderFactory(this.classLoaderFactory::buildManagerClassLoader);
        stepExecutor.setLifecycleFactory(this.lifecycleFactory::findManagerLifecycle);
        stepExecutor.setSmoothIdFactory(SmoothManagerId::new);
        stepExecutor.setDomainLoaderFactory(SmoothManagerDomainLoaderPreset::new);
        stepExecutor.setLaunchThreadFactory(this.lifecycleThreadFactory::buildLaunchThread);
        return stepExecutor.build(loaderArgument, sources);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public SmoothModuleDomainLoader buildModuleLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources)
            throws SmoothLoadException {
        StepExecutor<SmoothModuleLifecycle, SmoothModuleId, SmoothModuleDomainLoaderPreset> stepExecutor;
        stepExecutor = new StepExecutor<>();
        stepExecutor.setClassLoaderFactory(this.classLoaderFactory::buildModuleClassLoader);
        stepExecutor.setLifecycleFactory(this.lifecycleFactory::findModuleLifecycle);
        stepExecutor.setSmoothIdFactory(SmoothModuleId::new);
        stepExecutor.setDomainLoaderFactory(SmoothModuleDomainLoaderPreset::new);
        stepExecutor.setLaunchThreadFactory(this.lifecycleThreadFactory::buildLaunchThread);
        return stepExecutor.build(loaderArgument, sources);
    }

}
