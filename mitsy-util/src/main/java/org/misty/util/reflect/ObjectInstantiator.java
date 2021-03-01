package org.misty.util.reflect;

import org.misty.util.fi.FiSupplier;

import java.lang.reflect.Constructor;

public class ObjectInstantiator<TargetType> {

    private final Class<TargetType> openTableClass;

    public ObjectInstantiator(Class<TargetType> openTableClass) {
        if (openTableClass.isInterface() ||
                openTableClass.isEnum() ||
                openTableClass.isAnnotation() ||
                openTableClass.isArray()) {
            throw new UnsupportedOperationException(openTableClass + " can't be instance.");
        }
        this.openTableClass = openTableClass;
    }

    public Broker<TargetType> construct(Class<?>... parameterTypes) throws NoSuchMethodException {
        Constructor<TargetType> constructor = this.openTableClass.getConstructor(parameterTypes);
        return new Broker<TargetType>(constructor);
    }

    public Class<TargetType> getOpenTableClass() {
        return openTableClass;
    }

    public static class Broker<TargetType> {
        private final Constructor<TargetType> constructor;

        public Broker(Constructor<TargetType> constructor) {
            this.constructor = constructor;
        }

        public TargetType instance(Object... initargs) {
            FiSupplier<TargetType> action = () -> {
                return this.constructor.newInstance(initargs);
            };
            return action.getOrHandle();
        }
    }
}
