package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.api.cross.SmoothCrosser;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.core.error.SmoothDomainLoadError;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothLoader;
import org.misty.smooth.manager.loader.enums.SmoothLoadFinishState;
import org.misty.smooth.manager.loader.enums.SmoothLoadState;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.misty.util.tool.AtomicUpdater;
import org.misty.util.verify.Examiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class SmoothDomainLoaderAbstract<
        SmoothIdType extends SmoothId<SmoothIdType>,
        LoaderType extends SmoothLoader<SmoothIdType, LoaderType>,
        LifecycleType extends SmoothLifecycle
        > implements SmoothLoader<SmoothIdType, LoaderType>, SmoothDomainLoader<SmoothIdType> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicUpdater<SmoothLoadState> loadState = new AtomicUpdater<>(SmoothLoadState.INITIAL);

    private final AtomicReference<BiConsumer<SmoothLoadFinishState, LoaderType>> loadFinishAction = new AtomicReference<>();

    private SmoothCrosser domainCrosser;

    private SmoothIdType smoothId;

    private SmoothLoaderArgument loaderArgument;

    private LifecycleType domainLifecycle;

    private SmoothContext parentContext;

    private SmoothDomainLaunchThreadFactory<SmoothIdType> launchThreadFactory;

    private SmoothDomainLoadTypeController<SmoothIdType> loadTypeController;

    /**
     * thread context classloader is smooth-core's classloader
     */
    private ExecutorService launchThread; // FIXME 要在載入完成後, 或載入失敗後要關閉

    private SmoothLoadType loadType;

    private Throwable currentError;

    @Override
    public void launch() {
        checkLaunchField();
        changeState(SmoothLoadState.LOADING);
        startLaunchThread();

        this.loadType = null;
        this.currentError = null;

        Consumer<Runnable> errorWrapper = (runnable) -> {
            try {
                runnable.run();
            } catch (Throwable t) {
                this.logger.error(this.smoothId + " launch error.", t);
                this.currentError = t;
                changeState(SmoothLoadState.LOAD_FAILED);
            }
        };

        Runnable launchAction = () -> {
            SmoothLoadType loadType = this.loadTypeController.prepareLoading(this.loaderArgument, this.smoothId);
            Examiner.refuseNullAndEmpty("loadType", this.domainCrosser, SmoothDomainLoadError.UNEXPECTED);

            this.loadType = loadType;
            if (this.loadType.isIgnore()) {
                changeState(SmoothLoadState.LOAD_IGNORE);
                destroy();
                return;
            }

            initialLifecycle(this.domainLifecycle, this.loadType);
            changeState(SmoothLoadState.WAITING_ONLINE);
        };

        errorWrapper.accept(() -> {
            this.launchThread.execute(() -> {
                errorWrapper.accept(launchAction);
            });
        });
    }

    @Override
    public void online() throws SmoothLoadException {
        try {
            checkOnlineField();
            changeState(SmoothLoadState.GOING_ONLINE);
        } catch (Exception e) {
            throw SmoothLoadException.wrap(e);
        }

        try {
            this.currentError = null;
            // TODO
            changeState(SmoothLoadState.ONLINE);
        } catch (Throwable t) {
            this.logger.error(this.smoothId + " online error.", t);
            this.currentError = t;
            changeState(SmoothLoadState.ONLINE_FAILED);
        }
    }

    @Override
    public void retryLoad() throws SmoothLoadException {

    }

    @Override
    public void retryOnline() throws SmoothLoadException {

    }

    @Override
    public void destroy() {
        changeState(SmoothLoadState.DESTROYING);
        // TODO
        changeState(SmoothLoadState.DESTROYED);
    }

    @Override
    public LoaderType registerLoadFinishAction(BiConsumer<SmoothLoadFinishState, LoaderType> action) throws SmoothLoadException {
        Examiner.refuseNullAndEmpty("loadFinishAction", action, SmoothDomainLoadError.UNEXPECTED);

        ClassLoader wrapClassLoader = Thread.currentThread().getContextClassLoader();
        SmoothCrosser crosser = new SmoothCrosser(wrapClassLoader);
        BiConsumer<SmoothLoadFinishState, LoaderType> actionWrapper = (finishState, loader) -> {
            try {
                crosser.wrap(() -> action.accept(finishState, loader));
            } catch (Throwable t) {
                this.logger.error(this.smoothId + " loadFinishAction error.", t);
            }
        };

        boolean firstSet = this.loadFinishAction.compareAndSet(null, actionWrapper);
        if (!firstSet) {
            throw SmoothDomainLoadError.LOAD_FINISH_ACTION_REGISTER.thrown("loadFinishAction can't set again.");
        }

        this.launchThread.execute(this::doLoadFinishAction);

        return (LoaderType) this;
    }

    protected abstract void initialLifecycle(LifecycleType domainLifecycle, SmoothLoadType loadType);

    private void checkLaunchField() {
        Examiner.refuseNullAndEmpty("domainCrosser", this.domainCrosser, SmoothDomainLoadError.UNEXPECTED);
        Examiner.refuseNullAndEmpty("smoothId", this.smoothId, SmoothDomainLoadError.UNEXPECTED);
        Examiner.refuseNullAndEmpty("loaderArgument", this.loaderArgument, SmoothDomainLoadError.UNEXPECTED);
        Examiner.refuseNullAndEmpty("domainLifecycle", this.domainLifecycle, SmoothDomainLoadError.UNEXPECTED);
        Examiner.refuseNullAndEmpty("parentContext", this.parentContext, SmoothDomainLoadError.UNEXPECTED);
        Examiner.refuseNullAndEmpty("launchThreadFactory", this.launchThreadFactory, SmoothDomainLoadError.UNEXPECTED);
        Examiner.refuseNullAndEmpty("loadTypeController", this.loadTypeController, SmoothDomainLoadError.UNEXPECTED);
    }

    private void checkOnlineField() {
        // TODO
    }

    private void startLaunchThread() {
        if (this.launchThread != null) {
            return;
        }

        this.launchThread = Executors.newSingleThreadExecutor((runnable) -> {
            Thread thread = this.launchThreadFactory.build(loaderArgument, this.smoothId, runnable);
            thread.setContextClassLoader(SmoothDomainLoaderAbstract.class.getClassLoader());
            return thread;
        });
    }

    private void changeState(SmoothLoadState nextState) {
        this.loadState.update((state) -> state.toNext(nextState, (currentState) -> {
            String msg = "can't change state from " + currentState + " to " + nextState;
            throw SmoothDomainLoadError.LOAD_STATE_WRONG.thrown(msg);
        }));

        doLoadFinishAction();
    }

    private void doLoadFinishAction() {
        SmoothLoadState loadState = this.loadState.get();
        Optional<SmoothLoadFinishState> loadFinishState = SmoothLoadFinishState.fromLoadState(loadState);
        if (!loadFinishState.isPresent()) {
            return;
        }

        BiConsumer<SmoothLoadFinishState, LoaderType> action = this.loadFinishAction.get();
        if (action == null) {
            return;
        }

        // FIXME loader要cross過
        action.accept(loadFinishState.get(), (LoaderType) this);
    }

    public SmoothCrosser getDomainCrosser() {
        return domainCrosser;
    }

    public void setDomainCrosser(SmoothCrosser domainCrosser) {
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

    public SmoothContext getParentContext() {
        return parentContext;
    }

    public void setParentContext(SmoothContext parentContext) {
        this.parentContext = parentContext;
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

    @Override
    public SmoothLoadState getLoadState() {
        return this.loadState.get();
    }

    @Override
    public Optional<SmoothLoadType> getLoadType() {
        return Optional.ofNullable(this.loadType);
    }

    @Override
    public Optional<Throwable> getCurrentError() {
        return Optional.ofNullable(this.currentError);
    }

}
