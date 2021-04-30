package org.misty.smooth.core.domain.manager.loader;

import org.misty.smooth.core.domain.loader.api.SmoothDomainLoaderAbstract;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.lifecycle.SmoothManagerLifecycle;
import org.misty.smooth.manager.loader.SmoothManagerLoader;

public class SmoothManagerDomainLoaderPreset
        extends SmoothDomainLoaderAbstract<SmoothManagerId, SmoothManagerLoader, SmoothManagerLifecycle>
        implements SmoothManagerDomainLoader {

}
