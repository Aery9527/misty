package org.misty.util.tool;

public class ThreadSleep {

    public static void withSecond(float second) {
        withMillis((long) (second * 1000L));
    }

    public static void withMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // reset interrupt state
        }
    }

    public static void withRandomSecond(float maxSecond) {
        withSecond((float) (Math.random() * maxSecond));
    }

    public static void withRandomMillis(long maxMillis) {
        withMillis((long) (Math.random() * maxMillis));
    }

}
