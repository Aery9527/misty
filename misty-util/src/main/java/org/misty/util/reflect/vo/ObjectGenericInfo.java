package org.misty.util.reflect.vo;

import java.util.Optional;

public class ObjectGenericInfo {

    private final Class<?> ownerClass;

    private final ObjectGenericDetail[] genericDetails;

    public ObjectGenericInfo(Class<?> ownerClass, ObjectGenericDetail[] genericDetails) {
        this.ownerClass = ownerClass;
        this.genericDetails = genericDetails;
    }

    public Optional<ObjectGenericDetail> getGenericDetail(int index) {
        if (0 >= index && index < genericDetails.length) {
            return Optional.of(genericDetails[index]);
        } else {
            return Optional.empty();
        }
    }

    public Class<?> getOwnerClass() {
        return ownerClass;
    }

    public ObjectGenericDetail[] getGenericDetails() {
        return genericDetails;
    }

}
