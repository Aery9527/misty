package org.misty.smooth.core.domain.impl;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothDomainContext implements SmoothContext {

    private SmoothId<?> smoothId;

    private Instant launchInstant;

    private SmoothContext parentContext;

    @Override
    public String getIdentifier() {
        return this.smoothId.getDescription();
    }

    @Override
    public Instant getLaunchInstant() {
        return this.launchInstant;
    }

    @Override
    public SmoothEnvironment getEnvironment() {
        return this.parentContext.getEnvironment();
    }

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return this.parentContext.listModuleWithSet();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return this.parentContext.listServiceWithSet(moduleName);
    }

    @Override
    public Future<SmoothServiceResponseResult> invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        return this.parentContext.invokeService(moduleName, serviceKey, serviceRequest);
    }

    @Override
    public void invokeService(String moduleName, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        this.parentContext.invokeService(moduleName, serviceKey, serviceRequest, resultProcessor);
    }

    public SmoothId<?> getSmoothId() {
        return smoothId;
    }

    public void setSmoothId(SmoothId<?> smoothId) {
        this.smoothId = smoothId;
    }

    public void setLaunchInstant(Instant launchInstant) {
        this.launchInstant = launchInstant;
    }

    public SmoothContext getParentContext() {
        return parentContext;
    }

    public void setParentContext(SmoothContext parentContext) {
        this.parentContext = parentContext;
    }
}
