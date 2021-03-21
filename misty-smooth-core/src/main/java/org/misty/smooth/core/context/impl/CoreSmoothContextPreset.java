package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.CoreSmoothContext;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class CoreSmoothContextPreset implements CoreSmoothContext {

    @Override
    public Optional<Set<SmoothModuleId>> listModuleWithSet() {
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, String>> listModuleWithMap() {
        return Optional.empty();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName) {
        return Optional.empty();
    }

    @Override
    public Future<SmoothServiceResult> invokeService(String moduleName, String serviceId, SmoothServiceRequest serviceRequest) {
        return null;
    }

    @Override
    public void invokeService(String moduleName, String serviceId, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResult> resultProcessor) {

    }

    @Override
    public void close() throws IOException {

    }

}
