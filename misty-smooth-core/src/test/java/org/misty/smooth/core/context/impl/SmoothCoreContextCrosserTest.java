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
import org.misty.ut.common.CrosserTest;
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

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> identifier, this.context).getIdentifier();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.getIdentifier()).isEqualTo(identifier);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    void getLaunchInstant() {
        Instant instant = Instant.now();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> instant, this.context).getLaunchInstant();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.getLaunchInstant()).isEqualTo(instant);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    void getEnvironment() {
        SmoothEnvironment environment = new SmoothCoreEnvironmentPreset();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> environment, this.context).getEnvironment();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.getEnvironment()).isEqualTo(environment);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    void listModuleWithSet() {
        Set<SmoothModuleId> set = new HashSet<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> set, this.context).listModuleWithSet();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.listModuleWithSet()).isEqualTo(set);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    void listServiceWithSet() {
        String moduleName = "9527";
        Set<SmoothServiceId> set = new HashSet<>();

        AtomicReference<String> checkPoint1 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            return Optional.of(set);
        }, this.context).listServiceWithSet(Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.listServiceWithSet(moduleName)).isNotNull().get().isEqualTo(set);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(moduleName);
    }

    @SuppressWarnings("unchecked")
    @Test
    void invoke_Future() {
        SmoothModuleId id = new SmoothModuleId("kerker", "9527");
        String serviceKey = "9487";
        SmoothServiceRequest request = new SmoothServiceRequest();
        Future<SmoothServiceResponseResult> future = Mockito.mock(Future.class);

        AtomicReference<SmoothModuleId> checkPoint1 = new AtomicReference<>();
        AtomicReference<String> checkPoint2 = new AtomicReference<>();
        AtomicReference<SmoothServiceRequest> checkPoint3 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            checkPoint3.set(invocationOnMock.getArgument(2));
            return future;
        }, this.context).invoke(Mockito.any(), Mockito.any(), Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.invoke(id, serviceKey, request)).isEqualTo(future);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(id);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(serviceKey);
        Assertions.assertThat(checkPoint3.get()).isNotNull().isEqualTo(request);
    }

    @SuppressWarnings("unchecked")
    @Test
    void invoke_resultProcessor() {
        SmoothModuleId id = new SmoothModuleId("kerker", "9527");
        String serviceKey = "9487";
        SmoothServiceRequest request = new SmoothServiceRequest();
        Consumer<SmoothServiceResponseResult> resultProcessor = Mockito.mock(Consumer.class);

        AtomicReference<SmoothModuleId> checkPoint1 = new AtomicReference<>();
        AtomicReference<String> checkPoint2 = new AtomicReference<>();
        AtomicReference<SmoothServiceRequest> checkPoint3 = new AtomicReference<>();
        AtomicReference<Consumer<SmoothServiceResponseResult>> checkPoint4 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            checkPoint3.set(invocationOnMock.getArgument(2));
            checkPoint4.set(invocationOnMock.getArgument(3));
            return null;
        }, this.context).invoke(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        crosser.invoke(id, serviceKey, request, resultProcessor);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(id);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(serviceKey);
        Assertions.assertThat(checkPoint3.get()).isNotNull().isEqualTo(request);
        Assertions.assertThat(checkPoint4.get()).isNotNull().isEqualTo(resultProcessor);
    }

    @SuppressWarnings("unchecked")
    @Test
    void loadSmoothManager() {
        SmoothLoaderArgument loaderArgument = new SmoothLoaderArgument();
        Collection<URL> sources = new ArrayList<>();
        SmoothManagerLoader loader = new SmoothManagerDomainLoaderPreset();

        AtomicReference<SmoothLoaderArgument> checkPoint1 = new AtomicReference<>();
        AtomicReference<Collection<URL>> checkPoint2 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return loader;
        }, this.context).loadSmoothManager(Mockito.any(), (Collection<URL>) Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.loadSmoothManager(loaderArgument, sources)).isEqualTo(loader);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(loaderArgument);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(sources);

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

        AtomicReference<SmoothLoaderArgument> checkPoint1 = new AtomicReference<>();
        AtomicReference<Collection<URL>> checkPoint2 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint1.set(invocationOnMock.getArgument(0));
            checkPoint2.set(invocationOnMock.getArgument(1));
            return loader;
        }, this.context).loadSmoothModule(Mockito.any(), (Collection<URL>) Mockito.any());

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        Assertions.assertThat(crosser.loadSmoothModule(loaderArgument, sources)).isEqualTo(loader);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(loaderArgument);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(sources);

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
        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> null, this.context).close();

        SmoothCoreContextCrosser crosser = new SmoothCoreContextCrosser(CL, this.context);
        crosser.close();

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);

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
