package org.misty.util.reflect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjectAncestorDiggerTest {

    @Test
    public void test_findSuperClassAncestor() {
        Class<?> classSuperA = SuperA.class;
        Class<?> classSuperB = SuperB.class;
        Class<?> classSuperC1 = SuperC1.class;
        Class<?> classSuperC2 = SuperC2.class;
        Class<?> classSuperD1 = SuperD1.class;
        Class<?> classSuperD2 = SuperD2.class;
        Class<?> classSuperE1 = SuperE1.class;
        Class<?> classSuperE2 = SuperE2.class;

        Assertions.assertThat(ObjectAncestorDigger.findSuperClassAncestor(classSuperA)).isEmpty();

        Assertions.assertThat(ObjectAncestorDigger.findSuperClassAncestor(classSuperB))
                .containsExactly(classSuperA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClassAncestor(classSuperC1))
                .containsExactly(classSuperB, classSuperA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassAncestor(classSuperC2))
                .containsExactly(classSuperB, classSuperA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClassAncestor(classSuperD1))
                .containsExactly(classSuperC1, classSuperB, classSuperA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassAncestor(classSuperD2))
                .containsExactly(classSuperC2, classSuperB, classSuperA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClassAncestor(classSuperE1))
                .containsExactly(classSuperD1, classSuperC1, classSuperB, classSuperA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassAncestor(classSuperE2))
                .containsExactly(classSuperD2, classSuperC2, classSuperB, classSuperA);
    }

    @Test
    public void test_findSuperClassGenericTypes() {
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(SuperA.class, Object.class)).isEmpty();

        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(SuperB.class, SuperA.class)).isEmpty();

        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(SuperC1.class, SuperB.class))
                .containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(SuperC2.class, SuperB.class))
                .containsExactly(String.class);

    }

    public static class SuperA {

    }

    public static class SuperB<Type1> extends SuperA {

    }

    public static class SuperC1 extends SuperB<String> {

    }

    public static class SuperC2<Type1> extends SuperB<Type1> {

    }

    public static class SuperD1<Type2> extends SuperC1 {

    }

    public static class SuperD2<Type1, Type2> extends SuperC2<Type1> {

    }

    public static class SuperE1 extends SuperD1<Integer> {

    }

    public static class SuperE2 extends SuperD2<Double, Float> {

    }


}