package org.misty.smooth.sole.context;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothSoleContext implements SmoothContext {

    @Override
    public Instant getLaunchInstant() {
        return null;
    }

    @Override
    public SmoothEnvironment getEnvironment() {
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
}
