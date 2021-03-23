package org.misty.smooth.sole;

import org.misty.smooth.api.lifecycle.module.SmoothModuleRegister;
import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.sole.context.SmoothSoleRegister;
import org.misty.smooth.sole.context.SmoothSoleContext;

public class SmoothSoleApplication {

    public SmoothContext start() {
        SmoothSoleApplication soleSmoothApplication = new SmoothSoleApplication();
        soleSmoothApplication.launch();
        return soleSmoothApplication.getSmoothContext();
    }

    private SmoothModuleRegister actionRegister = new SmoothSoleRegister();

    private SmoothContext smoothContext = new SmoothSoleContext();

    public SmoothContext launch() {
        // TODO 模擬smooth環境運行
        return this.smoothContext;
    }

    public SmoothModuleRegister getActionRegister() {
        return actionRegister;
    }

    public void setActionRegister(SmoothModuleRegister actionRegister) {
        this.actionRegister = actionRegister;
    }

    public SmoothContext getSmoothContext() {
        return smoothContext;
    }

    public void setSmoothContext(SmoothContext smoothContext) {
        this.smoothContext = smoothContext;
    }

}
