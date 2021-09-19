package org.misty.util.reflect;

import org.misty.util.reflect.vo.ObjectAnalyzeInfo;

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

    private final Map<Class<?>, ObjectAnalyzeInfo> superClassesAnalyzeInfo;

    private final Map<Class<?>, ObjectAnalyzeInfo> interfacesAnalyzeInfo;

    private final AtomicReference<List<Class<?>>> superClasses = new AtomicReference<>();

    private final AtomicReference<List<Class<?>>> interfaces = new AtomicReference<>();

    public ObjectAncestorAnalyzer(Class<?> target) {
        this.target = target;

        Map<Class<?>, ObjectAncestorAnalyzeProcessor> superClassesAnalyzeProcessor = new LinkedHashMap<>();
        Map<Class<?>, ObjectAncestorAnalyzeProcessor> interfacesAnalyzeProcessor = new HashMap<>();
        analyze(target, superClassesAnalyzeProcessor, interfacesAnalyzeProcessor);

        Function<Map<Class<?>, ObjectAncestorAnalyzeProcessor>, Map<Class<?>, ObjectAnalyzeInfo>> toGenericInfo = (processorMap) -> {
            return Collections.unmodifiableMap(
                    processorMap.entrySet().stream()
                            .reduce(new HashMap<>(), (result, entry) -> {
                                result.put(entry.getKey(), entry.getValue().analyze(processorMap));
                                return result;
                            }, (result1, result2) -> null)
            );
        };

        this.superClassesAnalyzeInfo = toGenericInfo.apply(superClassesAnalyzeProcessor);
        this.interfacesAnalyzeInfo = toGenericInfo.apply(interfacesAnalyzeProcessor);
    }

    public Class<?> getTarget() {
        return target;
    }

    public List<Class<?>> getAllSuperClass() {
        return this.superClasses.updateAndGet((last) -> updateFirst(last, this.superClassesAnalyzeInfo));
    }

    public List<Class<?>> getAllInterface() {
        return this.interfaces.updateAndGet((last) -> updateFirst(last, this.interfacesAnalyzeInfo));
    }

//    public Optional<ObjectGenericInfo> getGenericInfo(Class<?> searchClass) {
//    }

//    public Optional<ObjectGenericDetail> getGenericAtIndex(Class<?> searchClass, int index) {
//    }

    public Map<Class<?>, ObjectAnalyzeInfo> getAllSuperClassAnalyzeInfo() {
        return this.superClassesAnalyzeInfo;
    }

    public Map<Class<?>, ObjectAnalyzeInfo> getAllInterfaceAnalyzeInfo() {
        return this.interfacesAnalyzeInfo;
    }

    protected List<Class<?>> updateFirst(List<Class<?>> target, Map<Class<?>, ObjectAnalyzeInfo> genericInfo) {
        return target == null ? Collections.unmodifiableList(new ArrayList<>(genericInfo.keySet())) : target;
    }

    protected void analyze(Class<?> target,
                           Map<Class<?>, ObjectAncestorAnalyzeProcessor> superClassesAnalyzeProcessor,
                           Map<Class<?>, ObjectAncestorAnalyzeProcessor> interfacesAnalyzeProcessor) {
        analyzeSuperClassGeneric(target, superClassesAnalyzeProcessor);
        analyzeInterfaceGeneric(target, interfacesAnalyzeProcessor);

        Class<?> superclass = target.getSuperclass();
        if (!superclass.equals(Object.class)) {
            analyze(superclass, superClassesAnalyzeProcessor, interfacesAnalyzeProcessor);
        }
    }

    private void analyzeSuperClassGeneric(Class<?> target, Map<Class<?>, ObjectAncestorAnalyzeProcessor> superClassesAnalyzeProcessor) {
        Class<?> superClass = target.getSuperclass();
        if (superClass.equals(Object.class)) {
            return;
        }

        ObjectAncestorAnalyzeProcessor analyzeProcessor = new ObjectAncestorAnalyzeProcessor(target, superClass);
        superClassesAnalyzeProcessor.put(superClass, analyzeProcessor);

        Type genericSuperclass = target.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return;
        }

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] arguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < arguments.length; i++) {
            Type argument = arguments[i];
            if (argument instanceof Class) {
                analyzeProcessor.add((Class<?>) argument);
            } else {
                analyzeProcessor.add(new TypeVariableDecorator<>((TypeVariable<?>) argument, parameterizedType));
            }
        }
    }

    private void analyzeInterfaceGeneric(Class<?> target, Map<Class<?>, ObjectAncestorAnalyzeProcessor> interfacesAnalyzeProcessor) {
        Class<?>[] interfaces = target.getInterfaces();
        Type[] genericInterfaces = target.getGenericInterfaces();

        System.out.println();
    }

}
