package org.misty.util.tool;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.util.fi.FiConsumer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class LazySupplierTest {

    @Test
    void supplierInvokeOneTimes() {
        String result = "kerker";

        AtomicInteger checkPoint = new AtomicInteger(0);

        LazySupplier<String> lazySupplier = new LazySupplier<>(() -> { // here only execute 1 times
            checkPoint.incrementAndGet();
            return result;
        });

        Assertions.assertThat(lazySupplier.get()).isEqualTo(result);
        Assertions.assertThat(lazySupplier.get()).isEqualTo(result);
        Assertions.assertThat(checkPoint.get()).isEqualTo(1);
    }

    @Test
    void atomicity_true() {
        AtomicInteger checkPoint = new AtomicInteger(0);
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(2);

        LazySupplier<String> lazySupplier = new LazySupplier<>(() -> { // here only execute 1 times
            ThreadSleep.withMillis(100);
            checkPoint.incrementAndGet();
            return "9527";
        });

        FiConsumer<CountDownLatch> await = CountDownLatch::await;

        new Thread(() -> {
            await.acceptOrHandle(latch1);
            lazySupplier.get();
            latch2.countDown();
        }).start();

        new Thread(() -> {
            await.acceptOrHandle(latch1);
            lazySupplier.get();
            latch2.countDown();
        }).start();

        latch1.countDown();
        await.acceptOrHandle(latch2);

        Assertions.assertThat(checkPoint.get()).isEqualTo(1);
    }

    @Test
    void atomicity_false() {
        AtomicInteger checkPoint = new AtomicInteger(0);
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(2);

        LazySupplier<String> lazySupplier = new LazySupplier<>(() -> { // here only execute 1 times
            ThreadSleep.withMillis(100);
            checkPoint.incrementAndGet();
            return "9527";
        }, false);

        FiConsumer<CountDownLatch> await = CountDownLatch::await;

        new Thread(() -> {
            await.acceptOrHandle(latch1);
            lazySupplier.get();
            latch2.countDown();
        }).start();

        new Thread(() -> {
            await.acceptOrHandle(latch1);
            lazySupplier.get();
            latch2.countDown();
        }).start();

        latch1.countDown();
        await.acceptOrHandle(latch2);

        Assertions.assertThat(checkPoint.get()).isEqualTo(2);
    }

}
