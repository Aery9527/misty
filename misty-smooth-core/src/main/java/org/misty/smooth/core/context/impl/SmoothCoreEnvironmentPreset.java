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

    private final Set<String> flags = new HashSet<>();

    private final Map<String, Set<String>> arguments = new HashMap<>();

    private FiBiConsumerThrow1<String, Collection<String>, MistyException> elementErrorThrowAction = (term, arg) -> {
        throw MistyError.ARGUMENT_ERROR.thrown("there is null or empty element in the " + term + ":" + arg);
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
    public Set<String> listFlags() {
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
    public Optional<Set<String>> getValues(String key) {
        Examiner.refuseNullAndEmpty("key", key);

        Set<String> values = this.arguments.get(key);
        if (values == null) {
            return Optional.empty();
        } else {
            return Optional.of(new HashSet<>(values));
        }
    }

    @Override
    public Optional<String> getValue(String key) {
        Examiner.refuseNullAndEmpty("key", key);

        Set<String> values = this.arguments.get(key);
        if (values == null || values.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(values.iterator().next());
        }
    }

    @Override
    public Set<String> listKeys() {
        return new HashSet<>(this.arguments.keySet());
    }

    @Override
    public Map<String, Set<String>> listArguments() {
        Map<String, Set<String>> result = new HashMap<>();
        this.arguments.forEach((k, v) -> result.put(k, new HashSet<>(v)));
        return result;
    }

    @Override
    public List<String> parseArgument(Collection<String> args) {
        if (Judge.isNullOrEmpty(args)) {
            return Collections.emptyList();
        }

        List<String> ignoreList = new ArrayList<>();

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parse(args, (rawArg, flag) -> {
            boolean added = addFlag(flag);
            if (!added) {
                ignoreList.add(rawArg);
            }

        }, (rawArg, keyValuesPair) -> {
            boolean added = addArgument(keyValuesPair.key, keyValuesPair.value);
            if (!added) {
                ignoreList.add(rawArg);
            }

        }, ignoreList::add);

        return ignoreList;
    }

    @Override
    public boolean addFlag(String flag) {
        Examiner.refuseNullAndEmpty("flag", flag);

        if (flag.startsWith(SmoothCoreEnvironment.ARGUMENT_PREFIX)) {
            this.flags.add(flag);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> addFlags(Collection<String> flags) {
        Examiner.refuseNullAndEmpty("flags", flags);

        List<String> noAddedFlags = new ArrayList<>();
        for (String f : flags) {
            Examiner.refuseNullAndEmpty("flags", f, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, flags);
            });

            boolean added = addFlag(f);
            if (!added) {
                noAddedFlags.add(f);
            }
        }
        return noAddedFlags;
    }

    @Override
    public boolean addArgument(String key, String value) {
        Examiner.refuseNullAndEmpty("key", key);
        Examiner.refuseNullAndEmpty("value", value);

        if (key.startsWith(SmoothCoreEnvironment.ARGUMENT_PREFIX)) {
            Set<String> argumentValues = this.arguments.computeIfAbsent(key, k -> new HashSet<>());
            argumentValues.add(value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addArguments(String key, List<String> values) {
        Examiner.refuseNullAndEmpty("key", key);
        Examiner.refuseNullAndEmpty("values", values);

        if (!key.startsWith(SmoothCoreEnvironment.ARGUMENT_PREFIX)) {
            return false;
        }

        Set<String> argumentValues = this.arguments.computeIfAbsent(key, k -> new HashSet<>());
        for (String v : values) {
            Examiner.refuseNullAndEmpty("values", v, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, values);
            });
            argumentValues.add(v);
        }

        return true;
    }

    public FiBiConsumerThrow1<String, Collection<String>, MistyException> getElementErrorThrowAction() {
        return elementErrorThrowAction;
    }

    public void setElementErrorThrowAction(FiBiConsumerThrow1<String, Collection<String>, MistyException> elementErrorThrowAction) {
        Examiner.refuseNullAndEmpty("elementErrorThrowAction", elementErrorThrowAction);
        this.elementErrorThrowAction = elementErrorThrowAction;
    }

}
