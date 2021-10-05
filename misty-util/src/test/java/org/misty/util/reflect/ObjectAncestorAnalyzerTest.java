package org.misty.util.reflect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class ObjectAncestorAnalyzerTest {

    public static abstract class Abstract {
    }

    public static abstract class AbstractA<G1, G2> {
    }

    public static abstract class AbstractB<G1, G2> extends AbstractA<String, G1> {
    }

    public interface Interface {

    }

    public interface InterfaceA<G1, G2> {
    }

    public interface InterfaceB<G1> extends InterfaceA<String, G1> {
    }

    public interface InterfaceC<G1> {
    }

    public static class Implement
            extends Abstract
            implements Interface {
    }

    public static class Implement1
            extends AbstractB<Integer, Long>
            implements Interface, InterfaceB<Void>, InterfaceC<Date> {
    }

    public static class Implement2<G1, G2, G3, G4>
            extends AbstractB<G1, G4>
            implements Interface, InterfaceB<G2>, InterfaceC<G3> {
    }

    @Test
    public void getAllSuperClass() {
        ObjectAncestorAnalyzer analyzer0 = ObjectAncestorAnalyzer.analyze(Implement.class);
        List<Class<?>> allSuperClass = analyzer0.getAllSuperClass();
        Assertions.assertThat(allSuperClass).containsExactly(Abstract.class);

        ObjectAncestorAnalyzer analyzer1 = ObjectAncestorAnalyzer.analyze(Implement1.class);
        List<Class<?>> allSuperClass1 = analyzer1.getAllSuperClass();
        Assertions.assertThat(allSuperClass1).containsExactly(AbstractB.class, AbstractA.class);

        ObjectAncestorAnalyzer analyzer2 = ObjectAncestorAnalyzer.analyze(Implement2.class);
        List<Class<?>> allSuperClass2 = analyzer2.getAllSuperClass();
        Assertions.assertThat(allSuperClass2).containsExactly(AbstractB.class, AbstractA.class);
    }

    @Test
    public void getAllInterface() {
        ObjectAncestorAnalyzer analyzer0 = ObjectAncestorAnalyzer.analyze(Implement.class);

        List<Class<?>> allInterface = analyzer0.getAllInterface();
        Assertions.assertThat(allInterface).containsExactlyInAnyOrder(Interface.class);

        ObjectAncestorAnalyzer analyzer1 = ObjectAncestorAnalyzer.analyze(Implement1.class);
        List<Class<?>> allInterface1 = analyzer1.getAllInterface();
        Assertions.assertThat(allInterface1).containsExactlyInAnyOrder(
                Interface.class, InterfaceA.class, InterfaceB.class, InterfaceC.class
        );

        ObjectAncestorAnalyzer analyzer2 = ObjectAncestorAnalyzer.analyze(Implement2.class);
        List<Class<?>> allInterface2 = analyzer2.getAllInterface();
        Assertions.assertThat(allInterface2).containsExactlyInAnyOrder(
                Interface.class, InterfaceA.class, InterfaceB.class, InterfaceC.class
        );
    }

    @Test
    public void getGenericAtIndex() {
        ObjectAncestorAnalyzer analyzer0 = ObjectAncestorAnalyzer.analyze(Implement.class);
        Assertions.assertThat(analyzer0.getGenericAtIndex(AbstractA.class, 0)).isEmpty();
        Assertions.assertThat(analyzer0.getGenericAtIndex(Abstract.class, -1)).isEmpty();
        Assertions.assertThat(analyzer0.getGenericAtIndex(Abstract.class, 0)).isEmpty();
        Assertions.assertThat(analyzer0.getGenericAtIndex(Abstract.class, 1)).isEmpty();
        Assertions.assertThat(analyzer0.getGenericAtIndex(Interface.class, -1)).isEmpty();
        Assertions.assertThat(analyzer0.getGenericAtIndex(Interface.class, 0)).isEmpty();
        Assertions.assertThat(analyzer0.getGenericAtIndex(Interface.class, 1)).isEmpty();

        ObjectAncestorAnalyzer analyzer1 = ObjectAncestorAnalyzer.analyze(Implement1.class);
        Assertions.assertThat(analyzer1.getGenericAtIndex(AbstractB.class, 0)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(AbstractB.class, 0, Integer.class)
        );
        Assertions.assertThat(analyzer1.getGenericAtIndex(AbstractB.class, 1)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(AbstractB.class, 1, Long.class)
        );
        Assertions.assertThat(analyzer1.getGenericAtIndex(AbstractA.class, 0)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(AbstractA.class, 0, String.class)
        );
        Assertions.assertThat(analyzer1.getGenericAtIndex(AbstractA.class, 1)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(AbstractA.class, 1, Integer.class)
        );
        Assertions.assertThat(analyzer1.getGenericAtIndex(InterfaceB.class, 0)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(InterfaceB.class, 0, Void.class)
        );
        Assertions.assertThat(analyzer1.getGenericAtIndex(InterfaceC.class, 0)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(InterfaceC.class, 0, Date.class)
        );
        Assertions.assertThat(analyzer1.getGenericAtIndex(InterfaceA.class, 0)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(InterfaceA.class, 0, String.class)
        );
        Assertions.assertThat(analyzer1.getGenericAtIndex(InterfaceA.class, 1)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(InterfaceA.class, 1, Void.class)
        );

        ObjectAncestorAnalyzer analyzer2 = ObjectAncestorAnalyzer.analyze(Implement2.class);
        Assertions.assertThat(analyzer2.getGenericAtIndex(AbstractB.class, 0).get().getUncertainName()).isEqualTo("G1");
        Assertions.assertThat(analyzer2.getGenericAtIndex(AbstractB.class, 1).get().getUncertainName()).isEqualTo("G4");
        Assertions.assertThat(analyzer2.getGenericAtIndex(AbstractA.class, 0)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(AbstractA.class, 0, String.class)
        );
        Assertions.assertThat(analyzer2.getGenericAtIndex(AbstractA.class, 1).get().getUncertainName()).isEqualTo("G1");
        Assertions.assertThat(analyzer2.getGenericAtIndex(InterfaceB.class, 0).get().getUncertainName()).isEqualTo("G2");
        Assertions.assertThat(analyzer2.getGenericAtIndex(InterfaceC.class, 0).get().getUncertainName()).isEqualTo("G3");
        Assertions.assertThat(analyzer2.getGenericAtIndex(InterfaceC.class, 0).get().getUncertainName()).isEqualTo("G3");
        Assertions.assertThat(analyzer2.getGenericAtIndex(InterfaceA.class, 0)).get().isEqualTo(
                new ObjectAncestorAnalyzer.GenericCertainType(InterfaceA.class, 0, String.class)
        );
        Assertions.assertThat(analyzer2.getGenericAtIndex(InterfaceA.class, 1).get().getUncertainName()).isEqualTo("G1");
    }

}
