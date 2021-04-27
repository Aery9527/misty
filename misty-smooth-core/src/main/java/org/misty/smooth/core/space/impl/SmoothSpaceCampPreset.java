package org.misty.smooth.core.space.impl;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.space.api.SmoothSpaceCamp;
import org.misty.smooth.core.space.module.api.SmoothModuleSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;

public class SmoothSpaceCampPreset implements SmoothSpaceCamp {

    private static class Tuple {
        final SmoothModuleId moduleId;
        final SmoothModuleSpace moduleSpace;

        public Tuple(SmoothModuleId moduleId, SmoothModuleSpace moduleSpace) {
            this.moduleId = moduleId;
            this.moduleSpace = moduleSpace;
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

        Set<SmoothServiceId> serviceIds = tuple.moduleSpace.listServices();
        return Optional.of(serviceIds);
    }

    @Override
    public SmoothModuleSpace getModuleSpace(String moduleName) throws SmoothModuleNotFoundException {
        Tuple tuple = this.map.get(moduleName);
        if (tuple == null) {
            throw new SmoothModuleNotFoundException(moduleName);
        }

        return tuple.moduleSpace;
    }

    @Override
    public void close() {
        Iterator<Map.Entry<String, Tuple>> iterator = this.map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Tuple> entry = iterator.next();
            Tuple tuple = entry.getValue();

            try {
                tuple.moduleSpace.close();
            } catch (Throwable t) {
                this.logger.error(tuple.moduleId + " close error.", t);
            } finally {
                iterator.remove();
            }
        }

        this.map = Collections.emptyMap();
    }

}
