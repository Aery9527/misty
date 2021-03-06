package org.misty.util.tool;

import org.misty.util.fi.FiSupplier;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class LazySupplier<Type> {

    private final AtomicReference<Supplier<Type>> supplier = new AtomicReference<>();

    public LazySupplier(FiSupplier<Type> supplier) {
        this(supplier, true);
    }

    public LazySupplier(FiSupplier<Type> supplier, boolean atomicity) {
        Runnable initialAction = () -> {
            Type type = supplier.getOrHandle();
            this.supplier.set(() -> type);
        };

        final AtomicBoolean setup = new AtomicBoolean(false);
        Runnable atomicityAction = atomicity ? () -> {
            synchronized (this.supplier) {
                if (setup.get()) {
                    return;
                }
                initialAction.run();
                setup.set(true);
            }
        } : initialAction;

        this.supplier.set(() -> {
            atomicityAction.run();
            return this.supplier.get().get();
        });
    }

    @Override
    public String toString() {
        return get().toString();
    }

    public Type get() {
        return this.supplier.get().get();
    }

}
