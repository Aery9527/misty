package org.misty.smooth.core.space.module.api;

import org.misty.smooth.api.error.SmoothServiceNotFoundException;
import org.misty.smooth.api.service.vo.SmoothServiceOrigin;
import org.misty.smooth.api.service.vo.SmoothServiceRequest;
import org.misty.smooth.api.service.vo.SmoothServiceResult;
import org.misty.smooth.api.vo.SmoothServiceId;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface SmoothModuleSpace {

    Set<SmoothServiceId> listServices();

    Future<SmoothServiceResult> invokeService(String serviceKey, SmoothServiceOrigin serviceOrigin)
            throws SmoothServiceNotFoundException;

    void invokeService(String serviceKey, SmoothServiceOrigin serviceOrigin
            , Consumer<SmoothServiceResult> resultProcessor, ExecutorService invokeExecutorService)
            throws SmoothServiceNotFoundException;

    void close();

}
