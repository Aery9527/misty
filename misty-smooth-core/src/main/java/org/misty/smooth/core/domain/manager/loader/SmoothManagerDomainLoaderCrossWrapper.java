package org.misty.smooth.core.domain.manager.loader;

import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.core.domain.loader.api.SmoothDomainLoadTypeController;
import org.misty.smooth.manager.SmoothManagerId;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothManagerLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.util.Optional;
import java.util.function.Consumer;

public class SmoothManagerDomainLoaderCrossWrapper
        extends SmoothCrossWrapper<SmoothManagerDomainLoader>
        implements SmoothManagerDomainLoader {

    public SmoothManagerDomainLoaderCrossWrapper(ClassLoader wrapClassLoader, SmoothManagerDomainLoader smoothManagerDomainLoader) {
        super(wrapClassLoader, smoothManagerDomainLoader);
    }

    @Override
    public void setLoadTypeController(SmoothDomainLoadTypeController<SmoothManagerId> loadTypeController) {
        super.wrap(() -> super.getWrappedTarget().setLoadTypeController(loadTypeController));
    }

    @Override
    public void launch() {
        super.wrap(() -> super.getWrappedTarget().launch());
    }

    @Override
    public SmoothManagerId getSmoothId() {
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
    public SmoothManagerLoader registerLoadFinishAction(Consumer<SmoothManagerLoader> action) throws SmoothLoadException {
        return super.wrap(() -> super.getWrappedTarget().registerLoadFinishAction(action));
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
