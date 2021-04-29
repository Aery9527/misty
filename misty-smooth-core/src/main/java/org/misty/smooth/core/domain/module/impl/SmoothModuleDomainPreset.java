package org.misty.smooth.core.domain.module.impl;

import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.lifecycle.SmoothLifecycle;
import org.misty.smooth.api.service.SmoothService;
import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceResponse;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.domain.module.api.SmoothModuleDomain;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Collector;

public class SmoothModuleDomainPreset implements SmoothModuleDomain {

    private static class Tuple {
        final SmoothServiceId serviceId;
        final SmoothService smoothService;

        public Tuple(SmoothServiceId serviceId, SmoothService smoothService) {
            this.serviceId = serviceId;
            this.smoothService = smoothService;
        }
    }

    private SmoothModuleId moduleId;

    private ClassLoader moduleClassLoader;

    private SmoothLifecycle moduleLifecycle;

    private ExecutorService moduleExecutorService;

    //    private Map<String, Tuple> map = new ConcurrentHashMap<>(32);
    private Map<String, Tuple> map;

    @Override
    public Set<SmoothServiceId> listServices() {
        return this.map.values().stream()
                .map((tuple) -> tuple.serviceId)
                .collect(Collector.of(
                        TreeSet::new, Set::add,
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        }
                ));
    }

    @Override
    public Future<SmoothServiceResponseResult> invokeService(String serviceKey, SmoothServiceRequestOrigin requestOrigin)
            throws SmoothServiceNotFoundException {
        Tuple tuple = getTuple(serviceKey);

        return this.moduleExecutorService.submit(() -> {
            SmoothServiceResponse response = tuple.smoothService.serve(requestOrigin);
            return new SmoothServiceResponseResult(this.moduleId, tuple.serviceId, response);
        });
    }

    @Override
    public void invokeService(String serviceKey, SmoothServiceRequestOrigin requestOrigin
            , ExecutorService invokeExecutorService, Consumer<SmoothServiceResponseResult> resultProcessor)
            throws SmoothServiceNotFoundException {
        Tuple tuple = getTuple(serviceKey);

        this.moduleExecutorService.execute(() -> {
            SmoothServiceResponse response = tuple.smoothService.serve(requestOrigin);

            invokeExecutorService.execute(() -> {
                resultProcessor.accept(new SmoothServiceResponseResult(this.moduleId, tuple.serviceId, response));
            });
        });
    }

    @Override
    public void close() {
        // FIXME
        this.map = Collections.emptyMap();
    }

    private Tuple getTuple(String serviceKey) {
        Tuple tuple = this.map.get(serviceKey);
        if (tuple == null) {
            throw new SmoothServiceNotFoundException(this.moduleId + ":" + serviceKey);
        } else {
            return tuple;
        }
    }

}
