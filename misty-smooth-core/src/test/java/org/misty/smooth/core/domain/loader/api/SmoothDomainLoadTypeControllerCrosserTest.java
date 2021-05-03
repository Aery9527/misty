package org.misty.smooth.core.domain.loader.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
class SmoothDomainLoadTypeControllerCrosserTest {

    private static final ClassLoader CL = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());

    @Mock
    private SmoothDomainLoadTypeController<SmoothModuleId> controller;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        Mockito.reset(this.controller);
    }

    @Test
    void prepareLoading() {
        SmoothLoaderArgument loaderArgument = new SmoothLoaderArgument();
        SmoothModuleId smoothId = new SmoothModuleId("kerker", "9527");
        SmoothLoadType loadType = SmoothLoadType.NEW;

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothLoaderArgument> checkPoint2 = new AtomicReference<>();
        AtomicReference<SmoothModuleId> checkPoint3 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            checkPoint3.set(invocationOnMock.getArgument(1));
            return loadType;
        }).when(this.controller).prepareLoading(Mockito.any(), Mockito.any());

        SmoothDomainLoadTypeControllerCrosser<SmoothModuleId> crosser = new SmoothDomainLoadTypeControllerCrosser<>(CL, this.controller);
        Assertions.assertThat(crosser.prepareLoading(loaderArgument, smoothId)).isEqualTo(loadType);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(loaderArgument);
        Assertions.assertThat(checkPoint3.get()).isNotNull().isEqualTo(smoothId);
    }

    @Test
    void release() {
        SmoothModuleId smoothId = new SmoothModuleId("kerker", "9527");

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothModuleId> checkPoint2 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.controller).release(Mockito.any());

        SmoothDomainLoadTypeControllerCrosser<SmoothModuleId> crosser = new SmoothDomainLoadTypeControllerCrosser<>(CL, this.controller);
        crosser.release(smoothId);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(smoothId);
    }


}
