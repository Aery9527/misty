package org.misty.util.reflect;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;
import java.util.Date;

public class ObjectAncestorAnalyzerTest {

    @Target(ElementType.TYPE_PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface AnnotationA {

    }

    public static abstract class Abstract {
    }

    public static abstract class AbstractA<G1, G2> {
    }

    public static abstract class AbstractB<@AnnotationA G1> extends AbstractA<String, G1> {
    }

    public interface Interface {

    }

    public interface InterfaceA<G1, G2> {
    }

    public interface InterfaceB<G1> extends InterfaceA<String, G1> {
    }

    public interface InterfaceC<G1> {
    }

    public static abstract class Implement
            extends Abstract
            implements Interface {
    }

    public static abstract class Implement1
            extends AbstractB<Integer>
            implements InterfaceB<Void>, InterfaceC<Date> {
    }

    public static abstract class Implement2<G1, G2, G3>
            extends AbstractB<G1>
            implements InterfaceB<G2>, InterfaceC<G3> {
    }

    @Test
    public void name() {
//        ObjectAncestorAnalyzer analyze1 = ObjectAncestorAnalyzer.analyze(Implement.class);
        ObjectAncestorAnalyzer analyze2 = ObjectAncestorAnalyzer.analyze(Implement2.class);


    }


}
