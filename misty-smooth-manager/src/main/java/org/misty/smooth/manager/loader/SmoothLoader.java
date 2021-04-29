package org.misty.smooth.manager.loader;

import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.util.function.Consumer;

public interface SmoothLoader<SmoothIdType extends SmoothId<SmoothIdType>, LoadType extends SmoothLoader<SmoothIdType, LoadType>> {

    SmoothIdType getSmoothId();

    SmoothLoadState getLoadState();

    SmoothLoadType getLoadType();

    SmoothLoaderArgument getArgument();

    LoadType registerLoadFinishAction(Consumer<LoadType> action) throws SmoothLoadException;

    void online() throws SmoothLoadException;

}
