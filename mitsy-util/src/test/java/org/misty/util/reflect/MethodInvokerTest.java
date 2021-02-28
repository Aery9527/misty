package org.misty.util.reflect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;

class MethodInvokerTest {

    @Test
    public void test_static_void_method_invoke() throws NoSuchMethodException {
        AtomicInteger checkPoint1 = new AtomicInteger(0);
        AtomicInteger checkPoint2 = new AtomicInteger(0);

        TestTargetOfVoidMethod.STATIC_METHOD1 = checkPoint1::getAndIncrement;
        TestTargetOfVoidMethod.STATIC_METHOD2 = checkPoint2::set;

        MethodInvoker methodInvoker = new MethodInvoker(TestTargetOfVoidMethod.class);

        MethodInvoker.VoidMethod staticMethod1 = methodInvoker.find("staticMethod");
        staticMethod1.invoke();
        Assertions.assertThat(checkPoint1.get()).isEqualTo(1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(0);

        int tester = 9527;
        MethodInvoker.VoidMethod staticMethod2 = methodInvoker.find("staticMethod", int.class);
        staticMethod2.invoke(tester);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(tester);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find("kerker", int.class);
        }).isInstanceOf(NoSuchMethodException.class);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find("staticMethod", String.class);
        }).isInstanceOf(NoSuchMethodException.class)
                .hasMessageContaining(MethodInvoker.CANT_FIND_VOID_METHOD_MSG);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find("instanceMethod");
        }).isInstanceOf(NoSuchMethodException.class)
                .hasMessageContaining(MethodInvoker.CANT_FIND_STATIC_METHOD_MSG);
    }

    @Test
    public void test_instance_void_method_invoke() throws NoSuchMethodException {
        AtomicInteger checkPoint1 = new AtomicInteger(0);
        AtomicInteger checkPoint2 = new AtomicInteger(0);

        TestTargetOfVoidMethod target = new TestTargetOfVoidMethod(checkPoint1::getAndIncrement, checkPoint2::set);

        MethodInvoker methodInvoker = new MethodInvoker(target);

        MethodInvoker.VoidMethod instanceMethod1 = methodInvoker.find("instanceMethod");
        instanceMethod1.invoke();
        Assertions.assertThat(checkPoint1.get()).isEqualTo(1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(0);

        int tester = 9527;
        MethodInvoker.VoidMethod instanceMethod2 = methodInvoker.find("instanceMethod", int.class);
        instanceMethod2.invoke(tester);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(tester);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find("kerker", int.class);
        }).isInstanceOf(NoSuchMethodException.class);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find("instanceMethod", String.class);
        }).isInstanceOf(NoSuchMethodException.class)
                .hasMessageContaining(MethodInvoker.CANT_FIND_VOID_METHOD_MSG);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find("staticMethod");
        }).isInstanceOf(NoSuchMethodException.class)
                .hasMessageContaining(MethodInvoker.CANT_FIND_INSTANCE_METHOD_MSG);
    }

    @Test
    public void test_static_return_method_invoke() throws NoSuchMethodException {
        AtomicInteger checkPoint1 = new AtomicInteger(0);
        AtomicInteger checkPoint2 = new AtomicInteger(0);

        String tester1 = "5566";
        int tester2 = 9527;
        TestTargetOfReturnMethod.STATIC_METHOD1 = () -> {
            checkPoint1.getAndIncrement();
            return tester1;
        };
        TestTargetOfReturnMethod.STATIC_METHOD2 = (i) -> {
            checkPoint2.set(i);
            return tester2 + i;
        };

        MethodInvoker methodInvoker = new MethodInvoker(TestTargetOfReturnMethod.class);

        MethodInvoker.ReturnMethod<String> staticMethod1 = methodInvoker.find(String.class, "staticMethod");
        Assertions.assertThat(staticMethod1.invoke()).isEqualTo(tester1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(0);

        int tester = 9527;
        MethodInvoker.ReturnMethod<Integer> staticMethod2 = methodInvoker.find(Integer.class, "staticMethod", int.class);
        Assertions.assertThat(staticMethod2.invoke(tester)).isEqualTo(tester + tester2);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(tester);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find(String.class, "kerker", int.class);
        }).isInstanceOf(NoSuchMethodException.class);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find(String.class, "staticMethod", String.class);
        }).isInstanceOf(NoSuchMethodException.class)
                .hasMessageContaining(String.format(MethodInvoker.CANT_FIND_RETURNED_METHOD_MSG, String.class.getName()));
        methodInvoker.find(ArrayList.class, "staticMethod", double.class); // is ok
        methodInvoker.find(List.class, "staticMethod", double.class); // is ok

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find(String.class, "instanceMethod");
        }).isInstanceOf(NoSuchMethodException.class)
                .hasMessageContaining(MethodInvoker.CANT_FIND_STATIC_METHOD_MSG);
    }

    @Test
    public void test_instance_return_method_invoke() throws NoSuchMethodException {
        AtomicInteger checkPoint1 = new AtomicInteger(0);
        AtomicInteger checkPoint2 = new AtomicInteger(0);

        String tester1 = "5566";
        int tester2 = 9527;
        TestTargetOfReturnMethod target = new TestTargetOfReturnMethod(() -> {
            checkPoint1.getAndIncrement();
            return tester1;
        }, (i) -> {
            checkPoint2.set(i);
            return tester2 + i;
        });

        MethodInvoker methodInvoker = new MethodInvoker(target);

        MethodInvoker.ReturnMethod<String> instanceMethod1 = methodInvoker.find(String.class, "instanceMethod");
        Assertions.assertThat(instanceMethod1.invoke()).isEqualTo(tester1);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(0);

        int tester = 9527;
        MethodInvoker.ReturnMethod<Integer> instanceMethod2 = methodInvoker.find(Integer.class, "instanceMethod", int.class);
        Assertions.assertThat(instanceMethod2.invoke(tester)).isEqualTo(tester + tester2);
        Assertions.assertThat(checkPoint1.get()).isEqualTo(1);
        Assertions.assertThat(checkPoint2.get()).isEqualTo(tester);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find(String.class, "kerker", int.class);
        }).isInstanceOf(NoSuchMethodException.class);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find(String.class, "instanceMethod", String.class);
        }).isInstanceOf(NoSuchMethodException.class)
                .hasMessageContaining(String.format(MethodInvoker.CANT_FIND_RETURNED_METHOD_MSG, String.class.getName()));
        methodInvoker.find(ArrayList.class, "instanceMethod", double.class);
        methodInvoker.find(List.class, "instanceMethod", double.class);

        Assertions.assertThatThrownBy(() -> {
            methodInvoker.find(String.class, "staticMethod");
        }).isInstanceOf(NoSuchMethodException.class)
                .hasMessageContaining(MethodInvoker.CANT_FIND_INSTANCE_METHOD_MSG);
    }

    static class TestTargetOfVoidMethod {
        public static Runnable STATIC_METHOD1;

        public static IntConsumer STATIC_METHOD2;

        private final Runnable instanceMethod1;

        private final IntConsumer instanceMethod2;

        public TestTargetOfVoidMethod(Runnable instanceMethod1, IntConsumer instanceMethod2) {
            this.instanceMethod1 = instanceMethod1;
            this.instanceMethod2 = instanceMethod2;
        }

        public static void staticMethod() {
            STATIC_METHOD1.run();
        }

        public static void staticMethod(int i) {
            STATIC_METHOD2.accept(i);
        }

        public static String staticMethod(String s) {
            return s;
        }

        public void instanceMethod() {
            this.instanceMethod1.run();
        }

        public void instanceMethod(int i) {
            this.instanceMethod2.accept(i);
        }

        public String instanceMethod(String s) {
            return s;
        }
    }

    static class TestTargetOfReturnMethod {

        public static Supplier<String> STATIC_METHOD1;

        public static IntFunction<Integer> STATIC_METHOD2;

        private final Supplier<String> instanceMethod1;

        private final IntFunction<Integer> instanceMethod2;

        public TestTargetOfReturnMethod(Supplier<String> instanceMethod1, IntFunction<Integer> instanceMethod2) {
            this.instanceMethod1 = instanceMethod1;
            this.instanceMethod2 = instanceMethod2;
        }

        public static String staticMethod() {
            return STATIC_METHOD1.get();
        }

        public static Integer staticMethod(int i) {
            return STATIC_METHOD2.apply(i);
        }

        public static void staticMethod(String s) {
        }

        public static ArrayList<String> staticMethod(double d) {
            return new ArrayList<>();
        }

        public String instanceMethod() {
            return this.instanceMethod1.get();
        }

        public Integer instanceMethod(int i) {
            return this.instanceMethod2.apply(i);
        }

        public void instanceMethod(String s) {
        }

        public ArrayList<String> instanceMethod(double d) {
            return new ArrayList<>();
        }
    }

}