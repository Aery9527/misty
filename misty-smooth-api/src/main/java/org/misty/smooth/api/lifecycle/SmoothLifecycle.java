package org.misty.smooth.api.lifecycle;

import java.util.Collections;
import java.util.List;

public interface SmoothLifecycle {

    String getName();

    String getVersion();

    /**
     * module attached message
     */
    default List<String> getAttachment() {
        return Collections.emptyList();
    }

    default void online() {
    }

    default void destroy() {
    }

}
