package org.misty.smooth.core.space.module.impl;

import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.service.SmoothService;
import org.misty.smooth.api.service.vo.SmoothServiceOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.space.module.api.SmoothModuleSpace;
import org.misty.util.ex.ReentrantLockEx;

import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
    public Future<SmoothServiceResult> invokeService(String serviceKey, SmoothServiceOrigin serviceOrigin)
            throws SmoothServiceNotFoundException {
        SmoothServiceId serviceId = this.serviceKeyMapId.get(serviceKey);
        if (serviceId == null) {
            throw new SmoothServiceNotFoundException(this.moduleId + ":" + serviceKey);
        }

        SmoothService smoothService = this.serviceIdMapService.get(serviceId);

        return this.moduleExecutorService.submit(() -> {
            SmoothServiceResponse response = smoothService.serve(serviceOrigin);
            return new SmoothServiceResult(this.moduleId, serviceId, response);
        });
    }

    @Override
    public void invokeService(String serviceKey, SmoothServiceOrigin serviceOrigin
            , Consumer<SmoothServiceResult> resultProcessor, ExecutorService invokeExecutorService)
            throws SmoothServiceNotFoundException {
        SmoothServiceId serviceId = this.serviceKeyMapId.get(serviceKey);
        if (serviceId == null) {
            throw new SmoothServiceNotFoundException(this.moduleId + ":" + serviceKey);
        }

        SmoothService smoothService = this.serviceIdMapService.get(serviceId);

        this.moduleExecutorService.execute(() -> {
            SmoothServiceResponse response = smoothService.serve(serviceOrigin);

            invokeExecutorService.execute(() -> {
                SmoothServiceResult result = new SmoothServiceResult(this.moduleId, serviceId, response);
                resultProcessor.accept(result);
            });
        });
    }

    @Override
    public void close() {

    }


}
