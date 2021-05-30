package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.mark.Guide;

import java.util.Collections;
import java.util.Set;

@Guide(
        implementationBy = {Guide.Scope.MANAGER, Guide.Scope.MODULE},
        usedBy = Guide.Scope.CORE
)
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
