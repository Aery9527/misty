package org.misty.smooth.api.lifecycle.module;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.mark.NeedCross;

@NeedCross(
        implementation = NeedCross.Scope.MODULE,
        usedBy = NeedCross.Scope.CORE
)
public interface SmoothModuleLifecycle extends SmoothLifecycle {

    void initial(SmoothContext smoothContext, SmoothModuleRegister moduleRegister);

}
