package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.SmoothModuleDomainCamp;
import org.misty.smooth.core.domain.module.api.SmoothModuleDomain;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;

public class SmoothModuleDomainCampPreset implements SmoothModuleDomainCamp {

    private static class Tuple {
        final SmoothModuleId moduleId;
        final SmoothModuleDomain moduleDomain;

        public Tuple(SmoothModuleId moduleId, SmoothModuleDomain moduleDomain) {
            this.moduleId = moduleId;
            this.moduleDomain = moduleDomain;
        }
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, Tuple> map = new ConcurrentHashMap<>(8);

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        return this.map.values().stream()
                .map((tuple) -> tuple.moduleId)
                .collect(Collector.of(
                        TreeSet::new, Set::add,
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        }
                ));
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        Tuple tuple = this.map.get(moduleName);
        if (tuple == null) {
            return Optional.empty();
        }

        Set<SmoothServiceId> serviceIds = tuple.moduleDomain.listServices();
        return Optional.of(serviceIds);
    }

    @Override
    public SmoothModuleDomain getModuleDomain(String moduleName) throws SmoothModuleNotFoundException {
        Tuple tuple = this.map.get(moduleName);
        if (tuple == null) {
            throw new SmoothModuleNotFoundException(moduleName);
        }

        return tuple.moduleDomain;
    }

    @Override
    public void close() {
        try {
            Iterator<Map.Entry<String, Tuple>> iterator = this.map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Tuple> entry = iterator.next();
                Tuple tuple = entry.getValue();

                try {
                    tuple.moduleDomain.close();
                } catch (Exception e) {
                    this.logger.error(tuple.moduleId + " close error.", e);
                } finally {
                    iterator.remove();
                }
            }

        } finally {
            this.map = Collections.emptyMap();
        }
    }

    @Override
    public SmoothLoadType prepareLoading(SmoothLoaderArgument loaderArgument, SmoothModuleId smoothId) {
        // TODO
        return null;
    }

    @Override
    public void release(SmoothModuleId smoothId) {
        // TODO
    }

}
