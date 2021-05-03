package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.manager.error.SmoothCloseException;
import org.misty.smooth.manager.error.SmoothLoadException;
import org.misty.smooth.manager.loader.SmoothManagerLoader;
import org.misty.smooth.manager.loader.SmoothModuleLoader;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

import java.net.URL;
import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothCoreContextCrosser extends SmoothCrossWrapper<SmoothCoreContext> implements SmoothCoreContext {

    public SmoothCoreContextCrosser(SmoothCoreContext smoothCoreContext) {
        super(SmoothCoreContextCrosser.class.getClassLoader(), smoothCoreContext);
    }

    public SmoothCoreContextCrosser(ClassLoader wrapClassLoader, SmoothCoreContext smoothCoreContext) {
        super(wrapClassLoader, smoothCoreContext);
    }

    @Override
    public String toString() {
        return super.wrap(() -> super.getWrappedTarget().toString());
    }

    @Override
    public String getIdentifier() {
        return super.wrap(() -> super.getWrappedTarget().getIdentifier());
    }

    @Override
    public Instant getLaunchInstant() {
        return super.wrap(() -> super.getWrappedTarget().getLaunchInstant());
    }

    @Override
    public SmoothEnvironment getEnvironment() {
        return super.wrap(() -> super.getWrappedTarget().getEnvironment());
    }

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return super.wrap(() -> super.getWrappedTarget().listModuleWithSet());
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return super.wrap(() -> super.getWrappedTarget().listServiceWithSet(moduleName));
    }

    @Override
    public Future<SmoothServiceResponseResult> invokeService(
            String moduleName, String serviceKey, SmoothServiceRequest serviceRequest
    ) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        return super.wrap(() -> super.getWrappedTarget().invokeService(moduleName, serviceKey, serviceRequest));
    }

    @Override
    public void invokeService(
            String moduleName, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor
    ) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        super.wrap(() -> super.getWrappedTarget().invokeService(moduleName, serviceKey, serviceRequest, resultProcessor));
    }

    @Override
    public SmoothManagerLoader loadSmoothManager(SmoothLoaderArgument loaderArgument, Collection<URL> sources) throws SmoothLoadException {
        return super.wrap(() -> {
            try {
                return super.getWrappedTarget().loadSmoothManager(loaderArgument, sources);
            } catch (Throwable t) {
                throw SmoothLoadException.wrap(t);
            }
        });
    }

    @Override
    public SmoothModuleLoader loadSmoothModule(SmoothLoaderArgument loaderArgument, Collection<URL> sources) throws SmoothLoadException {
        return super.wrap(() -> {
            try {
                return super.getWrappedTarget().loadSmoothModule(loaderArgument, sources);
            } catch (Throwable t) {
                throw SmoothLoadException.wrap(t);
            }
        });
    }

    @Override
    public void close() throws SmoothCloseException {
        super.wrap(() -> {
            try {
                super.getWrappedTarget().close();
            } catch (Throwable t) {
                throw SmoothCloseException.wrap(t);
            }
        });
    }

}
