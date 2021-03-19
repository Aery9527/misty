package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.context.SmoothActionRegister;
import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.service.SmoothService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface SmoothModuleLifecycle {

    String getName();

    String getVersion();

    /**
     * module attached message
     */
    default List<String> getAttachment() {
        return Collections.emptyList();
    }

    void initial(SmoothContext smoothContext, SmoothActionRegister actionRegister);

    void online();

    void destroy();

}