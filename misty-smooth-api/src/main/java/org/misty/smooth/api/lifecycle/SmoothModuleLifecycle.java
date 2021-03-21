package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.context.SmoothActionRegister;
import org.misty.smooth.api.context.SmoothContext;

import java.util.Collections;
import java.util.List;

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

    default void online() {
    }

    default void destroy() {
    }

}
