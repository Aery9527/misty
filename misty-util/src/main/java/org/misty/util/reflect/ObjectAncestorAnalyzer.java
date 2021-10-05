package org.misty.util.reflect;

import org.misty.util.fi.FiSupplier;
import sun.reflect.generics.repository.ClassRepository;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
            return Objects.equals(certainType, that.certainType) &&
                    Objects.equals(super.getOwnerClass(), that.getOwnerClass()) &&
                    Objects.equals(super.getIndex(), that.getIndex());
        }

        @Override
        public int hashCode() {
            return Objects.hash(certainType, super.getOwnerClass(), super.getIndex());
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
            return Objects.equals(typeVariable, that.typeVariable) &&
                    Objects.equals(super.getOwnerClass(), that.getOwnerClass()) &&
                    Objects.equals(super.getIndex(), that.getIndex());
        }

        @Override
        public int hashCode() {
            return Objects.hash(typeVariable, super.getOwnerClass(), super.getIndex());
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

    private static class Processor {
        private final Class<?> ownerClass;

        private final boolean superClass;

        private final List<GenericType> genericTypes = new ArrayList<>();

        public Processor(Class<?> ownerClass) {
            this.ownerClass = ownerClass;
            this.superClass = !ownerClass.isInterface();
        }

        public void add(Class<?> argument) {
            int index = genericTypes.size();
            this.genericTypes.add(new GenericCertainType(ownerClass, index, argument));
        }

        public void add(TypeVariable<?> argument) {
            int index = genericTypes.size();
            this.genericTypes.add(new GenericUncertainType(ownerClass, index, argument));
        }

        public Info analyze(Map<GenericType, GenericLink> genericRelationship) {
            boolean needFindGenericRelationship = this.genericTypes.stream()
                    .reduce(false, (result, genericType) -> {
                        return result || genericType.isUncertainType();
                    }, (result1, result2) -> result1 || result2);

            List<GenericType> generics;
            if (needFindGenericRelationship) {
                generics = withGenericRelationship(genericRelationship);
            } else {
                generics = this.genericTypes;
            }

            return new Info(this.ownerClass, Collections.unmodifiableList(generics));
        }

        protected List<GenericType> withGenericRelationship(Map<GenericType, GenericLink> genericRelationship) {
            List<GenericType> generics = new ArrayList<>();

            this.genericTypes.forEach(genericType -> {
                if (genericRelationship.containsKey(genericType)) {
                    GenericLink link = genericRelationship.get(genericType);
                    Class<?> ownerClass = genericType.getOwnerClass();
                    int index = genericType.getIndex();

                    GenericType to = link.getTo();
                    if (to.isCertainType()) {
                        Class<?> certainType = link.getTo().getCertainType();
                        generics.add(new GenericCertainType(ownerClass, index, certainType));
                    } else {
                        generics.add(genericType);
                    }
                } else {
                    generics.add(genericType);
                }
            });

            return generics;
        }

        public List<GenericType> getGenericTypes() {
            return Collections.unmodifiableList(this.genericTypes);
        }

        public Class<?> getOwnerClass() {
            return ownerClass;
        }

        public boolean isSuperClass() {
            return superClass;
        }
    }

    private static class GenericLink {
        private final GenericType from;

        private final GenericType to;

        public GenericLink(GenericType from, GenericType to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return GenericLink.class.getSimpleName() + "{" + this.from + "}-{" + this.to + "}";
        }

        public GenericType getFrom() {
            return from;
        }

        public GenericType getTo() {
            return to;
        }
    }

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

        Map<Class<?>, Processor> analyzeProcessors = new LinkedHashMap<>();
        analyze(target, analyzeProcessors);

        Map<GenericType, GenericLink> genericRelationship = buildGenericRelationship(analyzeProcessors);

        Map<Class<?>, Info> superClassesAnalyzeInfo = new LinkedHashMap<>();
        Map<Class<?>, Info> interfacesAnalyzeInfo = new HashMap<>();

        analyzeProcessors.forEach((type, processor) -> {
            Map<Class<?>, Info> targetAnalyzeInfo = processor.isSuperClass() ? superClassesAnalyzeInfo : interfacesAnalyzeInfo;
            Info info = processor.analyze(genericRelationship);
            targetAnalyzeInfo.put(type, info);
        });

        this.superClassesAnalyzeInfo = Collections.unmodifiableMap(superClassesAnalyzeInfo);
        this.interfacesAnalyzeInfo = Collections.unmodifiableMap(interfacesAnalyzeInfo);
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

    public Optional<Info> getGenericInfo(Class<?> searchClass) {
        if (searchClass.isInterface()) {
            return Optional.ofNullable(this.interfacesAnalyzeInfo.get(searchClass));
        } else {
            return Optional.ofNullable(this.superClassesAnalyzeInfo.get(searchClass));
        }
    }

    public Optional<GenericType> getGenericAtIndex(Class<?> searchClass, int index) {
        Optional<Info> infoOptional = getGenericInfo(searchClass);
        if (infoOptional.isPresent()) {
            Info info = infoOptional.get();
            List<GenericType> generics = info.getGenerics();
            if (index >= 0 && index < generics.size()) {
                return Optional.of(generics.get(index));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public Map<Class<?>, Info> getAllSuperClassAnalyzeInfo() {
        return this.superClassesAnalyzeInfo;
    }

    public Map<Class<?>, Info> getAllInterfaceAnalyzeInfo() {
        return this.interfacesAnalyzeInfo;
    }

    protected List<Class<?>> updateFirst(List<Class<?>> target, Map<Class<?>, Info> genericInfo) {
        return target == null ? Collections.unmodifiableList(new ArrayList<>(genericInfo.keySet())) : target;
    }

    protected void analyze(Class<?> target, Map<Class<?>, Processor> analyzeProcessors) {
        analyzeSuperClassGeneric(target, analyzeProcessors);
        analyzeInterfaceGeneric(target, analyzeProcessors);

        Class<?> superClass = target.getSuperclass();
        if (!superClass.equals(Object.class)) {
            analyze(superClass, analyzeProcessors);
        }
    }

    private void analyzeSuperClassGeneric(Class<?> target, Map<Class<?>, Processor> analyzeProcessors) {
        Class<?> superClass = target.getSuperclass();
        if (superClass.equals(Object.class)) {
            return;
        }

        Processor processor = new Processor(superClass);
        analyzeProcessors.put(superClass, processor);

        Type genericSuperclass = target.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return;
        }

        putIntoProcessor(processor, (ParameterizedType) genericSuperclass);
    }

    private void analyzeInterfaceGeneric(Class<?> target, Map<Class<?>, Processor> analyzeProcessors) {
        Type[] genericInterfaces = target.getGenericInterfaces();

        for (Type genericInterface : genericInterfaces) {
            Class<?> type;
            if (genericInterface instanceof ParameterizedType) {
                type = (Class<?>) ((ParameterizedType) genericInterface).getRawType();
            } else {
                type = (Class<?>) genericInterface;
            }

            Processor processor = new Processor(type);
            analyzeProcessors.put(type, processor);

            if (!(genericInterface instanceof ParameterizedType)) {
                continue;
            }

            putIntoProcessor(processor, (ParameterizedType) genericInterface);

            analyzeInterfaceGeneric(type, analyzeProcessors);
        }
    }

    protected void putIntoProcessor(Processor processor, ParameterizedType parameterizedType) {
        Type[] arguments = parameterizedType.getActualTypeArguments();
        for (Type argument : arguments) {
            if (argument instanceof Class) {
                processor.add((Class<?>) argument);
            } else {
                processor.add((TypeVariable<?>) argument);
            }
        }
    }

    protected Map<GenericType, GenericLink> buildGenericRelationship(Map<Class<?>, Processor> processorMap) {
        Map<GenericType, GenericLink> genericRelationship = new HashMap<>();

        Map<Class<?>, Map<Integer, GenericType>> allNodeMap = new HashMap<>();
        processorMap.values().forEach(processor -> {
            List<GenericType> genericTypes = processor.getGenericTypes();
            genericTypes.forEach(genericType -> {
                Class<?> ownerClass = genericType.getOwnerClass();
                int index = genericType.getIndex();
                allNodeMap.computeIfAbsent(ownerClass, (oc) -> new HashMap<>()).put(index, genericType);
            });
        });

        processorMap.values().forEach(processor -> {
            List<GenericType> genericTypes = processor.getGenericTypes();
            genericTypes.forEach(genericType -> {
                if (genericType.isUncertainType()) {
                    addLink(genericRelationship, allNodeMap, (GenericUncertainType) genericType);
                }
            });
        });

        return genericRelationship;
    }

    protected void addLink(Map<GenericType, GenericLink> genericRelationship,
                           Map<Class<?>, Map<Integer, GenericType>> allNodeMap,
                           GenericUncertainType genericUncertainType) {
        TypeVariable<?> typeVariable = genericUncertainType.typeVariable;
        String genericName = typeVariable.getName();

        int index = -1;
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        ClassRepository genericInfo = extractGenericInfo((Class<?>) genericDeclaration);
        TypeVariable<?>[] typeParameters = genericInfo.getTypeParameters();
        for (int i = 0; i < typeParameters.length; i++) {
            TypeVariable<?> typeVariable1 = typeParameters[i];
            if (genericName.equals(typeVariable1.getName())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            GenericType from = allNodeMap.get(genericUncertainType.getOwnerClass()).get(genericUncertainType.getIndex());
            Map<Integer, GenericType> nodeMap = allNodeMap.get(genericDeclaration);
            if (nodeMap != null) {
                GenericType to = allNodeMap.get(genericDeclaration).get(index);
                GenericLink genericLink = new GenericLink(from, to);
                genericRelationship.put(from, genericLink);
            }
        }
    }

    protected ClassRepository extractGenericInfo(Class<?> clazz) {
        FiSupplier<ClassRepository> extractor = () -> {
            Method method = Class.class.getDeclaredMethod("getGenericInfo");
            method.setAccessible(true);
            ClassRepository classRepository = (ClassRepository) method.invoke(clazz);
            method.setAccessible(false);
            return classRepository;
        };
        return extractor.getOrHandle();
    }

}
