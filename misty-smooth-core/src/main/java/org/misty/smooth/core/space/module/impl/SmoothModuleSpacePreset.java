package org.misty.smooth.core.space.module.impl;

import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.service.SmoothService;
import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.space.module.api.SmoothModuleSpace;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SmoothModuleSpacePreset implements SmoothModuleSpace {

    private SmoothModuleId moduleId;

    private ClassLoader moduleClassLoader;

    private SmoothLifecycle moduleLifecycle;

    private ExecutorService moduleExecutorService;

    private Map<String, SmoothServiceId> serviceKeyMapId;

    private Map<SmoothServiceId, SmoothService> serviceIdMapService;

    @Override
    public Set<SmoothServiceId> listServices() {
        return Collections.unmodifiableSet(this.serviceIdMapService.keySet());
    }

    @Override
    public Future<SmoothServiceResponseResult> invokeService(String serviceKey, SmoothServiceRequestOrigin requestOrigin)
            throws SmoothServiceNotFoundException {
        SmoothServiceId serviceId = this.serviceKeyMapId.get(serviceKey);
        if (serviceId == null) {
            throw new SmoothServiceNotFoundException(this.moduleId + ":" + serviceKey);
        }

        SmoothService smoothService = this.serviceIdMapService.get(serviceId);

        return this.moduleExecutorService.submit(() -> {
            SmoothServiceResponse response = smoothService.serve(requestOrigin);
            return new SmoothServiceResponseResult(this.moduleId, serviceId, response);
        });
    }

    @Override
    public void invokeService(String serviceKey, SmoothServiceRequestOrigin requestOrigin
            , Consumer<SmoothServiceResponseResult> resultProcessor, ExecutorService invokeExecutorService)
            throws SmoothServiceNotFoundException {
        SmoothServiceId serviceId = this.serviceKeyMapId.get(serviceKey);
        if (serviceId == null) {
            throw new SmoothServiceNotFoundException(this.moduleId + ":" + serviceKey);
        }

        SmoothService smoothService = this.serviceIdMapService.get(serviceId);

        this.moduleExecutorService.execute(() -> {
            SmoothServiceResponse response = smoothService.serve(requestOrigin);

            invokeExecutorService.execute(() -> {
                SmoothServiceResponseResult result = new SmoothServiceResponseResult(this.moduleId, serviceId, response);
                resultProcessor.accept(result);
            });
        });
    }

    @Override
    public void close() {

    }


}
