package org.misty.util.ex;

import org.misty.util.fi.FiRunnable;
import org.misty.util.fi.FiSupplier;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx extends ReentrantLock {

    public ReentrantLockEx() {
    }

    public ReentrantLockEx(boolean fair) {
        super(fair);
    }

    public void use(FiRunnable action) {
        try {
            super.lock();
            action.runOrHandle();
        } finally {
            super.unlock();
        }
    }

    public <ReturnType> ReturnType use(FiSupplier<ReturnType> action) {
        try {
            super.lock();
            return action.getOrHandle();
        } finally {
            super.unlock();
        }
    }

}
