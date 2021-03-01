package org.misty.util.tool;

import java.util.Random;

public class ThreadSleep {

    private static final Random RANDOM = new Random();

    public static void withSecond(float second) {
        withMillis((long) (second * 1000L));
    }

    public static void withMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // not thing to do
        }
    }

    public static void withRandomSecond(float maxSecond) {
        withSecond((float) (Math.random() * maxSecond));
    }

    public static void withRandomMillis(long maxMillis) {
        withMillis((long) (Math.random() * maxMillis));
    }

}
