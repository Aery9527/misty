package org.misty.util.fi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FITest {

    /**
     * 展示FI相關介面如何使用
     */
    @Test
    public void test() {
        Thread mainThread = Thread.currentThread();
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThread.interrupt();
        }).start();

        FiRunnable action = () -> Thread.sleep(5000); // 這裡就不需要catch了, 於調用時會直接拋出

        Assertions.assertThatThrownBy(action::runOrHandle).isInstanceOf(InterruptedException.class);
    }

}