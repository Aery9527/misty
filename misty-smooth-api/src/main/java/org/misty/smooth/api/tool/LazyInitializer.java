package org.misty.smooth.api.tool;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class LazyInitializer<Type> {

    private final AtomicReference<Supplier<Type>> supplier = new AtomicReference<>();

    public LazyInitializer(Supplier<Type> supplier) {
        this(supplier, true);
    }

    public LazyInitializer(Supplier<Type> supplier, boolean atomicity) {
        Runnable initialAction = () -> {
            Type type = supplier.get();
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
