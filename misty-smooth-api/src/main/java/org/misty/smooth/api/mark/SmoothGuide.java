package org.misty.smooth.api.mark;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.FIELD})
@Documented
public @interface SmoothGuide {

    class Domain {
        public static final String ANY = "ANY";
        public static final String CORE = "CORE";
        public static final String MANAGER = "MANAGER";
        public static final String MODULE = "MODULE";
    }

    /**
     * If need cross, the interface can't have default method, because Crosser may forget the wrap method because of the default method.
     *
     * @return true: Indicates the domain who implementation of the interface is different from the domain who uses the interface,
     * there are load by different ClassLoader. Therefore, Crosser is needed to replace thread's ContextClassLoader.
     */
    boolean needCross();

    String[] implementationBy();

    String[] usedBy();

}
