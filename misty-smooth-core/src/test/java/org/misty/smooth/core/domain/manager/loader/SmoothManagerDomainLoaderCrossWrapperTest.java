package org.misty.smooth.core.domain.manager.loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.smooth.api.cross.SmoothCrossSupplier;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoadTypeController;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.net.URLClassLoader;

@ExtendWith(MockitoExtension.class)
public class SmoothManagerDomainLoaderCrossWrapperTest {

    private static final ClassLoader CL = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());

    private static final SmoothManagerDomainLoader LOADER = new SmoothManagerDomainLoaderPreset();

    private static class TestWrapper extends SmoothManagerDomainLoaderCrossWrapper {
        public TestWrapper() {
            super(CL, LOADER);
        }
    }

    @Spy
    private TestWrapper wrapper;

    @BeforeEach
    public void setUp() {
        Mockito.reset(this.wrapper);
    }

    @Test
    public void setLoadTypeController() {
        SmoothDomainLoadTypeController<SmoothManagerId> loadTypeController = new SmoothDomainLoadTypeController<SmoothManagerId>() {
            @Override
            public SmoothLoadType prepareLoading(SmoothLoaderArgument loaderArgument, SmoothManagerId smoothId) {
                return null;
            }

            @Override
            public void release(SmoothManagerId smoothId) {

            }
        };

        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).wrap((SmoothCrossSupplier<Object>) Mockito::any);
//        }).when(this.wrapper).setLoadTypeController(Mockito.any());

//        Mockito.doAnswer((invocationOnMock) -> {
//            return null;
//        }).when(this.wrapper).setLoadTypeController(Mockito.any());

        this.wrapper.setLoadTypeController(loadTypeController);
    }

    @Test
    public void launch() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).launch();
    }

    @Test
    public void getSmoothId() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).getSmoothId();
    }

    @Test
    public void getLoadState() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).getLoadState();
    }

    @Test
    public void getLoaderArgument() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).getLoaderArgument();
    }

    @Test
    public void getLoadType() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).getLoadType();
    }

    @Test
    public void registerLoadFinishAction() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).registerLoadFinishAction(Mockito.any());
    }

    @Test
    public void online() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).online();
    }

    @Test
    public void retryLoading() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).retryLoading();
    }

    @Test
    public void retryOnline() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).retryOnline();
    }

    @Test
    public void getCurrentError() {
        Mockito.doAnswer((invocationOnMock) -> {
            return null;
        }).when(this.wrapper).getCurrentError();
    }

}
