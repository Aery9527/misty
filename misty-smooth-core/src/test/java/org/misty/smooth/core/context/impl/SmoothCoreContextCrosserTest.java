package org.misty.smooth.core.context.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.domain.manager.loader.SmoothManagerDomainLoaderPreset;
import org.misty.smooth.core.domain.module.loader.SmoothModuleDomainLoaderPreset;
import org.misty.smooth.manager.error.SmoothCloseException;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothManagerLoader;
import org.misty.smooth.manager.loader.SmoothModuleLoader;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.net.URLClassLoader;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@ExtendWith(MockitoExtension.class)
class SmoothCoreContextCrosserTest {

    private static final ClassLoader CL = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());

    @Mock
    private SmoothCoreContext context;

    @BeforeEach
    void setUp() {
        Mockito.reset(this.context);
    }

    @Test
    void getIdentifier() {
        String identifier = "kerker";

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return identifier;
        }).when(this.context).getIdentifier();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.getIdentifier()).isEqualTo(identifier);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    void getLaunchInstant() {
        Instant instant = Instant.now();

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return instant;
        }).when(this.context).getLaunchInstant();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.getLaunchInstant()).isEqualTo(instant);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    void getEnvironment() {
        SmoothEnvironment environment = new SmoothCoreEnvironmentPreset();

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return environment;
        }).when(this.context).getEnvironment();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.getEnvironment()).isEqualTo(environment);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    void listModuleWithSet() {
        Set<SmoothModuleId> set = new HashSet<>();

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return set;
        }).when(this.context).listModuleWithSet();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.listModuleWithSet()).isEqualTo(set);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    void listServiceWithSet() {
        String moduleName = "9527";
        Set<SmoothServiceId> set = new HashSet<>();

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<String> checkPoint2 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            return Optional.of(set);
        }).when(this.context).listServiceWithSet(Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.listServiceWithSet(moduleName)).isNotNull().get().isEqualTo(set);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(moduleName);
    }

    @SuppressWarnings("unchecked")
    @Test
    void invokeService_Future() {
        String moduleName = "9527";
        String serviceKey = "9487";
        SmoothServiceRequest request = new SmoothServiceRequest();
        Future<SmoothServiceResponseResult> future = Mockito.mock(Future.class);

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<String> checkPoint2 = new AtomicReference<>();
        AtomicReference<String> checkPoint3 = new AtomicReference<>();
        AtomicReference<SmoothServiceRequest> checkPoint4 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            checkPoint3.set(invocationOnMock.getArgument(1));
            checkPoint4.set(invocationOnMock.getArgument(2));
            return future;
        }).when(this.context).invokeService(Mockito.any(), Mockito.any(), Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.invokeService(moduleName, serviceKey, request)).isEqualTo(future);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(moduleName);
        Assertions.assertThat(checkPoint3.get()).isNotNull().isEqualTo(serviceKey);
        Assertions.assertThat(checkPoint4.get()).isNotNull().isEqualTo(request);
    }

    @SuppressWarnings("unchecked")
    @Test
    void invokeService_resultProcessor() {
        String moduleName = "9527";
        String serviceKey = "9487";
        SmoothServiceRequest request = new SmoothServiceRequest();
        Consumer<SmoothServiceResponseResult> resultProcessor = Mockito.mock(Consumer.class);

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<String> checkPoint2 = new AtomicReference<>();
        AtomicReference<String> checkPoint3 = new AtomicReference<>();
        AtomicReference<SmoothServiceRequest> checkPoint4 = new AtomicReference<>();
        AtomicReference<Consumer<SmoothServiceResponseResult>> checkPoint5 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            checkPoint3.set(invocationOnMock.getArgument(1));
            checkPoint4.set(invocationOnMock.getArgument(2));
            checkPoint5.set(invocationOnMock.getArgument(3));
            return null;
        }).when(this.context).invokeService(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        crosser.invokeService(moduleName, serviceKey, request, resultProcessor);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(moduleName);
        Assertions.assertThat(checkPoint3.get()).isNotNull().isEqualTo(serviceKey);
        Assertions.assertThat(checkPoint4.get()).isNotNull().isEqualTo(request);
        Assertions.assertThat(checkPoint5.get()).isNotNull().isEqualTo(resultProcessor);
    }

    @SuppressWarnings("unchecked")
    @Test
    void loadSmoothManager() {
        SmoothLoaderArgument loaderArgument = new SmoothLoaderArgument();
        Collection<URL> sources = new ArrayList<>();
        SmoothManagerLoader loader = new SmoothManagerDomainLoaderPreset();

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothLoaderArgument> checkPoint2 = new AtomicReference<>();
        AtomicReference<Collection<URL>> checkPoint3 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            checkPoint3.set(invocationOnMock.getArgument(1));
            return loader;
        }).when(this.context).loadSmoothManager(Mockito.any(), (Collection<URL>) Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.loadSmoothManager(loaderArgument, sources)).isEqualTo(loader);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(loaderArgument);
        Assertions.assertThat(checkPoint3.get()).isNotNull().isEqualTo(sources);

        //

        Throwable cause = new RuntimeException();

        Mockito.doAnswer((invocationOnMock) -> {
            throw cause;
        }).when(this.context).loadSmoothManager(Mockito.any(), (Collection<URL>) Mockito.any());

        Assertions.assertThatThrownBy(() -> crosser.loadSmoothManager(loaderArgument, sources))
                .isInstanceOf(SmoothLoadException.class)
                .hasCause(cause);
    }

    @SuppressWarnings("unchecked")
    @Test
    void loadSmoothModule() {
        SmoothLoaderArgument loaderArgument = new SmoothLoaderArgument();
        Collection<URL> sources = new ArrayList<>();
        SmoothModuleLoader loader = new SmoothModuleDomainLoaderPreset();

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothLoaderArgument> checkPoint2 = new AtomicReference<>();
        AtomicReference<Collection<URL>> checkPoint3 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            checkPoint3.set(invocationOnMock.getArgument(1));
            return loader;
        }).when(this.context).loadSmoothModule(Mockito.any(), (Collection<URL>) Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.loadSmoothModule(loaderArgument, sources)).isEqualTo(loader);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(loaderArgument);
        Assertions.assertThat(checkPoint3.get()).isNotNull().isEqualTo(sources);

        //

        Throwable cause = new RuntimeException();

        Mockito.doAnswer((invocationOnMock) -> {
            throw cause;
        }).when(this.context).loadSmoothModule(Mockito.any(), (Collection<URL>) Mockito.any());

        Assertions.assertThatThrownBy(() -> crosser.loadSmoothModule(loaderArgument, sources))
                .isInstanceOf(SmoothLoadException.class)
                .hasCause(cause);
    }

    @Test
    void close() {
        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return null;
        }).when(this.context).close();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        crosser.close();

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);

        //

        Throwable cause = new RuntimeException();

        Mockito.doAnswer((invocationOnMock) -> {
            throw cause;
        }).when(this.context).close();

        Assertions.assertThatThrownBy(crosser::close)
                .isInstanceOf(SmoothCloseException.class)
                .hasCause(cause);

    }

}
