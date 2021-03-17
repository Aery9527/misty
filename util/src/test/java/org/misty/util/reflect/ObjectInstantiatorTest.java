package org.misty.util.reflect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjectInstantiatorTest {

    @Test
    public void test() throws NoSuchMethodException {
        ObjectInstantiator<Kerker> objectInstantiator = new ObjectInstantiator<>(Kerker.class);

        ObjectInstantiator.Broker<Kerker> broker1 = objectInstantiator.construct();
        Kerker Kerker1 = broker1.instance();
        Assertions.assertThatThrownBy(() -> broker1.instance(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("wrong number of arguments");

        ObjectInstantiator.Broker<Kerker> broker2 = objectInstantiator.construct(String.class);
        Kerker Kerker2 = broker2.instance("123");
        Assertions.assertThatThrownBy(() -> broker2.instance())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("wrong number of arguments");
        Assertions.assertThatThrownBy(() -> broker2.instance(123))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("argument type mismatch");

        Assertions.assertThatThrownBy(() -> objectInstantiator.construct(int.class))
                .isInstanceOf(NoSuchMethodException.class);
    }

    public static class Kerker {
        public Kerker() {
        }

        public Kerker(String s) {
        }
    }

}