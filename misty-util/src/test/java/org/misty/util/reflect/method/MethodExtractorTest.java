package org.misty.util.reflect.method;

import org.junit.jupiter.api.Test;

class MethodExtractorTest {

    public static class TestTarget {

    }

    @Test
    void name() throws NoSuchMethodException {

        MethodExtractor extractor = new MethodExtractor(TestTarget.class);

        MethodArg0ReturnInvoker<String> a = extractor.buildObjectInvoker("kerker", String.class);
        MethodArg1ReturnInvoker<String, Integer> b = extractor.buildObjectInvoker("kerker", String.class, int.class);
        MethodArg2ReturnInvoker<String, Integer, Integer> c = extractor.buildObjectInvoker("kerker", String.class, int.class, int.class);
        MethodObjectInvoker<String> d = extractor.buildObjectInvoker("kerker", String.class, int.class, int.class, int.class);






    }


}
