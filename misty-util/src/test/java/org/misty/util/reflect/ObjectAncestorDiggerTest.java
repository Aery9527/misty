package org.misty.util.reflect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjectAncestorDiggerTest {

    @Test
    public void findSuperClasses() {
        Class<?> superA = SuperA.class;
        Class<?> superB = SuperB.class;
        Class<?> superC1 = SuperC1.class;
        Class<?> superC2 = SuperC2.class;
        Class<?> superD1 = SuperD1.class;
        Class<?> superD2 = SuperD2.class;
        Class<?> superE1 = SuperE1.class;
        Class<?> superE2 = SuperE2.class;

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(superA)).isEmpty();

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(superB)).containsExactly(superA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(superC1)).containsExactly(superB, superA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(superC2)).containsExactly(superB, superA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(superD1)).containsExactly(superC1, superB, superA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(superD2)).containsExactly(superC2, superB, superA);

        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(superE1)).containsExactly(superD1, superC1, superB, superA);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClasses(superE2)).containsExactly(superD2, superC2, superB, superA);
    }

    @Test
    public void findSuperClassGenericTypes() {
        Class<SuperA> superA = SuperA.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superA, Object.class)).isEmpty();

        Class<SuperB> superB = SuperB.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superB, SuperA.class)).isEmpty();

        Class<SuperC1> superC1 = SuperC1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superC1, SuperB.class)).containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superC1, SuperA.class)).isEmpty();

        Class<SuperC2> superC2 = SuperC2.class;
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericTypes(superC2, SuperB.class))
                .isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superC2, SuperA.class)).isEmpty();

        Class<SuperD1> superD1 = SuperD1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superD1, SuperC1.class)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superD1, SuperB.class))
                .containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superD1, SuperA.class)).isEmpty();

        Class<SuperD2> superD2 = SuperD2.class;
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericTypes(superD2, SuperC2.class))
                .isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericTypes(superD2, SuperB.class))
                .isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superD2, SuperA.class)).isEmpty();

        Class<SuperE1> superE1 = SuperE1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superE1, SuperD1.class)).containsExactly(Integer.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superE1, SuperC1.class)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superE1, SuperB.class)).containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superE1, SuperA.class)).isEmpty();

        Class<SuperE2> superE2 = SuperE2.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superE2, SuperD2.class)).containsExactly(Double.class, Float.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericTypes(superE2, SuperC2.class))
                .isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericTypes(superE2, SuperB.class))
                .isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericTypes(superE2, SuperA.class)).isEmpty();
    }

    @Test
    public void findSuperClassGenericType() {
        Class<SuperA> superA = SuperA.class;
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericType(superA, Object.class, -1))
                .isInstanceOf(IndexOutOfBoundsException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superA, Object.class, 0)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superA, Object.class, 1)).isEmpty();

        Class<SuperB> superB = SuperB.class;
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findSuperClassGenericType(superB, SuperA.class, -1))
                .isInstanceOf(IndexOutOfBoundsException.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superB, SuperA.class, 0)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superB, SuperA.class, 1)).isEmpty();

        Class<SuperC1> superC1 = SuperC1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superC1, SuperB.class, 0))
                .get().isEqualTo(String.class);

        Class<SuperC2> superC2 = SuperC2.class;
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericType(superC2, SuperB.class, 0);
        }).isInstanceOf(UnsupportedOperationException.class);

        Class<SuperD1> superD1 = SuperD1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superD1, SuperB.class, 0))
                .get().isEqualTo(String.class);

        Class<SuperD2> superD2 = SuperD2.class;
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericType(superD2, SuperB.class, 0);
        }).isInstanceOf(UnsupportedOperationException.class);

        Class<SuperE1> superE1 = SuperE1.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superE1, SuperD1.class, 0))
                .get().isEqualTo(Integer.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superE1, SuperB.class, 0))
                .get().isEqualTo(String.class);

        Class<SuperE2> superE2 = SuperE2.class;
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superE2, SuperD2.class, 0))
                .get().isEqualTo(Double.class);
        Assertions.assertThat(ObjectAncestorDigger.findSuperClassGenericType(superE2, SuperD2.class, 1))
                .get().isEqualTo(Float.class);
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericType(superE2, SuperB.class, 0);
        }).isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> { // 當子類未在宣告時定義父類泛型型別則無法取得
            ObjectAncestorDigger.findSuperClassGenericType(superE2, SuperB.class, 1);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void findInterfaceGenericTypes() {
        Class<Implement1> implement1 = Implement1.class;
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement1, _A.class)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement1, _B.class)).containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement1, $A.class)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement1, $B.class)).containsExactly(Integer.class);

        Class<Implement2> implement2 = Implement2.class;
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement2, _C1.class)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement2, _B.class)).containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement2, $C2.class)).containsExactly(Integer.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findInterfaceGenericTypes(implement2, $B.class))
                .isInstanceOf(UnsupportedOperationException.class);

        Class<Implement3> implement3 = Implement3.class;
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement3, _D1.class)).containsExactly(Double.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement3, _C1.class)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement3, _B.class)).containsExactly(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericTypes(implement3, $D2.class)).containsExactly(String.class, Integer.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findInterfaceGenericTypes(implement3, $C2.class))
                .isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findInterfaceGenericTypes(implement3, $B.class))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void findInterfaceGenericType() {
        Class<Implement1> implement1 = Implement1.class;
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement1, _A.class, 0)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement1, _A.class, 1)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement1, _B.class, 0)).get().isEqualTo(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement1, _B.class, 1)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement1, $A.class, 0)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement1, $B.class, 0)).get().isEqualTo(Integer.class);

        Class<Implement2> implement2 = Implement2.class;
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement2, _C1.class, 0)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement2, _B.class, 0)).get().isEqualTo(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement2, $C2.class, 0)).get().isEqualTo(Integer.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findInterfaceGenericType(implement2, $B.class, 0))
                .isInstanceOf(UnsupportedOperationException.class);

        Class<Implement3> implement3 = Implement3.class;
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement3, _D1.class, 0)).get().isEqualTo(Double.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement3, _C1.class, 0)).isEmpty();
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement3, _B.class, 0)).get().isEqualTo(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement3, $D2.class, 0)).get().isEqualTo(String.class);
        Assertions.assertThat(ObjectAncestorDigger.findInterfaceGenericType(implement3, $D2.class, 1)).get().isEqualTo(Integer.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findInterfaceGenericType(implement3, $C2.class, 0))
                .isInstanceOf(UnsupportedOperationException.class);
        Assertions.assertThatThrownBy(() -> ObjectAncestorDigger.findInterfaceGenericType(implement3, $B.class, 0))
                .isInstanceOf(UnsupportedOperationException.class);
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
