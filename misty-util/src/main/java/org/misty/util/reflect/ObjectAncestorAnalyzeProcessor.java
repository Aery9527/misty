package org.misty.util.reflect;

public class ObjectAncestorAnalyzeProcessor {

//    private static class Analyzer {
//
//        private final List<GenericLink> links = new ArrayList<>();
//
//        private final Class<?> actualType;
//
//        private final TypeVariable<?> genericType;
//
//        public Analyzer(Class<?> actualType) {
//            this.actualType = actualType;
//            this.genericType = null;
//        }
//
//        public Analyzer(TypeVariable<?> genericType) {
//            this.actualType = null;
//            this.genericType = genericType;
//        }
//
//        public boolean isActualType() {
//            return this.actualType != null;
//        }
//    }
//
//    private static class GenericLink {
//
//        private Class<?> subclass;
//
//        private int subclassGenericIndex;
//
//        private Class<?> extendClass;
//
//        private int extendClassGenericIndex;
//    }
//
//    private final Class<?> subclass;
//
//    private final Class<?> ancestorClass;
//
//    private List<Analyzer> analyzers = new ArrayList<>();
//
//    private ObjectGenericDetail genericDetail;
//
//    public ObjectAncestorAnalyzeProcessor(Class<?> subclass, Class<?> ancestorClass) {
//        this.subclass = subclass;
//        this.ancestorClass = ancestorClass;
//    }
//
//    public ObjectAnalyzeInfo analyze(Map<Class<?>, ObjectAncestorAnalyzeProcessor> processorMap) {
////        ObjectAncestorAnalyzeProcessor processor = processorMap.get(ownerClass);
//
//        List<ObjectGenericDetail> genericDetails = new ArrayList<>(this.analyzers.size());
//
//        for (Analyzer analyzer : this.analyzers) {
//            if (analyzer.isActualType()) {
//                genericDetails.add(new ObjectGenericClearly(analyzer.actualType));
//                continue;
//            }
//
////            GenericLink link = new GenericLink();
////            setupSubclassGenericInfo(link, );
//
//            String name = analyzer.genericType.getName();
//
//            Integer index = null;
//
//            GenericDeclaration genericDeclaration = analyzer.genericType.getGenericDeclaration();
//            ClassRepository genericInfo = extractGenericInfo((Class<?>) genericDeclaration);
//            TypeVariable<?>[] typeParameters = genericInfo.getTypeParameters();
//            for (int i = 0; i < typeParameters.length; i++) {
//                TypeVariable<?> typeVariable1 = typeParameters[i];
//                if (name.equals(typeVariable1.getName())) {
//                    index = i;
//                    break;
//                }
//            }
//
//
//        }
//
//        ObjectAnalyzeInfo info = new ObjectAnalyzeInfo(this.subclass, genericDetails.toArray(new ObjectGenericDetail[genericDetails.size()]));
//        return info;
//    }
//
//    public void add(Class<?> argument) {
//        this.analyzers.add(new Analyzer(argument));
//    }
//
//    public void add(TypeVariable<?> argument) {
//        this.analyzers.add(new Analyzer(argument));
//    }
//
//    private ClassRepository extractGenericInfo(Class<?> clazz) {
//
//        FiSupplier<ClassRepository> extractor = () -> {
//            MethodExtractor getGenericInfo = new MethodExtractor(Class.class, clazz);
//            MethodArg0ReturnInvoker<ClassRepository> methodInvoker = getGenericInfo.buildObjectInvoker("getGenericInfo", ClassRepository.class);
//            return methodInvoker.invoke();
//
////            subclass.getDeclaredMethod()
//
////            FieldExtractor genericInfoExtractor = new FieldExtractor(subclass);
////            FieldObjectGetter<ClassRepository> genericInfoGetter = genericInfoExtractor.buildGetter("genericInfo", ClassRepository.class);
////            return genericInfoGetter.get();
//        };
//        return extractor.getOrHandle();
//    }

}
