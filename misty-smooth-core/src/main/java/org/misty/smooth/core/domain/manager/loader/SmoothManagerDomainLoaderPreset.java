package org.misty.smooth.core.domain.manager.loader;

import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderAbstract;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.context.SmoothManagerContext;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.lifecycle.SmoothManagerRegister;
import org.misty.smooth.manager.loader.SmoothManagerLoader;

public class SmoothManagerDomainLoaderPreset
        extends SmoothDomainLoaderAbstract<SmoothManagerId, SmoothManagerLoader, SmoothManagerLifecycle>
        implements SmoothManagerDomainLoader {

    @Override
    protected void initialLifecycle(SmoothManagerLifecycle domainLifecycle, SmoothLoadType loadType) {
        SmoothManagerContext context = null; // TODO
        SmoothManagerRegister register = null; // TODO
        domainLifecycle.initial(context, register);
    }

}
