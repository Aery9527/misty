package org.misty.util.verify;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class Judge {

    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object o) {
        if (o == null) {
            return true;

        } else if (o instanceof String) {
            return isNullOrEmpty((String) o);

        } else if (o instanceof Collection) {
            return isNullOrEmpty((Collection) o);

        } else if (o instanceof Map) {
            return isNullOrEmpty((Map) o);

        } else if (o instanceof Object[]) {
            return isNullOrEmpty((Object[]) o);

        } else if (o instanceof Optional) {
            return isNullOrEmpty((Optional) o);

        } else {
            return false;
        }
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNullOrEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNullOrEmpty(Map<?, ?> m) {
        return m == null || m.isEmpty();
    }

    public static boolean isNullOrEmpty(Object[] a) {
        return a == null || a.length == 0;
    }

    public static boolean isNullOrEmpty(Optional<?> o) {
        if (o == null) {
            return true;
        }

        if (!o.isPresent()) {
            return true;
        }

        Object v = o.get();
        return isNullOrEmpty(v);
    }

    public static boolean notNullAndEmpty(Object o) {
        return !isNullOrEmpty(o);
    }

    public static boolean notNullAndEmpty(String s) {
        return !isNullOrEmpty(s);
    }

    public static boolean notNullAndEmpty(Collection<?> c) {
        return !isNullOrEmpty(c);
    }

    public static boolean notNullAndEmpty(Map<?, ?> m) {
        return !isNullOrEmpty(m);
    }

    public static boolean notNullAndEmpty(Object[] a) {
        return !isNullOrEmpty(a);
    }

    public static boolean notNullAndEmpty(Optional<?> o) {
        return !isNullOrEmpty(o);
    }

}
