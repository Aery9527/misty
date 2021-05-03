package org.misty.smooth.core.domain.module.loader;

import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.cross.SmoothCrosser;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoadTypeController;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothModuleLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.util.Optional;
import java.util.function.Consumer;

public class SmoothModuleDomainLoaderCrosser
        extends SmoothCrossWrapper<SmoothModuleDomainLoader>
        implements SmoothModuleDomainLoader {

    public SmoothModuleDomainLoaderCrosser(SmoothModuleDomainLoader smoothModuleDomainLoader) {
        super(SmoothModuleDomainLoaderCrosser.class.getClassLoader(), smoothModuleDomainLoader);
    }

    public SmoothModuleDomainLoaderCrosser(ClassLoader wrapClassLoader, SmoothModuleDomainLoader smoothModuleDomainLoader) {
        super(wrapClassLoader, smoothModuleDomainLoader);
    }

    @Override
    public void setLoadTypeController(SmoothDomainLoadTypeController<SmoothModuleId> loadTypeController) {
        super.wrap(() -> super.getWrappedTarget().setLoadTypeController(loadTypeController));
    }

    @Override
    public void launch() {
        super.wrap(() -> super.getWrappedTarget().launch());
    }

    @Override
    public SmoothModuleId getSmoothId() {
        return super.wrap(() -> super.getWrappedTarget().getSmoothId());
    }

    @Override
    public SmoothLoadState getLoadState() {
        return super.wrap(() -> super.getWrappedTarget().getLoadState());
    }

    @Override
    public SmoothLoaderArgument getLoaderArgument() {
        return super.wrap(() -> super.getWrappedTarget().getLoaderArgument());
    }

    @Override
    public Optional<SmoothLoadType> getLoadType() {
        return super.wrap(() -> super.getWrappedTarget().getLoadType());
    }

    @Override
    public SmoothModuleLoader registerLoadFinishAction(Consumer<SmoothModuleLoader> action) throws SmoothLoadException {
        Thread currentThread = Thread.currentThread();
        ClassLoader currentContextClassLoader = currentThread.getContextClassLoader();
        SmoothCrosser actionCrosser = new SmoothCrosser(currentContextClassLoader);

        Consumer<SmoothModuleLoader> wrapAction = (loader) -> {
            actionCrosser.wrap(() -> {
                action.accept(loader);
            });
        };

        return super.wrap(() -> {
            super.getWrappedTarget().registerLoadFinishAction(wrapAction);
            return this;
        });
    }

    @Override
    public void online() throws SmoothLoadException {
        super.wrap(() -> super.getWrappedTarget().online());
    }

    @Override
    public void retryLoading() throws SmoothLoadException {
        super.wrap(() -> super.getWrappedTarget().retryLoading());
    }

    @Override
    public void retryOnline() throws SmoothLoadException {
        super.wrap(() -> super.getWrappedTarget().retryOnline());
    }

    @Override
    public Optional<Throwable> getCurrentError() {
        return super.wrap(() -> super.getWrappedTarget().getCurrentError());
    }

}
