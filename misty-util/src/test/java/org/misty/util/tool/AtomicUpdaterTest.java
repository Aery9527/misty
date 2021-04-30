package org.misty.util.tool;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

class AtomicUpdaterTest {

    @Test
    void update() throws InterruptedException {
        String a = "aaa";
        String b = "bbb";
        String c = "ccc";
        long gap = 100;

        AtomicUpdater<String> atomicUpdater = new AtomicUpdater<>(a);

        Consumer<CountDownLatch> await = (latch) -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(2);

        AtomicReference<String> checkPoint1 = new AtomicReference<>();
        AtomicReference<Long> checkPoint2 = new AtomicReference<>();
        AtomicReference<String> checkPoint3 = new AtomicReference<>();
        AtomicReference<String> checkPoint4 = new AtomicReference<>();
        AtomicReference<Long> checkPoint5 = new AtomicReference<>();
        AtomicReference<String> checkPoint6 = new AtomicReference<>();

        new Thread(() -> {
            try {
                String old = atomicUpdater.update((s) -> {
                    checkPoint1.set(s);
                    latch1.countDown();
                    checkPoint2.set(System.currentTimeMillis());
                    ThreadSleep.withMillis(gap);
                    return b;
                });
                checkPoint3.set(old);
            } finally {
                latch2.countDown();
            }
        }).start();

        new Thread(() -> {
            try {
                await.accept(latch1);

                String old = atomicUpdater.update((s) -> {
                    checkPoint4.set(s);
                    ThreadSleep.withMillis(gap);
                    checkPoint5.set(System.currentTimeMillis());
                    return c;
                });
                checkPoint6.set(old);
            } finally {
                latch2.countDown();
            }
        }).start();

        latch2.await();

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(a);
        Assertions.assertThat(checkPoint3.get()).isNotNull().isEqualTo(a);
        Assertions.assertThat(checkPoint4.get()).isNotNull().isEqualTo(b);
        Assertions.assertThat(checkPoint6.get()).isNotNull().isEqualTo(b);
        Assertions.assertThat(atomicUpdater.get()).isNotNull().isEqualTo(c);

        Assertions.assertThat(checkPoint2.get()).isNotNull();
        Assertions.assertThat(checkPoint5.get()).isNotNull();
        Assertions.assertThat(checkPoint5.get() - checkPoint2.get()).isGreaterThan(gap);
    }

    @Test
    void targetChecker() {
        Consumer<Integer> targetChecker = (newTarget) -> {
            if (newTarget < 0) {
                throw new RuntimeException();
            }
        };

        Assertions.assertThatThrownBy(() -> new AtomicUpdater<>(-1, targetChecker))
                .isInstanceOf(RuntimeException.class);

        AtomicUpdater<Integer> atomicUpdater = new AtomicUpdater<>(100, targetChecker);
        Assertions.assertThatThrownBy(() -> atomicUpdater.update((oldTarget) -> -1))
                .isInstanceOf(RuntimeException.class);
    }

}
