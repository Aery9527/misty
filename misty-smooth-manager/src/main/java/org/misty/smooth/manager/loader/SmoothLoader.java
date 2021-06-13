package org.misty.smooth.manager.loader;

import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.enums.SmoothLoadFinishState;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface SmoothLoader<
        SmoothIdType extends SmoothId<SmoothIdType>,
        LoadType extends SmoothLoader<SmoothIdType, LoadType>
        > {

    SmoothIdType getSmoothId();

    SmoothLoadState getLoadState();

    SmoothLoaderArgument getLoaderArgument();

    Optional<SmoothLoadType> getLoadType();

    LoadType registerLoadFinishAction(BiConsumer<SmoothLoadFinishState, LoadType> action) throws SmoothLoadException;

    void online() throws SmoothLoadException;

    void retryLoading() throws SmoothLoadException;

    void retryOnline() throws SmoothLoadException;

    Optional<Throwable> getCurrentError();

}
