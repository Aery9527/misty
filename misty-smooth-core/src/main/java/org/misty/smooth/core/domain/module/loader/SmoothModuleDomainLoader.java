package org.misty.smooth.core.domain.module.loader;

import org.misty.smooth.core.context.api.SmoothDomainCamp;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoader;
import org.misty.smooth.manager.loader.SmoothModuleLoader;

public interface SmoothModuleDomainLoader extends SmoothDomainLoader, SmoothModuleLoader {

    void setDomainCamp(SmoothDomainCamp domainCamp);

}
