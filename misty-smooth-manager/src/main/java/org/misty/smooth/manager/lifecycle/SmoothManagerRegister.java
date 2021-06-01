package org.misty.smooth.manager.lifecycle;

import org.misty.smooth.api.lifecycle.SmoothRegister;
import org.misty.smooth.api.mark.SmoothGuide;

@SmoothGuide(needCross = true,
        implementationBy = SmoothGuide.Domain.CORE,
        usedBy = SmoothGuide.Domain.MANAGER
)
public interface SmoothManagerRegister extends SmoothRegister {

}
