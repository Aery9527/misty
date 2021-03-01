package org.misty.util.reflect;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Constructor;

public class ObjectInstantiator<TargetType> {

    private final Class<TargetType> target;

    public ObjectInstantiator(Class<TargetType> target) {
        if (target.isInterface() ||
                target.isEnum() ||
                target.isAnnotation() ||
                target.isArray()) {
            throw new UnsupportedOperationException(target + " can't be instance.");
        }
        this.target = target;
    }

    public Broker<TargetType> construct(Class<?>... parameterTypes) throws NoSuchMethodException {
        Constructor<TargetType> constructor = this.target.getConstructor(parameterTypes);
        return new Broker<>(constructor);
    }

    public Class<TargetType> getTarget() {
        return target;
    }

    public static class Broker<TargetType> {
        private final Constructor<TargetType> constructor;

        public Broker(Constructor<TargetType> constructor) {
            this.constructor = constructor;
        }

        public TargetType instance(Object... initargs) {
            FiSupplier<TargetType> action = () -> this.constructor.newInstance(initargs);
            return action.getOrHandle();
        }
    }
}
