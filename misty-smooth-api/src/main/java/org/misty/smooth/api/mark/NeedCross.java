package org.misty.smooth.api.mark;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface NeedCross {

    class Scope {
        public static final String ANY = "ANY";
        public static final String CORE = "CORE";
        public static final String MANAGER = "MANAGER";
        public static final String MODULE = "MODULE";
    }

    String[] implementation();

    String[] user();

}
