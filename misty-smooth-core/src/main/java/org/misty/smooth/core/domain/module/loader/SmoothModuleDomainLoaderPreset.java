package org.misty.smooth.core.domain.module.loader;

import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.context.api.SmoothDomainCamp;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderAbstract;
import org.misty.smooth.manager.loader.SmoothModuleLoader;

public class SmoothModuleDomainLoaderPreset
        extends SmoothDomainLoaderAbstract<SmoothModuleId, SmoothModuleLoader>
        implements SmoothModuleDomainLoader {

    @Override
    public void setDomainCamp(SmoothDomainCamp domainCamp) {

    }

}
