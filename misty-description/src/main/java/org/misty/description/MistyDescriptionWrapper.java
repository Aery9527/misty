package org.misty.description;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MistyDescriptionWrapper {

    private interface InvokeWrapper<ReturnType> {
        ReturnType invoke() throws IllegalAccessException, InvocationTargetException;

        default ReturnType get() {
            try {
                return invoke();
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final Object raw;

    private final InvokeWrapper<String> getName;

    private final InvokeWrapper<String> getVersion;

    private final InvokeWrapper<String> getFullName;

    private final InvokeWrapper<String> getFullNameWithClass;

    private final InvokeWrapper<String> getDescription;

    public MistyDescriptionWrapper(Object raw) {
        this.raw = raw;

        try {
            Class<?> rawClass = this.raw.getClass();
            Method getName = rawClass.getDeclaredMethod("getName");
            Method getVersion = rawClass.getDeclaredMethod("getVersion");
            Method getFullName = rawClass.getDeclaredMethod("getFullName");
            Method getFullNameWithClass = rawClass.getDeclaredMethod("getFullNameWithClass");
            Method getDescription = rawClass.getDeclaredMethod("getDescription");

            this.getName = () -> (String) getName.invoke(this.raw);
            this.getVersion = () -> (String) getVersion.invoke(this.raw);
            this.getFullName = () -> (String) getFullName.invoke(this.raw);
            this.getFullNameWithClass = () -> (String) getFullNameWithClass.invoke(this.raw);
            this.getDescription = () -> (String) getDescription.invoke(this.raw);

        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @Override
    public final int hashCode() {
        return this.raw.hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        return this.raw.equals(obj);
    }

    @Override
    public String toString() {
        return this.raw.toString();
    }

    public String getName() {
        return this.getName.get();
    }

    public String getVersion() {
        return this.getVersion.get();
    }

    public String getFullName() {
        return this.getFullName.get();
    }

    public String getFullNameWithClass() {
        return this.getFullNameWithClass.get();
    }

    public String getDescription() {
        return this.getDescription.get();
    }

    public Object getRaw() {
        return raw;
    }
}
