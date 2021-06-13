package org.misty.smooth.core.domain.impl;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.context.SmoothLoadType;
import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.mark.SmoothGuide;
import org.misty.smooth.api.service.SmoothServiceInvoker;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.impl.SmoothEnvironmentCrosser;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Consumer;

@SmoothGuide(needCross = false,
        implementationBy = SmoothGuide.Domain.CORE,
        usedBy = SmoothGuide.Domain.MODULE
)
public class SmoothDomainContext implements SmoothContext {

    private SmoothId<?> smoothId;

    private Instant launchInstant;

    private SmoothLoadType loadType;

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
    public SmoothLoadType getLoadType() {
        return this.loadType;
    }

    @Override
    public SmoothEnvironment getEnvironment() {
        return new SmoothEnvironmentCrosser(this.parentContext.getEnvironment());
    }

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return this.parentContext.listModuleWithSet();
    }

    @Override
    public Map<String, Set<String>> listModuleWithMap() {
        return this.parentContext.listModuleWithMap();
    }

    @Override
    public Optional<String> getModulePresetVersion(String moduleName) {
        return this.parentContext.getModulePresetVersion(moduleName);
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return this.parentContext.listServiceWithSet(moduleName);
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName, String moduleVersion) {
        return this.parentContext.listServiceWithSet(moduleName, moduleVersion);
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName) {
        return this.parentContext.listServiceWithMap(moduleName);
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName, String moduleVersion) {
        return this.parentContext.listServiceWithMap(moduleName, moduleVersion);
    }

    @Override
    public SmoothServiceInvoker buildServiceInvoker(String moduleName, String serviceKey) {
        return null; // FIXME
    }

    @Override
    public SmoothServiceInvoker buildServiceInvoker(String moduleName, String moduleVersion, String serviceKey) {
        return null; // FIXME
    }

    @Override
    public void invoke(SmoothModuleId id, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        // FIXME
    }

    @Override
    public Future<SmoothServiceResponseResult> invoke(SmoothModuleId id, String serviceKey, SmoothServiceRequest serviceRequest) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        return null; // FIXME
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
