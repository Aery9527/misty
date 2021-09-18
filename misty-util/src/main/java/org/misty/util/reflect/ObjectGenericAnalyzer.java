package org.misty.util.reflect;

import org.misty.util.reflect.vo.ObjectGenericDetail;
import org.misty.util.reflect.vo.ObjectGenericInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectGenericAnalyzer {

    private static class Analyzer {

        private final Class<?> actualType;

        private final TypeVariable<?> genericType;

        public Analyzer(Class<?> actualType) {
            this.actualType = actualType;
            this.genericType = null;
        }

        public Analyzer(TypeVariable<?> genericType) {
            this.actualType = null;
            this.genericType = genericType;
        }

    }

    private Map<Integer, Analyzer> map = new HashMap<>();

    private final Class<?> ownerClass;

    public ObjectGenericAnalyzer(Class<?> ownerClass) {
        this.ownerClass = ownerClass;
    }

    public ObjectGenericInfo analyze() {
        List<ObjectGenericDetail> genericDetails = new ArrayList<>();

        // TODO

        ObjectGenericInfo info = new ObjectGenericInfo(this.ownerClass, genericDetails.toArray(new ObjectGenericDetail[genericDetails.size()]));
        return info;
    }

    public void put(int i, Class<?> argument) {
        this.map.put(i, new Analyzer(argument));
    }

    public void put(int i, TypeVariable<?> argument) {
        this.map.put(i, new Analyzer(argument));

        String name = argument.getName();
        GenericDeclaration genericDeclaration = argument.getGenericDeclaration();
        AnnotatedType[] boundsAnnotatedTypes = argument.getAnnotatedBounds();
        Type[] boundsTypes = argument.getBounds();
        String typeName = argument.getTypeName();
        Annotation[] annotations = argument.getAnnotations();
        Annotation[] declaredAnnotations = argument.getDeclaredAnnotations();
    }

}
