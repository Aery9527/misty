package org.misty.util.reflect.vo;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public abstract class ObjectGenericDetail implements AnnotatedElement {

    public boolean isClearlyType() {
        return false;
    }

    public boolean isFuzzyType() {
        return false;
    }

    public abstract Class<?> getActualType();

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return withAnnotatedElement().getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return withAnnotatedElement().getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return withAnnotatedElement().getDeclaredAnnotations();
    }

    protected abstract AnnotatedElement withAnnotatedElement();

}
