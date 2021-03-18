package org.misty.smooth.api.lifecycle;

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

    Map<String, SmoothService> initial();

    void online();

    void offline();

    void destroy();

}
