package org.misty.smooth.core.space.module.api;

import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.vo.SmoothServiceRequestOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceResponseResult;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface SmoothModuleSpace {

    Set<SmoothServiceId> listServices();

    Future<SmoothServiceResponseResult> invokeService(String serviceKey, SmoothServiceRequestOrigin requestOrigin)
            throws SmoothServiceNotFoundException;

    void invokeService(String serviceKey, SmoothServiceRequestOrigin requestOrigin
            , Consumer<SmoothServiceResponseResult> resultProcessor, ExecutorService invokeExecutorService)
            throws SmoothServiceNotFoundException;

    void close();

}
