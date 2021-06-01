package org.misty.util.tool;

import org.misty.util.fi.FiSupplier;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class LazySupplier<Type> {

    private final AtomicReference<Supplier<Type>> supplier = new AtomicReference<>();

    private final boolean atomicity;

    private volatile boolean setup = false;

    public LazySupplier(FiSupplier<Type> supplier) {
        this(supplier, true);
    }

    public LazySupplier(FiSupplier<Type> supplier, boolean atomicity) {
        Runnable initialAction = () -> {
            Type type = supplier.getOrHandle();
            this.supplier.set(() -> type);
        };

        Runnable atomicityAction = atomicity ? () -> {
            synchronized (this.supplier) {
                if (setup) {
                    return;
                }
                initialAction.run();
                setup = true;
            }
        } : initialAction;

        this.supplier.set(() -> {
            atomicityAction.run();
            return this.supplier.get().get();
        });
        this.atomicity = atomicity;
    }

    Type get() {
        return this.supplier.get().get();
    }

}
