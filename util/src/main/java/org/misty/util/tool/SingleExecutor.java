package org.misty.util.tool;

import org.misty.util.ex.ReentrantLockEx;
import org.misty.util.fi.FiRunnable;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * 確保同一時間只有一個thread執行邏輯, 若同時間有其餘的thread要執行則不阻塞直接跳過
 */
public class SingleExecutor {

    private final ReentrantLockEx lock = new ReentrantLockEx(true);

    private volatile String executingThreadName;

    public boolean execute(FiRunnable executeAction) {
        return execute(executeAction, (lastExecutingThreadName) -> {
        });
    }

    public boolean execute(FiRunnable executeAction, Consumer<String> ignoreAction) {
        BooleanSupplier ignoreActionCheck = () -> {
            boolean ignore = this.executingThreadName != null;
            if (ignore) {
                ignoreAction.accept(this.executingThreadName);
            }
            return ignore;
        };

        if (ignoreActionCheck.getAsBoolean()) {
            return false;
        }

        boolean ignore = this.lock.use(() -> {
            if (ignoreActionCheck.getAsBoolean()) {
                return true;
            } else {
                this.executingThreadName = Thread.currentThread().getName();
                return false;
            }
        });

        if (ignore) {
            return false;
        }

        try { // only one thread do this at same time
            executeAction.runOrHandle();
            return true;
        } finally {
            this.executingThreadName = null;
        }
    }

    public String getExecutingThreadName() {
        return executingThreadName;
    }
}
