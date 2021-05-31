package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.mark.SmoothGuide;

import java.util.Set;

@SmoothGuide(needCross = true,
        implementationBy = {SmoothGuide.Domain.MANAGER, SmoothGuide.Domain.MODULE},
        usedBy = SmoothGuide.Domain.CORE
)
public interface SmoothLifecycle {

    String getName();

    String getVersion();

    Set<String> getAttachment();

    void online();

    void destroy();

}
