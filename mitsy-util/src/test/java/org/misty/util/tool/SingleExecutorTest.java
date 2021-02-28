package org.misty.util.tool;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
class SingleExecutorTest {

    @Spy
    private SingleExecutor singleExecutor;

    @Test
    public void test_single_entry_feature() throws InterruptedException {
        AtomicBoolean forkExecuted = new AtomicBoolean(false);
        AtomicReference<String> forkIgnored = new AtomicReference<>();
        AtomicReference<Boolean> forkReturned = new AtomicReference<Boolean>();
        AtomicBoolean mainExecuted = new AtomicBoolean(false);
        AtomicReference<String> mainIgnored = new AtomicReference<>();
        AtomicReference<Boolean> mainReturned = new AtomicReference<Boolean>();

        CountDownLatch latch = new CountDownLatch(2);

        String threadName = "9527";

        new Thread(() -> {
            boolean returned = this.singleExecutor.execute(() -> {
                ThreadTool.sleepWithSecond(1);
                forkExecuted.set(true);
                latch.countDown();
            }, (executingThreadName) -> {
                forkIgnored.set(executingThreadName);
                latch.countDown();
            });
            forkReturned.set(returned);
        }, threadName).start();

        ThreadTool.sleepWithSecond(0.5f);

        boolean returned = this.singleExecutor.execute(() -> {
            mainExecuted.set(true);
            latch.countDown();
        }, (executingThreadName) -> {
            mainIgnored.set(executingThreadName);
            latch.countDown();
        });
        mainReturned.set(returned);

        latch.await();

        Assertions.assertThat(forkExecuted.get()).isEqualTo(true);
        Assertions.assertThat(forkIgnored.get()).isEqualTo(null);
        Assertions.assertThat(forkReturned.get()).isNotNull().isEqualTo(true);
        Assertions.assertThat(mainExecuted.get()).isEqualTo(false);
        Assertions.assertThat(mainIgnored.get()).isEqualTo(threadName);
        Assertions.assertThat(mainReturned.get()).isNotNull().isEqualTo(false);
    }

}