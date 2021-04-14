package org.misty.smooth.sole.context;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothSoleContext implements SmoothContext {

    @Override
    public String identifier() {
        return null;
    }

    @Override
    public Instant getLaunchInstant() {
        return null;
    }

    @Override
    public SmoothEnvironment getEnvironment() {
        return null;
    }

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return Collections.emptySet();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return Optional.empty();
    }

    @Override
    public Future<SmoothServiceResult> invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest) {
        return null;
    }

    @Override
    public void invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResult> resultProcessor) {

    }
}
