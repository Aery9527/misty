package org.misty.smooth.core.domain.module.loader;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.lifecycle.module.SmoothModuleRegister;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderAbstract;
import org.misty.smooth.manager.loader.SmoothModuleLoader;

public class SmoothModuleDomainLoaderPreset
        extends SmoothDomainLoaderAbstract<SmoothModuleId, SmoothModuleLoader, SmoothModuleLifecycle>
        implements SmoothModuleDomainLoader {

    @Override
    protected void initialLifecycle(SmoothModuleLifecycle domainLifecycle) {




        SmoothContext context = null;
        SmoothModuleRegister register = null;
        domainLifecycle.initial(context, register);
    }

}
