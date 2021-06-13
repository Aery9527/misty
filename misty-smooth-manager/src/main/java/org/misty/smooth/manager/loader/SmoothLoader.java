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

    /**
     * Can only be registered once
     *
     * @param action
     * @return
     * @throws SmoothLoadException
     */
    LoadType registerLoadFinishAction(BiConsumer<SmoothLoadFinishState, LoadType> action) throws SmoothLoadException;

    /**
     * invoke to move on next stage when {@link #getLoadState()} return {@link SmoothLoadState#WAITING_ONLINE}
     *
     * @throws SmoothLoadException
     */
    void online() throws SmoothLoadException;

    /**
     * invoke to retry load when {@link #getLoadState()} return {@link SmoothLoadState#LOAD_FAILED}
     *
     * @throws SmoothLoadException
     */
    void retryLoad() throws SmoothLoadException;

    /**
     * invoke to retry online when {@link #getLoadState()} return {@link SmoothLoadState#ONLINE_FAILED}
     *
     * @throws SmoothLoadException
     */
    void retryOnline() throws SmoothLoadException;

    void destroy() throws SmoothLoadException;

    Optional<Throwable> getCurrentError();

}
