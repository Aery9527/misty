package org.misty.smooth.sole;

import org.misty.smooth.api.context.SmoothActionRegister;
import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.lifecycle.SmoothModuleLifecycle;
import org.misty.smooth.sole.context.SoleSmoothActionRegister;
import org.misty.smooth.sole.context.SoleSmoothContext;

public class SoleSmoothApplication {

    public SmoothContext start() {
        SoleSmoothApplication soleSmoothApplication = new SoleSmoothApplication();
        soleSmoothApplication.launch();
        return soleSmoothApplication.getSmoothContext();
    }

    private SmoothActionRegister actionRegister = new SoleSmoothActionRegister();

    private SmoothContext smoothContext = new SoleSmoothContext();

    public SmoothContext launch() {
        // TODO 模擬smooth環境運行
        return this.smoothContext;
    }

    public SmoothActionRegister getActionRegister() {
        return actionRegister;
    }

    public void setActionRegister(SmoothActionRegister actionRegister) {
        this.actionRegister = actionRegister;
    }

    public SmoothContext getSmoothContext() {
        return smoothContext;
    }

    public void setSmoothContext(SmoothContext smoothContext) {
        this.smoothContext = smoothContext;
    }

}
