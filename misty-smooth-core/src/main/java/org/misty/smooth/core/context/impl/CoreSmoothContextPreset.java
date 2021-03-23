package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.CoreSmoothContext;
import org.misty.smooth.manager.error.SmoothLoadException;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class CoreSmoothContextPreset implements CoreSmoothContext {

    @Override
    public Instant getLaunchInstant() {
        return null;
    }

    @Override
    public List<String> getArgument() {
        return null;
    }

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
    public void loadSmoothManager(Collection<URL> sources) throws SmoothLoadException {

    }

    @Override
    public void loadSmoothModule(Collection<URL> sources) throws SmoothLoadException {

    }
}
