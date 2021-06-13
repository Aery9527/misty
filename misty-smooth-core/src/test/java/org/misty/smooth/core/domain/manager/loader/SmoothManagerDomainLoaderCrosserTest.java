package org.misty.smooth.core.domain.manager.loader;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.api.vo.SmoothUnscalableMap;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoadTypeController;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.loader.SmoothManagerLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadFinishState;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.ut.common.CrosserTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

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

        AtomicReference<SmoothDomainLoadTypeController<SmoothManagerId>> checkPoint2 = new AtomicReference<>();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> {
            checkPoint2.set(invocationOnMock.getArgument(0));
            return null;
        }, this.loader).setLoadTypeController(Mockito.any());

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        crosser.setLoadTypeController(loadTypeController);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(checkPoint2.get()).isNotNull().isEqualTo(loadTypeController);
    }

    @Test
    public void launch() {
        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> null, this.loader).launch();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        crosser.launch();

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getSmoothId() {
        SmoothManagerId id = new SmoothManagerId("kerker", "9527");

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> id, this.loader).getSmoothId();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getSmoothId()).isEqualTo(id);
        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getLoadState() {
        SmoothLoadState loadState = SmoothLoadState.LOADING;

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> loadState, this.loader).getLoadState();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getLoadState()).isEqualTo(loadState);
        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getLoaderArgument() {
        SmoothUnscalableMap loaderArgument = new SmoothUnscalableMap();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> loaderArgument, this.loader).getLoaderArgument();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getLoaderArgument()).isEqualTo(loaderArgument);
        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getLoadType() {
        SmoothLoadType loadType = SmoothLoadType.NEW;

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> Optional.of(loadType), this.loader).getLoadType();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getLoadType()).isNotEmpty().get().isEqualTo(loadType);

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void registerLoadFinishAction() {
        SmoothLoadFinishState loadFinishState = SmoothLoadFinishState.LOAD_FAILED;
        BiConsumer<SmoothLoadFinishState, SmoothManagerLoader> action = Mockito.mock(BiConsumer.class);

        AtomicReference<BiConsumer<SmoothLoadFinishState, SmoothManagerLoader>> temp = new AtomicReference<>();

        CrosserTest crosserTest1 = new CrosserTest();
        crosserTest1.mock((invocationOnMock) -> {
            temp.set(invocationOnMock.getArgument(0));
            return invocationOnMock.getMock();
        }, this.loader).registerLoadFinishAction(Mockito.any());

        CrosserTest crosserTest2 = new CrosserTest();
        crosserTest2.mock((invocationOnMock) -> null, action).accept(Mockito.any(), Mockito.any());

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);

        ClassLoader tempCl = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());
        ClassLoader originalCl = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(tempCl);
        Assertions.assertThat(crosser.registerLoadFinishAction(action)).isEqualTo(crosser);
        Thread.currentThread().setContextClassLoader(originalCl);

        temp.get().accept(loadFinishState, null);

        Assertions.assertThat(crosserTest1.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
        Assertions.assertThat(crosserTest2.getExecuteClassLoader()).isNotNull().isEqualTo(tempCl);
    }

    @Test
    public void online() {
        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> null, this.loader).online();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        crosser.online();

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void retryLoading() {
        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> null, this.loader).retryLoad();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        crosser.retryLoad();

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void retryOnline() {
        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> null, this.loader).retryOnline();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        crosser.retryOnline();

        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

    @Test
    public void getCurrentError() {
        Throwable t = new RuntimeException();

        CrosserTest crosserTest = new CrosserTest();
        crosserTest.mock((invocationOnMock) -> Optional.of(t), this.loader).getCurrentError();

        SmoothManagerDomainLoaderCrosser crosser = new SmoothManagerDomainLoaderCrosser(CL, this.loader);
        Assertions.assertThat(crosser.getCurrentError()).isNotEmpty().get().isEqualTo(t);
        Assertions.assertThat(crosserTest.getExecuteClassLoader()).isNotNull().isEqualTo(CL);
    }

}
