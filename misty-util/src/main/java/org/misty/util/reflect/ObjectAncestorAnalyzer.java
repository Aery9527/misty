package org.misty.util.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class ObjectAncestorAnalyzer {

    public static class Info {
        private final Class<?> ownerClass;

        private final List<GenericType> generics;

        public Info(Class<?> ownerClass, List<GenericType> generics) {
            this.ownerClass = ownerClass;
            this.generics = generics;
        }

        public Class<?> getOwnerClass() {
            return ownerClass;
        }

        public List<GenericType> getGenerics() {
            return generics;
        }
    }

    public static abstract class GenericType {
        private final Class<?> ownerClass;

        private final int index;

        public GenericType(Class<?> ownerClass, int index) {
            this.ownerClass = ownerClass;
            this.index = index;
        }

        public abstract boolean isCertainType();

        public boolean isUncertainType() {
            return !isCertainType();
        }

        public Class<?> getCertainType() {
            throw new UnsupportedOperationException(ownerClass + " generic type at index " + this.index + " is not certain type");
        }

        public String getUncertainName() {
            throw new UnsupportedOperationException(ownerClass + " generic type at index " + this.index + " is not uncertain type");
        }

        public Class<?> getOwnerClass() {
            return ownerClass;
        }

        public int getIndex() {
            return index;
        }
    }

    public static class GenericCertainType extends GenericType {

        private final Class<?> certainType;

        public GenericCertainType(Class<?> ownerClass, int index, Class<?> certainType) {
            super(ownerClass, index);
            this.certainType = certainType;
        }

        @Override
        public boolean isCertainType() {
            return true;
        }

        @Override
        public Class<?> getCertainType() {
            return this.certainType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GenericCertainType that = (GenericCertainType) o;
            return Objects.equals(certainType, that.certainType) && Objects.equals(that.getOwnerClass(), super.getOwnerClass());
        }

        @Override
        public int hashCode() {
            return Objects.hash(certainType, super.getOwnerClass());
        }

        @Override
        public String toString() {
            return GenericCertainType.class.getSimpleName() + "{" +
                    super.getOwnerClass().getName() +
                    "[" + super.getIndex() + "]" +
                    ":" + certainType +
                    '}';
        }
    }

    public static class GenericUncertainType extends GenericType {

        private final TypeVariable<?> typeVariable;

        public GenericUncertainType(Class<?> ownerClass, int index, TypeVariable<?> typeVariable) {
            super(ownerClass, index);
            this.typeVariable = typeVariable;
        }

        @Override
        public boolean isCertainType() {
            return false;
        }

        @Override
        public String getUncertainName() {
            return this.typeVariable.getName();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GenericUncertainType that = (GenericUncertainType) o;
            return Objects.equals(typeVariable, that.typeVariable) && Objects.equals(that.getOwnerClass(), super.getOwnerClass());
        }

        @Override
        public int hashCode() {
            return Objects.hash(typeVariable, super.getOwnerClass());
        }

        @Override
        public String toString() {
            return GenericUncertainType.class.getSimpleName() + "{" +
                    super.getOwnerClass().getName() +
                    "[" + super.getIndex() + "]" +
                    ":" + this.typeVariable +
                    '}';
        }
    }

    public static class Processor {
        private final Class<?> ownerClass;

        private final List<GenericType> genericTypes = new ArrayList<>();

        public Processor(Class<?> ownerClass) {
            this.ownerClass = ownerClass;
        }

        public void add(Class<?> argument) {
            int index = genericTypes.size();
            this.genericTypes.add(new GenericCertainType(ownerClass, index, argument));
        }

        public void add(TypeVariable<?> argument) {
            int index = genericTypes.size();
            this.genericTypes.add(new GenericUncertainType(ownerClass, index, argument));
        }

        public Info analyze(Map<Class<?>, Processor> processorMap) {
            System.out.println(this.ownerClass);
//            processorMap.entrySet().forEach(System.out::println);
            this.genericTypes.forEach(System.out::println);
            System.out.println();




            List<GenericType> generics = new ArrayList<>();
            // TODO
            return new Info(this.ownerClass, generics);
        }
    }

    //

    public static ObjectAncestorAnalyzer analyze(Class<?> target) {
        return new ObjectAncestorAnalyzer(target);
    }

    private final Class<?> target;

    private final Map<Class<?>, Info> superClassesAnalyzeInfo;

    private final Map<Class<?>, Info> interfacesAnalyzeInfo;

    private final AtomicReference<List<Class<?>>> superClasses = new AtomicReference<>();

    private final AtomicReference<List<Class<?>>> interfaces = new AtomicReference<>();

    public ObjectAncestorAnalyzer(Class<?> target) {
        this.target = target;

        Map<Class<?>, Processor> superClassesAnalyzeProcessor = new LinkedHashMap<>();
        Map<Class<?>, Processor> interfacesAnalyzeProcessor = new HashMap<>();
        analyze(target, superClassesAnalyzeProcessor, interfacesAnalyzeProcessor);

        Function<Map<Class<?>, Processor>, Map<Class<?>, Info>> toGenericInfo = (processorMap) -> {
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

    public Map<Class<?>, Info> getAllSuperClassAnalyzeInfo() {
        return this.superClassesAnalyzeInfo;
    }

    public Map<Class<?>, Info> getAllInterfaceAnalyzeInfo() {
        return this.interfacesAnalyzeInfo;
    }

    protected List<Class<?>> updateFirst(List<Class<?>> target, Map<Class<?>, Info> genericInfo) {
        return target == null ? Collections.unmodifiableList(new ArrayList<>(genericInfo.keySet())) : target;
    }

    protected void analyze(Class<?> target,
                           Map<Class<?>, Processor> superClassesAnalyzeProcessor,
                           Map<Class<?>, Processor> interfacesAnalyzeProcessor) {
        analyzeSuperClassGeneric(target, superClassesAnalyzeProcessor);
        analyzeInterfaceGeneric(target, interfacesAnalyzeProcessor);

        Class<?> superClass = target.getSuperclass();
        if (!superClass.equals(Object.class)) {
            analyze(superClass, superClassesAnalyzeProcessor, interfacesAnalyzeProcessor);
        }
    }

    private void analyzeSuperClassGeneric(Class<?> target, Map<Class<?>, Processor> superClassesAnalyzeProcessor) {
        Class<?> superClass = target.getSuperclass();
        if (superClass.equals(Object.class)) {
            return;
        }

        Processor processor = new Processor(superClass);
        superClassesAnalyzeProcessor.put(superClass, processor);

        Type genericSuperclass = target.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return;
        }

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] arguments = parameterizedType.getActualTypeArguments();
        for (Type argument : arguments) {
            if (argument instanceof Class) {
                processor.add((Class<?>) argument);
            } else {
                processor.add((TypeVariable<?>) argument);
            }
        }
    }

    private void analyzeInterfaceGeneric(Class<?> target, Map<Class<?>, Processor> interfacesAnalyzeProcessor) {
        Class<?>[] interfaces = target.getInterfaces();
        Type[] genericInterfaces = target.getGenericInterfaces();

        System.out.println();
    }

}
