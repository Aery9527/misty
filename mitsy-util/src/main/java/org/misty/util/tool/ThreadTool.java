package org.misty.util.tool;

import java.util.Random;

public class ThreadTool {

    private static final Random RANDOM = new Random();

    public static void sleepWithSecond(float second) {
        sleep((long) (second * 1000L));
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // not thing to do
        }
    }

    public static void sleepRandomWithSecond(float second) {
        sleepWithSecond((float) (Math.random() * second));
    }

    public static void sleepRandom(long maxMillis) {
        sleep((long) (Math.random() * maxMillis));
    }

    public static String getCurrentName() {
        return Thread.currentThread().getName();
    }
}
