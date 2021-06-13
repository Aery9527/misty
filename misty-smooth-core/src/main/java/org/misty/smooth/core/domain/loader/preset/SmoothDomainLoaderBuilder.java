package org.misty.smooth.core.domain.loader.preset;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.cross.SmoothCrosser;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.core.domain.classloader.SmoothDomainClassLoader;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLaunchThreadFactory;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderAbstract;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class SmoothDomainLoaderBuilder<
        LifecycleType extends SmoothLifecycle,
        SmoothIdType extends SmoothId<SmoothIdType>,
        DomainLoaderType extends SmoothDomainLoaderAbstract<SmoothIdType, ?, LifecycleType>
        > {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private BiFunction<SmoothUnscalableMap, Collection<URL>, SmoothDomainClassLoader> classLoaderFactory;

    private BiFunction<SmoothUnscalableMap, SmoothCrosser, LifecycleType> lifecycleFactory;

    private BiFunction<String, String, SmoothIdType> smoothIdFactory;

    private Supplier<DomainLoaderType> domainLoaderFactory;

    private SmoothDomainLaunchThreadFactory<SmoothIdType> launchThreadFactory;

    private SmoothContext parentContext;

    public DomainLoaderType build(SmoothUnscalableMap loaderArgument, Collection<URL> sources) throws SmoothLoadException {
        SmoothDomainClassLoader classloader = null;
        try {
            classloader = this.classLoaderFactory.apply(loaderArgument, sources);

            SmoothCrosser domainCrosser = new SmoothCrosser(classloader);

            LifecycleType domainLifecycle = this.lifecycleFactory.apply(loaderArgument, domainCrosser);

            String moduleName = domainCrosser.wrap(domainLifecycle::getName);
            String moduleVersion = domainCrosser.wrap(domainLifecycle::getVersion);
            SmoothIdType domainId = this.smoothIdFactory.apply(moduleName, moduleVersion);

            classloader.setSmoothId(domainId);

            DomainLoaderType loader = this.domainLoaderFactory.get();
            loader.setDomainCrosser(domainCrosser);
            loader.setSmoothId(domainId);
            loader.setLoaderArgument(loaderArgument);
            loader.setDomainLifecycle(domainLifecycle);
            loader.setLaunchThreadFactory(this.launchThreadFactory);
            loader.setParentContext(this.parentContext);
            return loader;

        } catch (Exception t) {
            if (classloader != null) {
                try {
                    classloader.close();
                } catch (IOException e) {
                    this.logger.error(classloader + " close error.", e);
                }
            }

            throw SmoothLoadException.wrap(t);
        }
    }

    public BiFunction<SmoothUnscalableMap, Collection<URL>, SmoothDomainClassLoader> getClassLoaderFactory() {
        return classLoaderFactory;
    }

    public void setClassLoaderFactory(BiFunction<SmoothUnscalableMap, Collection<URL>, SmoothDomainClassLoader> classLoaderFactory) {
        this.classLoaderFactory = classLoaderFactory;
    }

    public BiFunction<SmoothUnscalableMap, SmoothCrosser, LifecycleType> getLifecycleFactory() {
        return lifecycleFactory;
    }

    public void setLifecycleFactory(BiFunction<SmoothUnscalableMap, SmoothCrosser, LifecycleType> lifecycleFactory) {
        this.lifecycleFactory = lifecycleFactory;
    }

    public BiFunction<String, String, SmoothIdType> getSmoothIdFactory() {
        return smoothIdFactory;
    }

    public void setSmoothIdFactory(BiFunction<String, String, SmoothIdType> smoothIdFactory) {
        this.smoothIdFactory = smoothIdFactory;
    }

    public Supplier<DomainLoaderType> getDomainLoaderFactory() {
        return domainLoaderFactory;
    }

    public void setDomainLoaderFactory(Supplier<DomainLoaderType> domainLoaderFactory) {
        this.domainLoaderFactory = domainLoaderFactory;
    }

    public SmoothDomainLaunchThreadFactory<SmoothIdType> getLaunchThreadFactory() {
        return launchThreadFactory;
    }

    public void setLaunchThreadFactory(SmoothDomainLaunchThreadFactory<SmoothIdType> launchThreadFactory) {
        this.launchThreadFactory = launchThreadFactory;
    }

    public SmoothContext getParentContext() {
        return parentContext;
    }

    public void setParentContext(SmoothContext parentContext) {
        this.parentContext = parentContext;
    }
}
