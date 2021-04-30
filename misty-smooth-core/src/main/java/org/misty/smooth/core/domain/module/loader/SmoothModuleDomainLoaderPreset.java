package org.misty.smooth.core.domain.module.loader;

import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.context.api.SmoothDomainCamp;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderAbstract;
import org.misty.smooth.manager.loader.SmoothModuleLoader;

public class SmoothModuleDomainLoaderPreset
        extends SmoothDomainLoaderAbstract<SmoothModuleId, SmoothModuleLoader, SmoothModuleLifecycle>
        implements SmoothModuleDomainLoader {

    private SmoothDomainCamp domainCamp;

    @Override
    public void setDomainCamp(SmoothDomainCamp domainCamp) {

    }

}
