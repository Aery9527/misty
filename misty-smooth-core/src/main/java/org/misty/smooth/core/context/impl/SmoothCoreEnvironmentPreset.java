package org.misty.smooth.core.context.impl;

import org.misty.smooth.core.context.api.SmoothCoreEnvironment;
import org.misty.util.error.MistyError;
import org.misty.util.error.MistyException;
import org.misty.util.fi.FiBiConsumerThrow1;
import org.misty.util.verify.Examiner;

import java.util.*;

public class SmoothCoreEnvironmentPreset implements SmoothCoreEnvironment {

    private final Set<String> flags = new HashSet<>();

    private final Map<String, Set<String>> arguments = new HashMap<>();

    private FiBiConsumerThrow1<String, Collection<String>, MistyException> elementErrorThrowAction = (term, arg) -> {
        throw MistyError.ARGUMENT_ERROR.thrown("there is null or empty element in the " + term + " " + arg);
    };

    @Override
    public boolean containsFlag(String key) {
        Examiner.refuseNullAndEmpty("key", key);
        return this.flags.contains(key);
    }

    @Override
    public boolean containsAllFlags(Collection<String> keys) {
        boolean containsAll = true;

        for (String k : keys) {
            Examiner.refuseNullAndEmpty("keys", k, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, keys);
            });

            containsAll &= this.flags.contains(k);
        }

        return containsAll;
    }

    @Override
    public boolean containsAnyFlags(Collection<String> keys) {
        boolean containsAny = false;

        for (String k : keys) {
            Examiner.refuseNullAndEmpty("keys", k, (term, arg) -> {
                this.elementErrorThrowAction.acceptOrHandle(term, keys);
            });

            containsAny |= this.flags.contains(k);
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
    public boolean containsAnyKey(Collection<String> keys) {
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
        // TODO

        return Collections.emptyList();
    }

    @Override
    public boolean addFlag(String key) {
        Examiner.refuseNullAndEmpty("key", key);
        if (key.startsWith(SmoothCoreEnvironment.ARGUMENT_PREFIX)) {
            this.flags.add(key);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addArgument(String key, List<String> values) {
        Examiner.refuseNullAndEmpty("key", key);
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
