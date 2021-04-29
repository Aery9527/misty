package org.misty.util.tool;

@FunctionalInterface
public interface StackFetcherFilter {

    static StackFetcherFilter presetFilterChain() {
        StackFetcherFilter filter = filterJavaReflectInvoke();
        filter = mixFilterChain(filter, filterJavaProxy());
        filter = mixFilterChain(filter, filterSpringAOP());

        return filter;
    }

    static StackFetcherFilter mixFilterChain(StackFetcherFilter previousFilter, StackFetcherFilter nextFilter) {
        return (ste) -> {
            boolean notMatch = previousFilter.notMatch(ste);
            return notMatch && nextFilter.notMatch(ste);
        };
    }

    static StackFetcherFilter filterJavaReflectInvoke() {
        return (ste) -> {
            String className = ste.getClassName();
            return !className.equals("sun.reflect.NativeMethodAccessorImpl") &&
                    !className.equals("sun.reflect.DelegatingMethodAccessorImpl") &&
                    !className.equals("java.lang.reflect.Method")
                    ;
        };
    }

    static StackFetcherFilter filterJavaProxy() {
        return (ste) -> {
            String className = ste.getClassName();
            return !className.startsWith("com.sun.proxy.$Proxy");
        };
    }

    /**
     * TODO 之後記得搬到spring的工具裡去
     *
     * @return
     */
    static StackFetcherFilter filterSpringAOP() {
        return (ste) -> {
            String className = ste.getClassName();
            return !className.startsWith("org.springframework.aop.support") &&
                    !className.startsWith("org.springframework.aop.framework")
                    ;
        };
    }

    boolean notMatch(StackTraceElement ste);

}
