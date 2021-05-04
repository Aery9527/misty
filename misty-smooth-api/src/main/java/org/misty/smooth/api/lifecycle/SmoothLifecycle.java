package org.misty.smooth.api.lifecycle;

import org.misty.smooth.api.mark.NeedCross;

import java.util.Collections;
import java.util.Set;

@NeedCross(
        implementation = {NeedCross.Scope.MANAGER, NeedCross.Scope.MODULE},
        usedBy = NeedCross.Scope.CORE
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
