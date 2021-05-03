package org.misty.smooth.core.context.impl;

import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.smooth.core.error.SmoothCoreError;
import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;
import org.misty.util.tool.ArgumentParser;
import org.misty.util.verify.Examiner;
import org.misty.util.verify.Judge;

import java.util.*;
import java.util.function.Function;

public class SmoothCoreEnvironmentPreset implements SmoothCoreEnvironment {

    public static final String ELEMENT_ERROR_THROW_ACTION_FORMAT = "there is null or empty element in the %s:%s";

    public static final String ADD_ARGUMENTS_ERROR_THROW_ACTION_FORMAT = "addArguments key(%s) or value(%s) can't be null or empty";

    private final Set<String> flags = new TreeSet<>();

    private final Map<String, String> arguments = new TreeMap<>();

    private final FiBiConsumerThrow1<String, Collection<String>, MistyException> elementErrorThrowAction = (term, arg) -> {
        throw SmoothCoreError.ARGUMENT_ERROR.thrown(String.format(ELEMENT_ERROR_THROW_ACTION_FORMAT, term, arg));
    };

    @Override
    public boolean containsFlag(String flag) {
        Examiner.refuseNullAndEmpty("flag", flag, SmoothCoreError.ARGUMENT_ERROR);

        return this.flags.contains(flag);
    }

    @Override
    public boolean containsExactlyFlags(Collection<String> flags) {
        Examiner.refuseNullAndEmpty("flags", flags, SmoothCoreError.ARGUMENT_ERROR);

        if (flags.size() != this.flags.size()) {
            return false;
        }

        return containsAllFlags(flags);
    }

    @Override
    public boolean containsAllFlags(Collection<String> flags) {
        Examiner.refuseNullAndEmpty("flags", flags, SmoothCoreError.ARGUMENT_ERROR);

        boolean containsAll = true;
        for (String f : flags) {
            Examiner.refuseNullAndEmpty("flags", f, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, flags);
            });
            containsAll &= this.flags.contains(f);
        }
        return containsAll;
    }

    @Override
    public boolean containsAnyFlags(Collection<String> flags) {
        Examiner.refuseNullAndEmpty("flags", flags, SmoothCoreError.ARGUMENT_ERROR);

        boolean containsAny = false;
        for (String f : flags) {
            Examiner.refuseNullAndEmpty("flags", f, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, flags);
            });
            containsAny |= this.flags.contains(f);
        }
        return containsAny;
    }

    @Override
    public Set<String> getFlags() {
        return new TreeSet<>(this.flags);
    }

    @Override
    public boolean containsKey(String key) {
        Examiner.refuseNullAndEmpty("key", key, SmoothCoreError.ARGUMENT_ERROR);

        return this.arguments.containsKey(key);
    }

    @Override
    public boolean containsExactlyKeys(Collection<String> keys) {
        Examiner.refuseNullAndEmpty("keys", keys, SmoothCoreError.ARGUMENT_ERROR);

        if (keys.size() != this.arguments.size()) {
            return false;
        }

        return containsAllKeys(keys);
    }

    @Override
    public boolean containsAllKeys(Collection<String> keys) {
        Examiner.refuseNullAndEmpty("keys", keys, SmoothCoreError.ARGUMENT_ERROR);

        boolean containsAll = true;
        for (String k : keys) {
            Examiner.refuseNullAndEmpty("keys", k, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, keys);
            });
            containsAll &= this.arguments.containsKey(k);
        }
        return containsAll;
    }

    @Override
    public boolean containsAnyKeys(Collection<String> keys) {
        Examiner.refuseNullAndEmpty("keys", keys, SmoothCoreError.ARGUMENT_ERROR);

        boolean containsAny = false;
        for (String k : keys) {
            Examiner.refuseNullAndEmpty("keys", k, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, keys);
            });
            containsAny |= this.arguments.containsKey(k);
        }
        return containsAny;
    }

    @Override
    public boolean equalsValue(String key, String value) {
        Examiner.refuseNullAndEmpty("key", key, SmoothCoreError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty("value", value, SmoothCoreError.ARGUMENT_ERROR);

        String argValue = this.arguments.get(key);
        return value.equals(argValue);
    }

    @Override
    public boolean equalsAnyValues(String key, Collection<String> values) {
        Examiner.refuseNullAndEmpty("key", key, SmoothCoreError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty("values", values, SmoothCoreError.ARGUMENT_ERROR);

        String argValue = this.arguments.get(key);

        for (String v : values) {
            Examiner.refuseNullAndEmpty("values", v, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, values);
            });

            if (v.equals(argValue)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public <Type> Type getValue(String key, Function<String, Type> transformer) {
        String value = this.arguments.get(key);
        return transformer.apply(value);
    }

    @Override
    public Set<String> getKeys() {
        return new TreeSet<>(this.arguments.keySet());
    }

    @Override
    public Map<String, String> getArguments() {
        return new TreeMap<>(this.arguments);
    }

    @Override
    public Set<String> parseArgument(Collection<String> args) {
        if (Judge.isNullOrEmpty(args)) {
            return Collections.emptySet();
        }

        ArgumentParser argumentParser = new ArgumentParser();
        ArgumentParser.Result result = argumentParser.parse(args);
        Set<String> flags = result.getFlags();
        Map<String, String> kvPair = result.getKeyValuesPair();
        Set<String> unrecognized = result.getUnrecognized();

        addFlags(flags);
        addArguments(kvPair);

        return unrecognized;
    }

    @Override
    public void addFlag(String flag) {
        Examiner.refuseNullAndEmpty("flag", flag, SmoothCoreError.ARGUMENT_ERROR);

        this.flags.add(flag);
    }

    @Override
    public void addFlags(Collection<String> flags) {
        Examiner.refuseNullAndEmpty("flags", flags, SmoothCoreError.ARGUMENT_ERROR);

        for (String flag : flags) {
            Examiner.refuseNullAndEmpty("flags", flag, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, flags);
            });

            this.flags.add(flag);
        }
    }

    @Override
    public Optional<String> addArgument(String key, String value) {
        Examiner.refuseNullAndEmpty("key", key, SmoothCoreError.ARGUMENT_ERROR);
        Examiner.refuseNullAndEmpty("value", value, SmoothCoreError.ARGUMENT_ERROR);

        String oldValue = this.arguments.put(key, value);
        return Optional.ofNullable(oldValue);
    }

    @Override
    public Map<String, Optional<String>> addArguments(Map<String, String> args) {
        Examiner.refuseNullAndEmpty("args", args, SmoothCoreError.ARGUMENT_ERROR);

        Map<String, Optional<String>> oldValues = new TreeMap<>();

        args.forEach((k, v) -> {
            if (Judge.isNullOrEmpty(k) || Judge.isNullOrEmpty(v)) {
                throw MistyError.ARGUMENT_ERROR.thrown(String.format(ADD_ARGUMENTS_ERROR_THROW_ACTION_FORMAT, k, v));
            } else {
                Optional<String> oldValue = addArgument(k, v);
                oldValues.put(k, oldValue);
            }
        });

        return oldValues;
    }

}
