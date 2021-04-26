package org.misty.smooth.api.vo;

public interface SmoothId<ComparableType extends SmoothId<ComparableType>> extends Comparable<ComparableType> {

    String getTypeKey();

}
