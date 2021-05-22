package org.misty.util.tool;

import org.misty.util.ex.ReentrantLockEx;
import org.misty.util.verify.Examiner;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class AtomicUpdater<TargetType> {

    private final ReentrantLockEx lock = new ReentrantLockEx();

    private final Consumer<TargetType> targetChecker;

    private final AtomicReference<TargetType> target = new AtomicReference<>();

    public AtomicUpdater(TargetType target) {
        this(target, (newTarget) -> {
            Examiner.refuseNullAndEmpty("target", newTarget);
        });
    }

    public AtomicUpdater(TargetType target, Consumer<TargetType> targetChecker) {
        Examiner.refuseNullAndEmpty("targetChecker", targetChecker);
        targetChecker.accept(target);

        this.target.set(target);
        this.targetChecker = targetChecker;
    }

    public TargetType update(UnaryOperator<TargetType> changeAction) {
        Examiner.refuseNullAndEmpty("changeAction", changeAction);

        AtomicReference<TargetType> oldHolder = new AtomicReference<>();
        this.lock.use(() -> {
            TargetType oldTarget = this.target.get();
            oldHolder.set(oldTarget);

            TargetType newTarget = changeAction.apply(oldTarget);
            this.targetChecker.accept(newTarget);
            this.target.set(newTarget);
        });

        return oldHolder.get();
    }

    public TargetType get() {
        return target.get();
    }

}
