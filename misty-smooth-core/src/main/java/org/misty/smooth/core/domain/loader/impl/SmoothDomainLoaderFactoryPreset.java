package org.misty.smooth.core.domain.loader.impl;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderFactory;
import org.misty.smooth.core.domain.loader.preset.SmoothDomainLoaderBuilder;
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

import java.net.URL;
import java.util.Collection;

public class SmoothDomainLoaderFactoryPreset implements SmoothDomainLoaderFactory {

    private final SmoothContext parentContext;

    private SmoothDomainClassLoaderFactory classLoaderFactory;

    private SmoothDomainLifecycleFactory lifecycleFactory;

    private SmoothDomainLifecycleThreadFactory lifecycleThreadFactory;

    public SmoothDomainLoaderFactoryPreset(SmoothContext parentContext) {
        this.parentContext = parentContext;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public SmoothManagerDomainLoader buildManagerLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources)
            throws SmoothLoadException {
        SmoothDomainLoaderBuilder<SmoothManagerLifecycle, SmoothManagerId, SmoothManagerDomainLoaderPreset> builder;
        builder = new SmoothDomainLoaderBuilder<>();
        builder.setClassLoaderFactory(this.classLoaderFactory::buildManagerClassLoader);
        builder.setLifecycleFactory(this.lifecycleFactory::findManagerLifecycle);
        builder.setSmoothIdFactory(SmoothManagerId::new);
        builder.setDomainLoaderFactory(SmoothManagerDomainLoaderPreset::new);
        builder.setLaunchThreadFactory(this.lifecycleThreadFactory::buildLaunchThread);
        builder.setParentContext(this.parentContext);
        return builder.build(loaderArgument, sources);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public SmoothModuleDomainLoader buildModuleLoader(SmoothLoaderArgument loaderArgument, Collection<URL> sources)
            throws SmoothLoadException {
        SmoothDomainLoaderBuilder<SmoothModuleLifecycle, SmoothModuleId, SmoothModuleDomainLoaderPreset> builder;
        builder = new SmoothDomainLoaderBuilder<>();
        builder.setClassLoaderFactory(this.classLoaderFactory::buildModuleClassLoader);
        builder.setLifecycleFactory(this.lifecycleFactory::findModuleLifecycle);
        builder.setSmoothIdFactory(SmoothModuleId::new);
        builder.setDomainLoaderFactory(SmoothModuleDomainLoaderPreset::new);
        builder.setLaunchThreadFactory(this.lifecycleThreadFactory::buildLaunchThread);
        builder.setParentContext(this.parentContext);
        return builder.build(loaderArgument, sources);
    }

    public SmoothDomainClassLoaderFactory getClassLoaderFactory() {
        return classLoaderFactory;
    }

    public void setClassLoaderFactory(SmoothDomainClassLoaderFactory classLoaderFactory) {
        this.classLoaderFactory = classLoaderFactory;
    }

    public SmoothDomainLifecycleFactory getLifecycleFactory() {
        return lifecycleFactory;
    }

    public void setLifecycleFactory(SmoothDomainLifecycleFactory lifecycleFactory) {
        this.lifecycleFactory = lifecycleFactory;
    }

    public SmoothDomainLifecycleThreadFactory getLifecycleThreadFactory() {
        return lifecycleThreadFactory;
    }

    public void setLifecycleThreadFactory(SmoothDomainLifecycleThreadFactory lifecycleThreadFactory) {
        this.lifecycleThreadFactory = lifecycleThreadFactory;
    }

}
