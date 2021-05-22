package org.misty.smooth.core.domain.classloader;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.error.SmoothLoadException;

import java.net.URL;

class SmoothDomainClassLoaderTest {

    @Test
    void test() {
        SmoothDomainClassLoader cl = new SmoothDomainClassLoader(new URL[0], ClassLoader.getSystemClassLoader());
        System.out.println(cl);

        SmoothManagerId id = new SmoothManagerId("kerker", "v9527");
        cl.setSmoothId(id);
        System.out.println(cl);

        Assertions.assertThatThrownBy(() -> cl.setSmoothId(null))
                .isInstanceOf(SmoothLoadException.class);

        Assertions.assertThatThrownBy(() -> cl.setSmoothId(id))
                .isInstanceOf(SmoothLoadException.class);
    }


}
