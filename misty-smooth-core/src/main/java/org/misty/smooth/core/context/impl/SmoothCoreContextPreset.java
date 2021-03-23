package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.SmoothCoreContext;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.manager.error.SmoothLoadException;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothCoreContextPreset implements SmoothCoreContext {

    private final Instant launchInstant = Instant.now();

    private SmoothCoreEnvironment environment = new SmoothCoreEnvironmentPreset();

    @Override
    public Instant getLaunchInstant() {
        return this.launchInstant;
    }

    @Override
    public SmoothCoreEnvironment getEnvironment() {
        return this.environment;
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

    @Override
    public void close() throws IOException {

    }

    //

    public void setEnvironment(SmoothCoreEnvironment environment) {
        this.environment = environment;
    }


}
