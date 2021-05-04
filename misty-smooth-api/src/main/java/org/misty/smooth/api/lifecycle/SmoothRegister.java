package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.error.SmoothActionRegisterException;
import org.misty.smooth.api.mark.NeedCross;

import java.util.concurrent.ExecutorService;

@NeedCross(
        implementation = NeedCross.Scope.CORE,
        usedBy = {NeedCross.Scope.MANAGER, NeedCross.Scope.MODULE}
)
public interface SmoothRegister {

    void registerExecutor(ExecutorService executor) throws SmoothActionRegisterException;

}
