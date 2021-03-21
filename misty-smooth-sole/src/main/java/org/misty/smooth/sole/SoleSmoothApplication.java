package org.misty.smooth.sole;

import org.misty.smooth.api.context.SmoothActionRegister;
import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.SmoothModuleLifecycle;
import org.misty.smooth.sole.context.SoleSmoothActionRegister;
import org.misty.smooth.sole.context.SoleSmoothContext;

public class SoleSmoothApplication {

    public static void start(Class<SmoothModuleLifecycle> moduleLifecycleClass) {
        new SoleSmoothApplication().launch(moduleLifecycleClass);
    }

    private SmoothActionRegister actionRegister = new SoleSmoothActionRegister();

    private SmoothContext smoothContext = new SoleSmoothContext();

    public SmoothContext launch(Class<SmoothModuleLifecycle> moduleLifecycleClass) {
        // TODO 模擬smooth環境運行
        return this.smoothContext;
    }

}
