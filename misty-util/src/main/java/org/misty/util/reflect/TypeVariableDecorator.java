package org.misty.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class TypeVariableDecorator<D extends GenericDeclaration> implements TypeVariable<D> {

    private final TypeVariable<D> raw;

    private final String description;

    public TypeVariableDecorator(TypeVariable<D> raw, ParameterizedType parameterizedType) {
        this.raw = raw;
        this.description = raw + " from " + parameterizedType;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public Type[] getBounds() {
        return this.raw.getBounds();
    }

    @Override
    public D getGenericDeclaration() {
        return this.raw.getGenericDeclaration();
    }

    @Override
    public String getName() {
        return this.raw.getName();
    }

    @Override
    public AnnotatedType[] getAnnotatedBounds() {
        return this.raw.getAnnotatedBounds();
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return this.raw.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return this.raw.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return this.raw.getDeclaredAnnotations();
    }

}
