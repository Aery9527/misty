package org.misty.smooth.core.domain.module.loader;

import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.api.lifecycle.module.SmoothModuleLifecycle;
import org.misty.smooth.api.lifecycle.module.SmoothModuleRegister;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.domain.impl.SmoothDomainContext;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderAbstract;
import org.misty.smooth.manager.loader.SmoothModuleLoader;

public class SmoothModuleDomainLoaderPreset
        extends SmoothDomainLoaderAbstract<SmoothModuleId, SmoothModuleLoader, SmoothModuleLifecycle>
        implements SmoothModuleDomainLoader {

    @Override
    protected void initialLifecycle(SmoothModuleLifecycle domainLifecycle, SmoothLoadType loadType) {
        SmoothDomainContext context = new SmoothDomainContext();
        context.setSmoothId(super.getSmoothId());
        context.setLaunchInstant(super.getSmoothId().getLaunchTime());
        context.setParentContext(super.getParentContext());

        SmoothModuleRegister register = null;

        domainLifecycle.initial(context, register); // FIXME lifecycle need cross


    }

}
