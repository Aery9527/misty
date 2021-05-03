package org.misty.smooth.core.domain.module.loader;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoadTypeController;
import org.misty.smooth.manager.loader.SmoothModuleLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@ExtendWith(MockitoExtension.class)
class SmoothModuleDomainLoaderCrosserTest {

    private static final ClassLoader CL = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());

    @Mock
    private SmoothModuleDomainLoaderPreset loader;

    @BeforeEach
    void setUp() {
        Mockito.reset(this.loader);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setLoadTypeController() {
        SmoothDomainLoadTypeController<SmoothModuleId> loadTypeController = Mockito.mock(SmoothDomainLoadTypeController.class);

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothDomainLoadTypeController<SmoothModuleId>> checkPoint2 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.loader).setLoadTypeController(Mockito.any());

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        crosser.setLoadTypeController(loadTypeController);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(loadTypeController);
    }

    @Test
    public void launch() {
        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return null;
        }).when(this.loader).launch();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        crosser.launch();

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getSmoothId() {
        SmoothModuleId id = new SmoothModuleId("kerker", "9527");

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return id;
        }).when(this.loader).getSmoothId();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getSmoothId()).isEqualTo(id);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getLoadState() {
        SmoothLoadState loadState = SmoothLoadState.LOADING;

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return loadState;
        }).when(this.loader).getLoadState();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getLoadState()).isEqualTo(loadState);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getLoaderArgument() {
        SmoothLoaderArgument loaderArgument = new SmoothLoaderArgument();

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return loaderArgument;
        }).when(this.loader).getLoaderArgument();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getLoaderArgument()).isEqualTo(loaderArgument);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getLoadType() {
        SmoothLoadType loadType = SmoothLoadType.NEW;

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return Optional.of(loadType);
        }).when(this.loader).getLoadType();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getLoadType()).isNotEmpty().get().isEqualTo(loadType);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void registerLoadFinishAction() {
        Consumer<SmoothModuleLoader> action = Mockito.mock(Consumer.class);

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<ClassLoader> checkPoint2 = new AtomicReference<>();

        AtomicReference<Consumer<SmoothModuleLoader>> temp = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            temp.set(invocationOnMock.getArgument(0));
            return invocationOnMock.getMock();
        }).when(this.loader).registerLoadFinishAction(Mockito.any());

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint2.set(Thread.currentThread().getContextClassLoader());
            return null;
        }).when(action).accept(Mockito.any());

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);

        ClassLoader tempCl = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());
        ClassLoader originalCl = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(tempCl);
        Assertions.assertThat(crosser.registerLoadFinishAction(action)).isEqualTo(crosser);
        Thread.currentThread().setContextClassLoader(originalCl);

        temp.get().accept(null);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(tempCl);
    }

    @Test
    public void online() {
        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return null;
        }).when(this.loader).online();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        crosser.online();

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void retryLoading() {
        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return null;
        }).when(this.loader).retryLoading();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        crosser.retryLoading();

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void retryOnline() {
        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return null;
        }).when(this.loader).retryOnline();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        crosser.retryOnline();

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getCurrentError() {
        Throwable t = new RuntimeException();

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return Optional.of(t);
        }).when(this.loader).getCurrentError();

        SmoothModuleDomainLoaderCrosser crosser = new SmoothModuleDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getCurrentError()).isNotEmpty().get().isEqualTo(t);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

}
