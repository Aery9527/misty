package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.SmoothServiceInvoker;
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
import java.util.Map;
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
    public String getIdentifier() {
        return super.wrap(() -> super.getWrappedTarget().getIdentifier());
    }

    @Override
    public Instant getLaunchInstant() {
        return super.wrap(() -> super.getWrappedTarget().getLaunchInstant());
    }

    @Override
    public SmoothLoadType getLoadType() {
        return super.wrap(() -> super.getWrappedTarget().getLoadType());
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
    public Map<String, Set<String>> listModuleWithMap() {
        return super.wrap(() -> super.getWrappedTarget().listModuleWithMap());
    }

    @Override
    public Optional<String> getModulePresetVersion(String moduleName) {
        return super.wrap(() -> super.getWrappedTarget().getModulePresetVersion(moduleName));
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return super.wrap(() -> super.getWrappedTarget().listServiceWithSet(moduleName));
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName, String moduleVersion) {
        return super.wrap(() -> super.getWrappedTarget().listServiceWithSet(moduleName, moduleVersion));
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName) {
        return super.wrap(() -> super.getWrappedTarget().listServiceWithMap(moduleName));
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName, String moduleVersion) {
        return super.wrap(() -> super.getWrappedTarget().listServiceWithMap(moduleName, moduleVersion));
    }

    @Override
    public SmoothServiceInvoker buildServiceInvoker(String moduleName, String serviceKey) {
        return super.wrap(() -> super.getWrappedTarget().buildServiceInvoker(moduleName, serviceKey));
    }

    @Override
    public SmoothServiceInvoker buildServiceInvoker(String moduleName, String moduleVersion, String serviceKey) {
        return super.wrap(() -> super.getWrappedTarget().buildServiceInvoker(moduleName, moduleVersion, serviceKey));
    }

    @Override
    public Future<SmoothServiceResponseResult> invoke(SmoothModuleId id, String serviceKey, SmoothServiceRequest serviceRequest)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        return super.wrap(() -> super.getWrappedTarget().invoke(id, serviceKey, serviceRequest));
    }

    @Override
    public void invoke(SmoothModuleId id, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor)
            throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        super.wrap(() -> super.getWrappedTarget().invoke(id, serviceKey, serviceRequest, resultProcessor));
    }

    @Override
    public SmoothManagerLoader loadSmoothManager(URL... sources) throws SmoothLoadException {
        return super.wrap(() -> {
            try {
                return super.getWrappedTarget().loadSmoothManager(sources);
            } catch (Throwable t) {
                throw SmoothLoadException.wrap(t);
            }
        });
    }

    @Override
    public SmoothManagerLoader loadSmoothManager(SmoothLoaderArgument loaderArgument, URL... sources) throws SmoothLoadException {
        return super.wrap(() -> {
            try {
                return super.getWrappedTarget().loadSmoothManager(loaderArgument, sources);
            } catch (Throwable t) {
                throw SmoothLoadException.wrap(t);
            }
        });
    }

    @Override
    public SmoothManagerLoader loadSmoothManager(Collection<URL> sources) throws SmoothLoadException {
        return super.wrap(() -> {
            try {
                return super.getWrappedTarget().loadSmoothManager(sources);
            } catch (Throwable t) {
                throw SmoothLoadException.wrap(t);
            }
        });
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
    public SmoothModuleLoader loadSmoothModule(URL... sources) throws SmoothLoadException {
        return super.wrap(() -> {
            try {
                return super.getWrappedTarget().loadSmoothModule(sources);
            } catch (Throwable t) {
                throw SmoothLoadException.wrap(t);
            }
        });
    }

    @Override
    public SmoothModuleLoader loadSmoothModule(SmoothLoaderArgument loaderArgument, URL... sources) throws SmoothLoadException {
        return super.wrap(() -> {
            try {
                return super.getWrappedTarget().loadSmoothModule(loaderArgument, sources);
            } catch (Throwable t) {
                throw SmoothLoadException.wrap(t);
            }
        });
    }

    @Override
    public SmoothModuleLoader loadSmoothModule(Collection<URL> sources) throws SmoothLoadException {
        return super.wrap(() -> {
            try {
                return super.getWrappedTarget().loadSmoothModule(sources);
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
