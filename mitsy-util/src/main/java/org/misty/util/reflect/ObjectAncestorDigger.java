package org.misty.util.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ObjectAncestorDigger {

    public static List<Class<?>> findSuperClasses(Class<?> targetClass) {
        List<Class<?>> list = new ArrayList<>();
        findSuperClasses(targetClass, list);
        return list;
    }

    public static <T> List<Class<?>> findSuperClassGenericTypes(Class<? extends T> targetClass, Class<T> superClass) throws UnsupportedOperationException {
        try {
            return searchSuperClassGenericTypes(targetClass, superClass);
        } catch (ErrorGenericType errorGenericType) {
            throw new UnsupportedOperationException(targetClass.getName() + " super class " + superClass.getName() + " generic type error.", errorGenericType);
        }
    }

    public static <T> Optional<Class<?>> findSuperClassGenericType(Class<? extends T> targetClass, Class<T> superClass, int index) throws UnsupportedOperationException {
        List<Class<?>> types = findSuperClassGenericTypes(targetClass, superClass);
        if (index >= types.size()) {
            return Optional.empty();
        } else {
            return Optional.of(types.get(index));
        }
    }

    public static <T> List<Class<?>> findInterfaceGenericTypes(Class<? extends T> targetClass, Class<T> interfaceClass) throws UnsupportedOperationException {
        try {
            Optional<List<Class<?>>> result = searchInterfaceGenericTypes(targetClass, interfaceClass);
            return result.orElse(Collections.emptyList());
        } catch (ErrorGenericType errorGenericType) {
            throw new UnsupportedOperationException(targetClass.getName() + " interface " + interfaceClass.getName() + " generic type error.", errorGenericType);
        }
    }

    public static <T> Optional<Class<?>> findInterfaceGenericType(Class<? extends T> targetClass, Class<T> interfaceClass, int index) throws UnsupportedOperationException {
        List<Class<?>> types = findInterfaceGenericTypes(targetClass, interfaceClass);
        if (index >= types.size()) {
            return Optional.empty();
        } else {
            return Optional.of(types.get(index));
        }
    }

    private static <T> List<Class<?>> searchSuperClassGenericTypes(Class<? extends T> targetClass, Class<T> superClass) throws ErrorGenericType {
        if (targetClass.equals(Object.class)) {
            return Collections.emptyList();
        }

        Type genericSuperclass = targetClass.getGenericSuperclass();
        if (genericSuperclass == null) {
            return Collections.emptyList();
        }

        boolean isParameterizedType = false;
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type rawType = parameterizedType.getRawType();
            if (rawType.equals(superClass)) {
                return findGenerics(genericSuperclass);
            } else {
                isParameterizedType = true;
            }
        }

        if (isParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type rawType = parameterizedType.getRawType();
            return searchSuperClassGenericTypes((Class<? extends T>) rawType, superClass);
        } else {
            return searchSuperClassGenericTypes((Class<? extends T>) genericSuperclass, superClass);
        }
    }

    private static Optional<List<Class<?>>> searchInterfaceGenericTypes(Class<?> targetClass, Class<?> interfaceClass) throws ErrorGenericType {
        Type[] genericInterfaces = targetClass.getGenericInterfaces();

        Optional<List<Class<?>>> result = Optional.empty();
        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof Class) {
                result = searchInterfaceGenericTypes((Class<?>) genericInterface, interfaceClass);
                if (result.isPresent()) {
                    return result;
                } else {
                    continue;
                }
            }

            ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
            Type rawType = parameterizedType.getRawType();
            if (rawType.equals(interfaceClass)) {
                List<Class<?>> list = findGenerics(genericInterface);
                return Optional.of(list);
            }

            result = searchInterfaceGenericTypes((Class<?>) rawType, interfaceClass);

            if (result.isPresent()) {
                return result;
            }
        }

        return result;
    }

    private static List<Class<?>> findGenerics(Type type) throws ErrorGenericType {
        if (!(type instanceof ParameterizedType)) {
            return Collections.emptyList();
        }

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] generics = parameterizedType.getActualTypeArguments();

        List<Class<?>> result = new ArrayList<>();
        for (Type generic : generics) {
            if (generic instanceof Class) {
                result.add((Class<?>) generic);
            } else {
                throw new ErrorGenericType(parameterizedType, generic);
            }
        }
        return result;
    }

    private static void findSuperClasses(Class<?> targetClass, List<Class<?>> list) {
        Class<?> superClass = targetClass.getSuperclass();

        if (superClass == null || superClass.equals(Object.class)) {
            return;
        }

        list.add(superClass);
        findSuperClasses(superClass, list);
    }

    private static class ErrorGenericType extends Exception {

        private static String mixMsg(ParameterizedType classType, Type genericType) {
            return "the generic type <" + genericType.toString() + "> of " + classType.getRawType() + " is not clearly class from classloader.";
        }

        private final ParameterizedType classType;

        private final Type genericType;

        public ErrorGenericType(ParameterizedType classType, Type genericType) {
            super(mixMsg(classType, genericType));
            this.classType = classType;
            this.genericType = genericType;
        }

        public ParameterizedType getClassType() {
            return classType;
        }

        public Type getGenericType() {
            return genericType;
        }
    }

}
