package org.misty.smooth.core.context.impl;

import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;
import org.misty.util.tool.ArgumentParser;
import org.misty.util.verify.Examiner;
import org.misty.util.verify.Judge;

import java.util.*;

public class SmoothCoreEnvironmentPreset implements SmoothCoreEnvironment {

    public static final String ELEMENT_ERROR_THROW_ACTION_FORMAT = "there is null or empty element in the %s:%s";

    private final Set<String> flags = new HashSet<>();

    private final Map<String, Set<String>> arguments = new HashMap<>();

    private final FiBiConsumerThrow1<String, Collection<String>, MistyException> elementErrorThrowAction = (term, arg) -> {
        throw MistyError.ARGUMENT_ERROR.thrown(String.format(ELEMENT_ERROR_THROW_ACTION_FORMAT, term, arg));
    };

    @Override
    public boolean containsFlag(String flag) {
        Examiner.refuseNullAndEmpty("flag", flag);

        return this.flags.contains(flag);
    }

    @Override
    public boolean containsAllFlags(Collection<String> flags) {
        Examiner.refuseNullAndEmpty("flags", flags);

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
        Examiner.refuseNullAndEmpty("flags", flags);

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
        return new HashSet<>(this.flags);
    }

    @Override
    public boolean containsKey(String key) {
        Examiner.refuseNullAndEmpty("key", key);

        return this.arguments.containsKey(key);
    }

    @Override
    public boolean containsAllKeys(Collection<String> keys) {
        Examiner.refuseNullAndEmpty("keys", keys);

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
        Examiner.refuseNullAndEmpty("keys", keys);

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
    public boolean containsValue(String key, String value) {
        Examiner.refuseNullAndEmpty("key", key);
        Examiner.refuseNullAndEmpty("value", value);

        Set<String> argumentValues = this.arguments.getOrDefault(key, Collections.emptySet());
        return argumentValues.contains(value);
    }

    @Override
    public boolean containsAllValues(String key, Collection<String> values) {
        Examiner.refuseNullAndEmpty("key", key);
        Examiner.refuseNullAndEmpty("values", values);

        Set<String> argumentValues = this.arguments.getOrDefault(key, Collections.emptySet());

        boolean containsAll = true;
        for (String v : values) {
            Examiner.refuseNullAndEmpty("values", v, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, values);
            });
            containsAll &= argumentValues.contains(v);
        }
        return containsAll;
    }

    @Override
    public boolean containsAnyValues(String key, Collection<String> values) {
        Examiner.refuseNullAndEmpty("key", key);
        Examiner.refuseNullAndEmpty("values", values);

        Set<String> argumentValues = this.arguments.getOrDefault(key, Collections.emptySet());

        boolean containsAny = false;
        for (String v : values) {
            Examiner.refuseNullAndEmpty("values", v, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, values);
            });
            containsAny |= argumentValues.contains(v);
        }
        return containsAny;
    }

    @Override
    public Set<String> getValues(String key) {
        Examiner.refuseNullAndEmpty("key", key);

        Set<String> values = this.arguments.get(key);
        if (values == null) {
            return Collections.emptySet();
        } else {
            return new HashSet<>(values);
        }
    }

    @Override
    public Set<String> getKeys() {
        return new HashSet<>(this.arguments.keySet());
    }

    @Override
    public Map<String, Set<String>> getArguments() {
        Map<String, Set<String>> result = new HashMap<>();
        this.arguments.forEach((k, v) -> result.put(k, new HashSet<>(v)));
        return result;
    }

    @Override
    public Set<String> parseArgument(Collection<String> args) {
        if (Judge.isNullOrEmpty(args)) {
            return Collections.emptySet();
        }

        ArgumentParser argumentParser = new ArgumentParser();
        ArgumentParser.Result result = argumentParser.parse(args);
        Set<String> flags = result.getFlags();
        Map<String, Set<String>> kvPair = result.getKeyValuesPair();
        Set<String> unrecognized = result.getUnrecognized();

        addFlags(flags);
        kvPair.forEach(this::addArguments);

        return unrecognized;
    }

    @Override
    public void addFlag(String flag) {
        Examiner.refuseNullAndEmpty("flag", flag);

        this.flags.add(flag);
    }

    @Override
    public void addFlags(Collection<String> flags) {
        Examiner.refuseNullAndEmpty("flags", flags);

        for (String flag : flags) {
            Examiner.refuseNullAndEmpty("flags", flag, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, flags);
            });

            this.flags.add(flag);
        }
    }

    @Override
    public void addArgument(String key, String value) {
        Examiner.refuseNullAndEmpty("key", key);
        Examiner.refuseNullAndEmpty("value", value);

        Set<String> argumentValues = this.arguments.computeIfAbsent(key, k -> new HashSet<>());
        argumentValues.add(value);
    }

    @Override
    public void addArguments(String key, Collection<String> values) {
        Examiner.refuseNullAndEmpty("key", key);
        Examiner.refuseNullAndEmpty("values", values);

        Set<String> argumentValues = this.arguments.computeIfAbsent(key, k -> new HashSet<>());
        for (String value : values) {
            Examiner.refuseNullAndEmpty("values", value, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, values);
            });
            argumentValues.add(value);
        }
    }

    @Override
    public void addArguments(Map<String, String> args) {
        Examiner.refuseNullAndEmpty("args", args);

        args.forEach(this::addArgument);
    }

}
