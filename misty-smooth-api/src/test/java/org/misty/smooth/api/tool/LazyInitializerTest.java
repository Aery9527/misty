package org.misty.smooth.api.tool;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.util.fi.FiConsumer;
import org.misty.util.tool.ThreadSleep;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class LazyInitializerTest {

    @Test
    void supplierInvokeOneTimes() {
        String result = "kerker";

        AtomicInteger checkPoint = new AtomicInteger(0);

        LazyInitializer<String> lazyInitializer = new LazyInitializer<>(() -> { // here only execute 1 times
            checkPoint.incrementAndGet();
            return result;
        });

        Assertions.assertThat(lazyInitializer.get()).isEqualTo(result);
        Assertions.assertThat(lazyInitializer.get()).isEqualTo(result);
        Assertions.assertThat(checkPoint.get()).isEqualTo(1);
    }

    @Test
    void atomicity_true() {
        AtomicInteger checkPoint = new AtomicInteger(0);
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(2);

        LazyInitializer<String> lazyInitializer = new LazyInitializer<>(() -> { // here only execute 1 times
            ThreadSleep.withMillis(100);
            checkPoint.incrementAndGet();
            return "9527";
        }, true);

        FiConsumer<CountDownLatch> await = CountDownLatch::await;

        Runnable testAction = () -> {
            await.acceptOrHandle(latch1);
            lazyInitializer.get();
            latch2.countDown();
        };
        new Thread(testAction).start();
        new Thread(testAction).start();

        ThreadSleep.withMillis(100);

        latch1.countDown();
        await.acceptOrHandle(latch2);

        Assertions.assertThat(checkPoint.get()).isEqualTo(1);
    }

    @Test
    void atomicity_false() {
        AtomicInteger checkPoint = new AtomicInteger(0);
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(2);

        LazyInitializer<String> lazyInitializer = new LazyInitializer<>(() -> { // here may not execute only 1 times
            ThreadSleep.withMillis(100);
            checkPoint.incrementAndGet();
            return "9527";
        }, false);

        FiConsumer<CountDownLatch> await = CountDownLatch::await;

        Runnable testAction = () -> {
            await.acceptOrHandle(latch1);
            lazyInitializer.get();
            latch2.countDown();
        };
        new Thread(testAction).start();
        new Thread(testAction).start();

        ThreadSleep.withMillis(100);

        latch1.countDown();
        await.acceptOrHandle(latch2);

        Assertions.assertThat(checkPoint.get()).isEqualTo(2);
    }

}
