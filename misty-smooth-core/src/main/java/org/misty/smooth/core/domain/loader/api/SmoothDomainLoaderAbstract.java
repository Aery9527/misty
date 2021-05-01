package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.cross.SmoothCrossObject;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.core.error.SmoothCoreError;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.misty.util.tool.AtomicUpdater;
import org.misty.util.verify.Examiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public abstract class SmoothDomainLoaderAbstract<
        SmoothIdType extends SmoothId<SmoothIdType>,
        LoadType extends SmoothLoader<SmoothIdType, LoadType>,
        LifecycleType extends SmoothLifecycle
        > implements SmoothLoader<SmoothIdType, LoadType>, SmoothDomainLoader<SmoothIdType> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SmoothCrossObject loaderCrosser = new SmoothCrossObject(getClass().getClassLoader());

    private final AtomicUpdater<SmoothLoadState> loadState = new AtomicUpdater<>(SmoothLoadState.INITIAL);

    private final AtomicReference<Consumer<LoadType>> loadFinishAction = new AtomicReference<>();

    private SmoothCrossObject domainCrosser;

    private SmoothIdType smoothId;

    private SmoothLoaderArgument loaderArgument;

    private SmoothLoadType loadType;

    private LifecycleType domainLifecycle;

    private SmoothDomainLaunchThreadFactory<SmoothIdType> launchThreadFactory;

    private SmoothDomainLoadTypeController<SmoothIdType> loadTypeController;

    private Throwable currentError;

    @Override
    public void launch() {
        checkLaunchField();
        changeState(SmoothLoadState.LOADING);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(
                (runnable) -> this.launchThreadFactory.build(loaderArgument, this.smoothId, runnable)
        );

        try {
            currentError = null;

            // TODO
            changeState(SmoothLoadState.WAITING_ONLINE);
        } catch (Throwable t) {
            this.logger.error(this.smoothId + " launch error.", t);
            currentError = t;
            changeState(SmoothLoadState.LOAD_FAILED);
        }
    }

    @Override
    public void online() throws SmoothLoadException {
        this.loaderCrosser.wrap(() -> {
            try {
                checkOnlineField();
                changeState(SmoothLoadState.GOING_ONLINE);
            } catch (Exception e) {
                throw SmoothLoadException.wrap(e);
            }

            try {
                currentError = null;
                // TODO
                changeState(SmoothLoadState.ONLINE);
            } catch (Throwable t) {
                this.logger.error(this.smoothId + " online error.", t);
                currentError = t;
                changeState(SmoothLoadState.ONLINE_FAILED);
            }
        });
    }

    @Override
    public void retryLoading() throws SmoothLoadException {
        this.loaderCrosser.wrap(() -> {

        });
    }

    @Override
    public void retryOnline() throws SmoothLoadException {
        this.loaderCrosser.wrap(() -> {

        });
    }

    @Override
    public Optional<Throwable> getCurrentError() {
        return Optional.ofNullable(this.currentError);
    }

    @Override
    public LoadType registerLoadFinishAction(Consumer<LoadType> action) throws SmoothLoadException {
        return this.loaderCrosser.wrap(() -> {
            boolean firstSet = this.loadFinishAction.compareAndSet(null, action);
            if (!firstSet) {
                throw new SmoothLoadException("loadFinishAction can't set again.");
            }

            this.loadFinishAction.set(action);
            // TODO
            return (LoadType) this;
        });
    }

    private void checkLaunchField() {
        Examiner.refuseNullAndEmpty("domainCrosser", this.domainCrosser);
        Examiner.refuseNullAndEmpty("smoothId", this.smoothId);
        // TODO
    }

    private void checkOnlineField() {
        // TODO
    }

    private void changeState(SmoothLoadState nextState) {
        this.loadState.update((state) -> state.toNext(nextState, (currentState) -> {
            String msg = "can't change state from " + currentState + " to " + nextState;
            throw SmoothCoreError.DOMAIN_LOAD_STATE_ERROR.thrown(msg);
        }));
    }

    public SmoothCrossObject getDomainCrosser() {
        return domainCrosser;
    }

    public void setDomainCrosser(SmoothCrossObject domainCrosser) {
        this.domainCrosser = domainCrosser;
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
    public SmoothLoaderArgument getLoaderArgument() {
        return loaderArgument;
    }

    public void setLoaderArgument(SmoothLoaderArgument loaderArgument) {
        this.loaderArgument = loaderArgument;
    }

    @Override
    public Optional<SmoothLoadType> getLoadType() {
        return Optional.ofNullable(loadType);
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
        ClassLoader wrapClassLoader = this.loaderCrosser.getWrapClassLoader();
        this.launchThreadFactory = new SmoothDomainLaunchThreadFactoryCrossWrapper<>(wrapClassLoader, launchThreadFactory);
    }

    public SmoothDomainLoadTypeController<SmoothIdType> getLoadTypeController() {
        return loadTypeController;
    }

    @Override
    public void setLoadTypeController(SmoothDomainLoadTypeController<SmoothIdType> loadTypeController) {
        ClassLoader wrapClassLoader = this.loaderCrosser.getWrapClassLoader();
        this.loadTypeController = new SmoothDomainLoadTypeControllerCrossWrapper<>(wrapClassLoader, loadTypeController);
    }

}
