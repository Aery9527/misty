package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.error.SmoothModuleNotFoundException;
import org.misty.smooth.api.vo.SmoothModuleId;
import org.misty.smooth.api.vo.SmoothServiceId;
import org.misty.smooth.core.context.api.SmoothModuleDomainCamp;
import org.misty.smooth.core.domain.module.api.SmoothModuleDomain;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;
import org.misty.util.verify.Judge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SmoothModuleDomainCampPreset implements SmoothModuleDomainCamp {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int defaultInitialCapacity = 8;

    private final Instant useless = Instant.now();

    private Map<SmoothModuleId, SmoothModuleDomain> domainMap = new ConcurrentHashMap<>(this.defaultInitialCapacity);

    @Override
    public String getPresetVersion() {
        return SmoothModuleId.PRESET_VERSION;
    }

    @Override
    public Set<SmoothModuleId> listModuleWithSet() {
        if (this.domainMap.isEmpty()) {
            return Collections.emptySet();
        }

        Set<SmoothModuleId> set = new TreeSet<>(this.domainMap.keySet());
        return Collections.unmodifiableSet(set);
    }

    @Override
    public Map<String, Set<String>> listModuleWithMap() {
        if (this.domainMap.isEmpty()) {
            return Collections.emptyMap();
        }

        return this.domainMap.keySet().stream()
                .reduce(new TreeMap<>(), (map, moduleId) -> {
                    Set<String> versionSet = new HashSet<>();
                    versionSet.add(moduleId.getModuleVersion());
                    map.put(moduleId.getModuleName(), versionSet);
                    return map;
                }, (m1, m2) -> {
                    m2.forEach((moduleName, versionSet) -> {
                        Set<String> mergeSet = m1.computeIfAbsent(moduleName, k -> new TreeSet<>());
                        mergeSet.addAll(versionSet);
                    });
                    return m1;
                });
    }

    @Override
    public Optional<String> getModulePresetVersion(String moduleName) {
        if (Judge.isNullOrEmpty(moduleName)) {
            return Optional.empty();
        }

        SmoothModuleId id = buildModuleId(moduleName);
        SmoothModuleDomain moduleDomain = this.domainMap.get(id);
        if (moduleDomain == null) {
            return Optional.empty();
        } else {
            SmoothModuleId smoothId = moduleDomain.getSmoothId();
            return Optional.of(smoothId.getModuleVersion());
        }
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName) {
        return listServiceWithSet(moduleName, getPresetVersion());
    }

    @Override
    public Optional<Set<SmoothServiceId>> listServiceWithSet(String moduleName, String moduleVersion) {
        if (Judge.isNullOrEmpty(moduleName) || Judge.isNullOrEmpty(moduleVersion)) {
            return Optional.empty();
        }

        SmoothModuleId id = buildModuleId(moduleName, moduleVersion);
        SmoothModuleDomain moduleDomain = this.domainMap.get(id);
        if (moduleDomain == null) {
            return Optional.empty();
        } else {
            return Optional.of(moduleDomain.listServices());
        }
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName) {
        return listServiceWithMap(moduleName, getPresetVersion());
    }

    @Override
    public Optional<Map<String, String>> listServiceWithMap(String moduleName, String moduleVersion) {
        Optional<Set<SmoothServiceId>> op = listServiceWithSet(moduleName, moduleVersion);
        if (!op.isPresent()) {
            return Optional.empty();
        }

        Set<SmoothServiceId> set = op.get();
        return Optional.of(set.stream().reduce(new TreeMap<>(), (map, serviceId) -> {
            map.put(serviceId.getServiceKey(), serviceId.getServiceName());
            return map;
        }, (m1, m2) -> {
            m1.putAll(m2);
            return m1;
        }));
    }

    @Override
    public SmoothModuleDomain getModuleDomain(String moduleName) throws SmoothModuleNotFoundException {
        return getModuleDomain(moduleName, getPresetVersion());
    }

    @Override
    public SmoothModuleDomain getModuleDomain(String moduleName, String moduleVersion) throws SmoothModuleNotFoundException {
        SmoothModuleId id = buildModuleId(moduleName, moduleVersion);
        return getModuleDomain(id);
    }

    @Override
    public SmoothModuleDomain getModuleDomain(SmoothModuleId id) throws SmoothModuleNotFoundException {
        SmoothModuleDomain moduleDomain = this.domainMap.get(id);
        if (moduleDomain == null) {
            throw new SmoothModuleNotFoundException(id.getDescription());
        } else {
            return moduleDomain;
        }
    }

    @Override
    public void close() {
        try {
            Iterator<Map.Entry<SmoothModuleId, SmoothModuleDomain>> iterator = this.domainMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<SmoothModuleId, SmoothModuleDomain> entry = iterator.next();
                SmoothModuleDomain moduleDomain = entry.getValue();

                try {
                    moduleDomain.close();
                } catch (Exception e) {
                    SmoothModuleId id = entry.getKey();
                    this.logger.error("close error : " + id, e);
                } finally {
                    iterator.remove();
                }
            }

        } finally {
            this.domainMap = Collections.emptyMap();
        }
    }

    @Override
    public SmoothLoadType prepareLoading(SmoothLoaderArgument loaderArgument, SmoothModuleId smoothId) {
        // TODO
        return null;
    }

    @Override
    public boolean releaseWaitForOnline(SmoothModuleId smoothId) {
        // TODO
        return true;
    }

    protected SmoothModuleId buildModuleId(String name) {
        return buildModuleId(name, getPresetVersion());
    }

    protected SmoothModuleId buildModuleId(String name, String version) {
        return new SmoothModuleId(name, version, this.useless);
    }

}
