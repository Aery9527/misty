package org.misty.util.tool;

import java.util.Arrays;

public class StringTool {

    public static String toString(Object o) {
        if (o == null) {
            return null;
        }

        if (o.getClass().isArray()) {
            if (o instanceof short[]) {
                return Arrays.toString((short[]) o);
            } else if (o instanceof int[]) {
                return Arrays.toString((int[]) o);
            } else if (o instanceof long[]) {
                return Arrays.toString((long[]) o);
            } else if (o instanceof float[]) {
                return Arrays.toString((float[]) o);
            } else if (o instanceof double[]) {
                return Arrays.toString((double[]) o);
            } else if (o instanceof boolean[]) {
                return Arrays.toString((boolean[]) o);
            } else if (o instanceof char[]) {
                return Arrays.toString((char[]) o);
            } else if (o instanceof byte[]) {
                return Arrays.toString((byte[]) o);
            } else {
                return Arrays.toString((Object[]) o);
            }
        } else {
            return o.toString();
        }
    }

}
