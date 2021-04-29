package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.util.function.Consumer;

public abstract class SmoothDomainLoaderAbstract<
        SmoothIdType extends SmoothId<SmoothIdType>, LoadType extends SmoothLoader<SmoothIdType, LoadType>
        > implements SmoothLoader<SmoothIdType, LoadType>, SmoothDomainLoader {

    @Override
    public SmoothIdType getSmoothId() {
        return null;
    }

    @Override
    public SmoothLoadState getLoadState() {
        return null;
    }

    @Override
    public SmoothLoadType getLoadType() {
        return null;
    }

    @Override
    public SmoothLoaderArgument getArgument() {
        return null;
    }

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

}
