package org.misty.util.tool;

public class ThreadSleep {

    public static boolean withSecond(float second) {
        return withMillis((long) (second * 1000L));
    }

    public static boolean withMillis(long millis) {
        try {
            Thread.sleep(millis);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // reset interrupt state
            return false;
        }
    }

    public static boolean withRandomSecond(float maxSecond) {
        return withSecond((float) (Math.random() * maxSecond));
    }

    public static boolean withRandomMillis(long maxMillis) {
        return withMillis((long) (Math.random() * maxMillis));
    }

}
