package org.misty.smooth.api.lifecycle;

import java.util.Collections;
import java.util.Set;

public interface SmoothLifecycle {

    String getName();

    String getVersion();

    /**
     * module attached message
     */
    default Set<String> getAttachment() {
        return Collections.emptySet();
    }

    default void online() {
    }

    default void destroy() {
    }

}
