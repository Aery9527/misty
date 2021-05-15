package org.misty.util.reflect.method;

import java.lang.reflect.Method;
import java.util.Optional;

public interface MethodInvoker {

    Method getMethod();

    Optional<Object> getTarget();

    MethodStyle getMethodStyle();

}
