package org.misty.util.reflect;

import org.misty.util.reflect.vo.ObjectGenericInfo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class ObjectAncestorAnalyzer {

    public static ObjectAncestorAnalyzer analyze(Class<?> target) {
        return new ObjectAncestorAnalyzer(target);
    }

    private final Class<?> target;

    private final Map<Class<?>, ObjectGenericInfo> superClassesGenericInfo;

    private final Map<Class<?>, ObjectGenericInfo> interfacesGenericInfo;

    private final AtomicReference<List<Class<?>>> superClasses = new AtomicReference<>();

    private final AtomicReference<List<Class<?>>> interfaces = new AtomicReference<>();

    public ObjectAncestorAnalyzer(Class<?> target) {
        this.target = target;

        Map<Class<?>, ObjectGenericAnalyzer> superClassesGenericInfo = new LinkedHashMap<>();
        Map<Class<?>, ObjectGenericAnalyzer> interfacesGenericInfo = new HashMap<>();
        analyze(target, superClassesGenericInfo, interfacesGenericInfo);

        Function<Map<Class<?>, ObjectGenericAnalyzer>, Map<Class<?>, ObjectGenericInfo>> toGenericInfo = (map) -> {
            return Collections.unmodifiableMap(
                    map.entrySet().stream()
                            .reduce(new HashMap<>(), (result, entry) -> {
                                result.put(entry.getKey(), entry.getValue().analyze());
                                return result;
                            }, (result1, result2) -> null)
            );
        };

        this.superClassesGenericInfo = toGenericInfo.apply(superClassesGenericInfo);
        this.interfacesGenericInfo = toGenericInfo.apply(interfacesGenericInfo);
    }

    public Class<?> getTarget() {
        return target;
    }

    public List<Class<?>> getAllSuperClass() {
        return this.superClasses.updateAndGet((last) -> updateFirst(last, this.superClassesGenericInfo));
    }

    public List<Class<?>> getAllInterface() {
        return this.interfaces.updateAndGet((last) -> updateFirst(last, this.interfacesGenericInfo));
    }

//    public Optional<ObjectGenericInfo> getGenericInfo(Class<?> searchClass) {
//    }

//    public Optional<ObjectGenericDetail> getGenericAtIndex(Class<?> searchClass, int index) {
//    }

    public Map<Class<?>, ObjectGenericInfo> getAllSuperClassGenericInfo() {
        return this.superClassesGenericInfo;
    }

    public Map<Class<?>, ObjectGenericInfo> getAllInterfaceGenericInfo() {
        return this.interfacesGenericInfo;
    }

    protected List<Class<?>> updateFirst(List<Class<?>> target, Map<Class<?>, ObjectGenericInfo> genericInfo) {
        return target == null ? Collections.unmodifiableList(new ArrayList<>(genericInfo.keySet())) : target;
    }

    protected void analyze(Class<?> target,
                           Map<Class<?>, ObjectGenericAnalyzer> superClassesGenericInfo,
                           Map<Class<?>, ObjectGenericAnalyzer> interfacesGenericInfo) {
        analyzeSuperClass(target, superClassesGenericInfo);
        analyzeInterface(target, interfacesGenericInfo);

        Class<?> superclass = target.getSuperclass();
        if (!superclass.equals(Object.class)) {
            analyze(superclass, superClassesGenericInfo, interfacesGenericInfo);
        }
    }

    private void analyzeSuperClass(Class<?> target, Map<Class<?>, ObjectGenericAnalyzer> superClassesGenericInfo) {
        Class<?> superClass = target.getSuperclass();
        if (superClass.equals(Object.class)) {
            return;
        }

        ObjectGenericAnalyzer analyzer = new ObjectGenericAnalyzer(superClass);
        superClassesGenericInfo.put(superClass, analyzer);

        Type genericSuperclass = target.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return;
        }

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] arguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < arguments.length; i++) {
            Type argument = arguments[i];
            if (argument instanceof Class) {
                analyzer.put(i, (Class<?>) argument);
            } else {
                analyzer.put(i, (TypeVariable<?>) argument);
            }
        }
    }

    private void analyzeInterface(Class<?> target, Map<Class<?>, ObjectGenericAnalyzer> interfacesGenericInfo) {
        Class<?>[] interfaces = target.getInterfaces();
        Type[] genericInterfaces = target.getGenericInterfaces();

        System.out.println();
    }

}
