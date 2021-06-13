package org.misty.smooth.sole.context;

import org.misty.smooth.api.context.SmoothContext;
import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.SmoothServiceInvoker;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothSoleContext implements SmoothContext {

    @Override
    public String getIdentifier() {
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
        return null;
    }

    @Override
    public Map<String, Set<String>> listModuleWithMap() {
        return null;
    }

    @Override
    public Optional<String> getModulePresetVersion(String moduleName) {
        return Optional.empty();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return Optional.empty();
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName, String moduleVersion) {
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName) {
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName, String moduleVersion) {
        return Optional.empty();
    }

    @Override
    public SmoothServiceInvoker buildServiceInvoker(String moduleName, String moduleVersion, String serviceKey) {
        return null;
    }

    @Override
    public void invoke(SmoothModuleId id, String serviceKey, SmoothServiceRequest serviceRequest, Consumer<SmoothServiceResponseResult> resultProcessor) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {

    }

    @Override
    public Future<SmoothServiceResponseResult> invoke(SmoothModuleId id, String serviceKey, SmoothServiceRequest serviceRequest) throws SmoothModuleNotFoundException, SmoothServiceNotFoundException {
        return null;
    }

    @Override
    public SmoothServiceInvoker buildServiceInvoker(String moduleName, String serviceKey) {
        return null;
    }

}
