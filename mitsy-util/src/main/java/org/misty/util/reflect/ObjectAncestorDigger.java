package org.misty.util.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ObjectAncestorDigger {

    @SuppressWarnings("unchecked")
    public static <T> List<Class<?>> findSuperClassGenericTypes(Class<? extends T> targetClass, Class<T> superClass) {
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
            return findSuperClassGenericTypes((Class<? extends T>) rawType, superClass);
        } else {
            return findSuperClassGenericTypes((Class<? extends T>) genericSuperclass, superClass);
        }
    }

    private static <T> Optional<Class<?>> findSuperClassGenericType(Class<? extends T> targetClass, Class<T> superClass, int index) {
        List<Class<?>> types = findSuperClassGenericTypes(targetClass, superClass);
        if (index >= types.size()) {
            return Optional.empty();
        } else {
            return Optional.of(types.get(index));
        }
    }

    public static List<Class<?>> findInterfaceGenericTypes(Class<?> targetClass, Class<?> interfaceClass) {
        Type[] genericInterfaces = targetClass.getGenericInterfaces();

        for (Type genericInterface : genericInterfaces) {
            if (!(genericInterface instanceof ParameterizedType)) {
                continue;
            }

            ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
            Type rawType = parameterizedType.getRawType();
            if (rawType.equals(interfaceClass)) {
                return findGenerics(genericInterface);
            }
        }

        return Collections.emptyList();
    }

    public static Optional<Class<?>> findInterfaceGenericType(Class<?> targetClass, Class<?> interfaceClass, int index) {
        Type[] genericInterfaces = targetClass.getGenericInterfaces();

        for (Type genericInterface : genericInterfaces) {
            if (!(genericInterface instanceof ParameterizedType)) {
                continue;
            }

            ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
            Type rawType = parameterizedType.getRawType();
            if (rawType.equals(interfaceClass)) {
                return findGeneric(genericInterface, index);
            }
        }

        return Optional.empty();
    }

    private static List<Class<?>> findGenerics(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return Collections.emptyList();
        }

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] generics = parameterizedType.getActualTypeArguments();

        List<Class<?>> result = new ArrayList<>();
        for (Type generic : generics) {
            result.add((Class<?>) generic);
        }
        return result;
    }

    private static Optional<Class<?>> findGeneric(Type type, int index) {
        if (!(type instanceof ParameterizedType)) {
            return Optional.empty();
        }

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] generics = parameterizedType.getActualTypeArguments();

        if (index >= generics.length) {
            return Optional.empty();
        }

        return Optional.of((Class<?>) generics[index]);
    }

    public static List<Class<?>> findSuperClassAncestor(Class<?> targetClass) {
        List<Class<?>> list = new ArrayList<>();
        findSuperClassAncestor(targetClass, list);
        return list;
    }

    private static void findSuperClassAncestor(Class<?> targetClass, List<Class<?>> list) {
        Class<?> superClass = targetClass.getSuperclass();

        if (superClass == null || superClass.equals(Object.class)) {
            return;
        }

        list.add(superClass);
        findSuperClassAncestor(superClass, list);
    }

}
