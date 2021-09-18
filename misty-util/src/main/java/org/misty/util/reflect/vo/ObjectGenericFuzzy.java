package org.misty.util.reflect.vo;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.TypeVariable;

public class ObjectGenericFuzzy extends ObjectGenericDetail {

    private final TypeVariable<?> type;

    public ObjectGenericFuzzy(TypeVariable<?> type) {
        this.type = type;
    }

    @Override
    public boolean isFuzzyType() {
        return true;
    }

    @Override
    public Class<?> getActualType() {
        throw new RuntimeException(); // FIXME
    }

    @Override
    public AnnotatedElement withAnnotatedElement() {
        return this.type;
    }


}
