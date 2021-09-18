package org.misty.util.reflect.vo;

import java.lang.reflect.AnnotatedElement;

public class ObjectGenericClearly extends ObjectGenericDetail {

    private final Class<?> type;

    public ObjectGenericClearly(Class<?> type) {
        this.type = type;
    }

    @Override
    public boolean isClearlyType() {
        return true;
    }

    @Override
    public Class<?> getActualType() {
        return this.type;
    }

    @Override
    public AnnotatedElement withAnnotatedElement() {
        return this.type;
    }

}
