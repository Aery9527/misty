package org.misty.smooth.api.mark;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface NeedCross {

    enum Scope {
        ANY, CORE, MANAGER, MODULE
    }

    Scope[] implementation();

    Scope[] usedBy();

}
