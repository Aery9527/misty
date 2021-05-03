package org.misty.smooth.core.context.impl;

import org.misty.smooth.api.context.SmoothEnvironment;
import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.core.context.api.SmoothCoreEnvironment;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * only wrap {@link SmoothEnvironment} method
 */
public class SmoothCoreEnvironmentCrosser extends SmoothCrossWrapper<SmoothCoreEnvironment> implements SmoothCoreEnvironment {

    public SmoothCoreEnvironmentCrosser(SmoothCoreEnvironment smoothCoreEnvironment) {
        super(SmoothCoreEnvironmentCrosser.class.getClassLoader(), smoothCoreEnvironment);
    }

    public SmoothCoreEnvironmentCrosser(ClassLoader wrapClassLoader, SmoothCoreEnvironment smoothCoreEnvironment) {
        super(wrapClassLoader, smoothCoreEnvironment);
    }

    @Override
    public boolean containsFlag(String flag) {
        return super.wrap(() -> super.getWrappedTarget().containsFlag(flag));
    }

    @Override
    public boolean containsExactlyFlags(Collection<String> flags) {
        return super.wrap(() -> super.getWrappedTarget().containsExactlyFlags(flags));
    }

    @Override
    public boolean containsAllFlags(Collection<String> flags) {
        return super.wrap(() -> super.getWrappedTarget().containsAllFlags(flags));
    }

    @Override
    public boolean containsAnyFlags(Collection<String> flags) {
        return super.wrap(() -> super.getWrappedTarget().containsAnyFlags(flags));
    }

    @Override
    public Set<String> getFlags() {
        return super.wrap(() -> super.getWrappedTarget().getFlags());
    }

    @Override
    public boolean containsKey(String key) {
        return super.wrap(() -> super.getWrappedTarget().containsKey(key));
    }

    @Override
    public boolean containsExactlyKeys(Collection<String> keys) {
        return super.wrap(() -> super.getWrappedTarget().containsExactlyKeys(keys));
    }

    @Override
    public boolean containsAllKeys(Collection<String> keys) {
        return super.wrap(() -> super.getWrappedTarget().containsAllKeys(keys));
    }

    @Override
    public boolean containsAnyKeys(Collection<String> keys) {
        return super.wrap(() -> super.getWrappedTarget().containsAnyKeys(keys));
    }

    @Override
    public boolean equalsValue(String key, String value) {
        return super.wrap(() -> super.getWrappedTarget().equalsValue(key, value));
    }

    @Override
    public boolean equalsAnyValues(String key, Collection<String> values) {
        return super.wrap(() -> super.getWrappedTarget().equalsAnyValues(key, values));
    }

    @Override
    public <Type> Type getValue(String key, Function<String, Type> transformer) {
        return super.wrap(() -> super.getWrappedTarget().getValue(key, transformer));
    }

    @Override
    public Set<String> getKeys() {
        return super.wrap(() -> super.getWrappedTarget().getKeys());
    }

    @Override
    public Map<String, String> getArguments() {
        return super.wrap(() -> super.getWrappedTarget().getArguments());
    }

    @Override
    public Set<String> parseArgument(Collection<String> args) {
        return super.wrap(() -> super.getWrappedTarget().parseArgument(args));
    }

    @Override
    public void addFlag(String flag) {
        super.getWrappedTarget().addFlag(flag);
    }

    @Override
    public void addFlags(Collection<String> flags) {
        super.getWrappedTarget().addFlags(flags);
    }

    @Override
    public Optional<String> addArgument(String key, String value) {
        return super.getWrappedTarget().addArgument(key, value);
    }

    @Override
    public Map<String, Optional<String>> addArguments(Map<String, String> args) {
        return super.getWrappedTarget().addArguments(args);
    }

}
