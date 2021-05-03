package org.misty.smooth.core.domain.manager.loader;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoadTypeController;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.loader.SmoothManagerLoader;
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
public class SmoothManagerDomainLoaderCrosserTest {

    private static final ClassLoader CL = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());

    @Mock
    private SmoothManagerDomainLoaderPreset loader;

    @BeforeEach
    void setUp() {
        Mockito.reset(this.loader);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setLoadTypeController() {
        SmoothDomainLoadTypeController<SmoothManagerId> loadTypeController = Mockito.mock(SmoothDomainLoadTypeController.class);

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<SmoothDomainLoadTypeController<SmoothManagerId>> checkPoint2 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            checkPoint2.set(invocationOnMock.getArgument(0));
            return null;
        }).when(this.loader).setLoadTypeController(Mockito.any());

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
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

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        crosser.launch();

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getSmoothId() {
        SmoothManagerId id = new SmoothManagerId("kerker", "9527");

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            return id;
        }).when(this.loader).getSmoothId();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
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

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
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

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
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

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getLoadType()).isNotEmpty().get().isEqualTo(loadType);

        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void registerLoadFinishAction() {
        Consumer<SmoothManagerLoader> action = Mockito.mock(Consumer.class);

        AtomicReference<ClassLoader> checkPoint1 = new AtomicReference<>();
        AtomicReference<ClassLoader> checkPoint2 = new AtomicReference<>();

        AtomicReference<Consumer<SmoothManagerLoader>> temp = new AtomicReference<>();

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint1.set(Thread.currentThread().getContextClassLoader());
            temp.set(invocationOnMock.getArgument(0));
            return invocationOnMock.getMock();
        }).when(this.loader).registerLoadFinishAction(Mockito.any());

        Mockito.doAnswer((invocationOnMock) -> {
            checkPoint2.set(Thread.currentThread().getContextClassLoader());
            return null;
        }).when(action).accept(Mockito.any());

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);

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

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
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

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
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

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
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

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getCurrentError()).isNotEmpty().get().isEqualTo(t);
        Assertions.assertThat(checkPoint1.get()).isNotNull().isEqualTo(CL);
    }

}
