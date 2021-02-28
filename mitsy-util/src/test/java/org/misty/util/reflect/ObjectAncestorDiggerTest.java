package org.misty.util.reflect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjectAncestorDiggerTest {

    @Test
    public void test_findSuperClasses() {
        Class<?> classSuperA = SuperA.class;
        Class<?> classSuperB = SuperB.class;
        Class<?> classSuperC1 = SuperC1.class;
        Class<?> classSuperC2 = SuperC2.class;
        Class<?> classSuperD1 = SuperD1.class;
        Class<?> classSuperD2 = SuperD2.class;
        Class<?> classSuperE1 = SuperE1.class;
        Class<?> classSuperE2 = SuperE2.class;

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(classSuperA)).isEmpty();

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(classSuperB))
                .containsExactly(classSuperA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(classSuperC1))
                .containsExactly(classSuperB, classSuperA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(classSuperC2))
                .containsExactly(classSuperB, classSuperA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(classSuperD1))
                .containsExactly(classSuperC1, classSuperB, classSuperA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(classSuperD2))
                .containsExactly(classSuperC2, classSuperB, classSuperA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(classSuperE1))
                .containsExactly(classSuperD1, classSuperC1, classSuperB, classSuperA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(classSuperE2))
                .containsExactly(classSuperD2, classSuperC2, classSuperB, classSuperA);
    }

    @Test
    public void test_findSuperClassGenericTypes() {
        Class<SuperA> classSuperA = SuperA.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperA, Object.class)).isEmpty();

        Class<SuperB> classSuperB = SuperB.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperB, SuperA.class)).isEmpty();

        Class<SuperC1> classSuperC1 = SuperC1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperC1, SuperB.class))
                .containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperC1, SuperA.class)).isEmpty();

        Class<SuperC2> classSuperC2 = SuperC2.class;
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericTypes(classSuperC2, SuperB.class);
        }).isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperC2, SuperA.class)).isEmpty();

        Class<SuperD1> classSuperD1 = SuperD1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperD1, SuperC1.class)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperD1, SuperB.class))
                .containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperD1, SuperA.class)).isEmpty();

        Class<SuperD2> classSuperD2 = SuperD2.class;
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericTypes(classSuperD2, SuperC2.class);
        }).isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericTypes(classSuperD2, SuperB.class);
        }).isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperD2, SuperA.class)).isEmpty();

        Class<SuperE1> classSuperE1 = SuperE1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperE1, SuperD1.class))
                .containsExactly(Integer.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperE1, SuperC1.class)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperE1, SuperB.class))
                .containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperE1, SuperA.class)).isEmpty();

        Class<SuperE2> classSuperE2 = SuperE2.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperE2, SuperD2.class))
                .containsExactly(Double.class, Float.class);
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericTypes(classSuperE2, SuperC2.class);
        }).isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericTypes(classSuperE2, SuperB.class);
        }).isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(classSuperE2, SuperA.class)).isEmpty();
    }

    @Test
    public void test_findSuperClassGenericType() {
        Class<SuperA> classSuperA = SuperA.class;
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericType(classSuperA, Object.class, -1))
                .isInstanceOf(IndexOutOfBoundsException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperA, Object.class, 0)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperA, Object.class, 1)).isEmpty();

        Class<SuperB> classSuperB = SuperB.class;
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericType(classSuperB, SuperA.class, -1))
                .isInstanceOf(IndexOutOfBoundsException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperB, SuperA.class, 0)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperB, SuperA.class, 1)).isEmpty();

        Class<SuperC1> classSuperC1 = SuperC1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperC1, SuperB.class, 0))
                .get().isEqualTo(String.class);

        Class<SuperC2> classSuperC2 = SuperC2.class;
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericType(classSuperC2, SuperB.class, 0);
        }).isInstanceOf(UnsupportedOperationException.class);

        Class<SuperD1> classSuperD1 = SuperD1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperD1, SuperB.class, 0))
                .get().isEqualTo(String.class);

        Class<SuperD2> classSuperD2 = SuperD2.class;
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericType(classSuperD2, SuperB.class, 0);
        }).isInstanceOf(UnsupportedOperationException.class);

        Class<SuperE1> classSuperE1 = SuperE1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperE1, SuperD1.class, 0))
                .get().isEqualTo(Integer.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperE1, SuperB.class, 0))
                .get().isEqualTo(String.class);

        Class<SuperE2> classSuperE2 = SuperE2.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperE2, SuperD2.class, 0))
                .get().isEqualTo(Double.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(classSuperE2, SuperD2.class, 1))
                .get().isEqualTo(Float.class);
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericType(classSuperE2, SuperB.class, 0);
        }).isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericType(classSuperE2, SuperB.class, 1);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void test_findInterfaceGenericTypes() {

    }

    @Test
    public void test_findInterfaceGenericType() {

    }

    //

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

    //

    public interface _A {
    }

    public interface _B<Type1> extends _A {
    }

    public interface _C1 extends _B<String> {
    }

    public interface _C2<Type1> extends _B<Type1> {
    }

    public interface _D1<Type2> extends _C1 {
    }

    public interface _D2<Type1, Type2> extends _C2<Type1> {
    }

    public interface $A {
    }

    public interface $B<Type1> extends $A {
    }

    public interface $C1 extends $B<String> {
    }

    public interface $C2<Type1> extends $B<Type1> {
    }

    public interface $D1<Type2> extends $C1 {
    }

    public interface $D2<Type1, Type2> extends $C2<Type1> {
    }

    public static class Implement1 implements _B<String>, $B<Integer> {
    }

    public static class Implement2 implements _C1, $C2<Integer> {
    }

    public static class Implement3 implements _D1<Double>, $D2<String, Integer> {
    }

}