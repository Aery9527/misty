package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.misty.util.tool.AtomicStep;

import java.util.function.Consumer;

public abstract class SmoothDomainLoaderAbstract<
        SmoothIdType extends SmoothId<SmoothIdType>,
        LoadType extends SmoothLoader<SmoothIdType, LoadType>,
        LifecycleType extends SmoothLifecycle
        > implements SmoothLoader<SmoothIdType, LoadType>, SmoothDomainLoader<SmoothIdType> {

    private SmoothIdType smoothId;

    private AtomicStep<SmoothLoadState> loadState = new AtomicStep<>(SmoothLoadState.INITIAL);

    private SmoothLoadType loadType;

    private SmoothLoaderArgument loaderArgument;

    private LifecycleType domainLifecycle;

    private SmoothDomainLaunchThreadFactory<SmoothIdType> launchThreadFactory;

    private SmoothDomainLoadTypeController<SmoothIdType> loadTypeController;

    private Consumer<LoadType> loadFinishAction;

    @Override
    public void launch() {
        this.loadState.to((currentState) -> {
            return currentState.toNext(SmoothLoadState.LOADING, (previousState) -> {
                // TODO
            });
        });
        checkField();


    }

    @Override
    public void online() throws SmoothLoadException {

    }

    @Override
    public LoadType registerLoadFinishAction(Consumer<LoadType> action) throws SmoothLoadException {
        this.loadFinishAction = action;
        return (LoadType) this;
    }

    private void checkField() {
    }

    @Override
    public SmoothIdType getSmoothId() {
        return smoothId;
    }

    public void setSmoothId(SmoothIdType smoothId) {
        this.smoothId = smoothId;
    }

    @Override
    public SmoothLoadState getLoadState() {
        return loadState.get();
    }

    @Override
    public SmoothLoadType getLoadType() {
        return loadType;
    }

    public void setLoadType(SmoothLoadType loadType) {
        this.loadType = loadType;
    }

    @Override
    public SmoothLoaderArgument getLoaderArgument() {
        return loaderArgument;
    }

    public void setLoaderArgument(SmoothLoaderArgument loaderArgument) {
        this.loaderArgument = loaderArgument;
    }

    public LifecycleType getDomainLifecycle() {
        return domainLifecycle;
    }

    public void setDomainLifecycle(LifecycleType domainLifecycle) {
        this.domainLifecycle = domainLifecycle;
    }

    public SmoothDomainLaunchThreadFactory<SmoothIdType> getLaunchThreadFactory() {
        return launchThreadFactory;
    }

    public void setLaunchThreadFactory(SmoothDomainLaunchThreadFactory<SmoothIdType> launchThreadFactory) {
        this.launchThreadFactory = launchThreadFactory;
    }

    public SmoothDomainLoadTypeController<SmoothIdType> getLoadTypeController() {
        return loadTypeController;
    }

    @Override
    public void setLoadTypeController(SmoothDomainLoadTypeController<SmoothIdType> loadTypeController) {
        this.loadTypeController = loadTypeController;
    }

}
