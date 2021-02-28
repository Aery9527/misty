package org.misty.util.tool;

import java.util.Arrays;

public class StackFetcher {

    public static StackTraceElement currentStack() {
        return find(1);
    }

    public static StackTraceElement previousStack() {
        return find(2);
    }

    /**
     * @param offset 0表示呼叫者當下起算的stack, 1等於往上一個stack, 2表示再往上一個stack, ...依此類推
     * @return {@link StackTraceElement}
     */
    public static StackTraceElement find(int offset) {
        StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
        return steArray[offset + 2];
    }

    public static StackTraceElement findAndFilter(int offset) {
        return findAndFilter(offset + 1, StackFetcherFilter.presetFilterChain());
    }

    public static StackTraceElement findAndFilter(int offset, StackFetcherFilter filter) {
        StackTraceElement[] steArray = stacks(offset + 1);

        for (StackTraceElement ste : steArray) {
            if (filter.notMatch(ste)) {
                return ste;
            }
        }

        return null;
    }

    public static StackTraceElement[] stacks() {
        return stacks(1);
    }

    public static StackTraceElement[] stacks(int offset) {
        StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
        return Arrays.copyOfRange(steArray, offset + 2, steArray.length - 1);
    }

    public static StringBuilder toPrettyString(String prefix, String suffix, StackTraceElement... steArray) {
        StringBuilder sb = new StringBuilder();

        for (StackTraceElement ste : steArray) {
            sb.append(prefix).append(ste.toString()).append(suffix);
        }

        return sb;
    }

    public static class ClassFetcher {
        public static Class<?> fetch() {
            return fetch(1);
        }

        public static Class<?> fetchPrevious() {
            return fetch(2);
        }

        public static Class<?> fetch(int offset) {
            StackTraceElement ste = find(offset + 1);
            return fetch(ste);
        }

        public static Class<?> fetch(StackTraceElement ste) {
            try {
                String className = ste.getClassName();
                return Thread.currentThread().getContextClassLoader().loadClass(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class MethodFetcher {

        public static String fetchName() {
            return fetchName(1);
        }

        public static String fetchNamePrevious() {
            return fetchName(2);
        }

        public static String fetchName(int offset) {
            StackTraceElement ste = find(offset + 1);
            return fetchName(ste);
        }

        public static String fetchName(StackTraceElement ste) {
            return ste.getMethodName();
        }

        public static int fetchLine() {
            return fetchLine(1);
        }

        public static int fetchLinePrevious() {
            return fetchLine(2);
        }

        public static int fetchLine(int offset) {
            StackTraceElement ste = find(offset + 1);
            return fetchLine(ste);
        }

        public static int fetchLine(StackTraceElement ste) {
            return ste.getLineNumber();
        }
    }

}
