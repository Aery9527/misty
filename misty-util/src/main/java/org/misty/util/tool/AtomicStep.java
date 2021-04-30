package org.misty.util.tool;

import org.misty.util.ex.ReentrantLockEx;
import org.misty.util.verify.Examiner;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class AtomicStep<HoldType> {

    private final ReentrantLockEx lock = new ReentrantLockEx();

    private HoldType holded;

    public AtomicStep(HoldType holded) {
        Examiner.refuseNullAndEmpty("holded", holded);
        this.holded = holded;
    }

    public HoldType to(UnaryOperator<HoldType> changeAction) {
        Examiner.refuseNullAndEmpty("holded", holded);

        AtomicReference<HoldType> oldHolded = new AtomicReference<>();
        this.holded = this.lock.use(() -> {
            HoldType old = this.holded;
            oldHolded.set(old);

            HoldType newHolded = changeAction.apply(old);
            Examiner.refuseNullAndEmpty("newHolded", newHolded);
            return newHolded;
        });

        return oldHolded.get();
    }

    public HoldType get() {
        return holded;
    }

}
