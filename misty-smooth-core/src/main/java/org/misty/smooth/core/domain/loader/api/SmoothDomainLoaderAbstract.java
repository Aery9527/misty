package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.core.domain.loader.preset.api.SmoothDomainLifecycleThreadFactory;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.util.function.Consumer;

public abstract class SmoothDomainLoaderAbstract<
        SmoothIdType extends SmoothId<SmoothIdType>, LoadType extends SmoothLoader<SmoothIdType, LoadType>
        > implements SmoothLoader<SmoothIdType, LoadType>, SmoothDomainLoader {

    private SmoothIdType smoothId;

    private SmoothLoadState loadState = SmoothLoadState.LOADING;

    private SmoothLoadType loadType;

    private SmoothLoaderArgument loaderArgument;

    private SmoothDomainLifecycleThreadFactory lifecycleThreadFactory;

    @Override
    public LoadType registerLoadFinishAction(Consumer<LoadType> action) throws SmoothLoadException {
        return null;
    }

    @Override
    public void online() throws SmoothLoadException {

    }

    @Override
    public void launch() {

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
        return loadState;
    }

    public void setLoadState(SmoothLoadState loadState) {
        this.loadState = loadState;
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

    public SmoothDomainLifecycleThreadFactory getLifecycleThreadFactory() {
        return lifecycleThreadFactory;
    }

    public void setLifecycleThreadFactory(SmoothDomainLifecycleThreadFactory lifecycleThreadFactory) {
        this.lifecycleThreadFactory = lifecycleThreadFactory;
    }

}
