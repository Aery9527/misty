package org.misty.util.tool;

import org.misty.util.verify.Examiner;

import java.util.*;

public class ArgumentParser {

    public static class Result {
        private final Set<String> flags = new HashSet<>();

        private final Map<String, Set<String>> keyValuesPair = new HashMap<>();

        private final List<String> unrecognized = new ArrayList<>();

        public boolean addFlag(String flag) {
            return this.flags.add(flag);
        }

        public boolean addKeyValuePair(String key, String value) {
            Set<String> values = this.keyValuesPair.computeIfAbsent(key, k -> new HashSet<>());
            return values.add(value);
        }

        public boolean addUnrecognized(String arg) {
            return this.unrecognized.add(arg);
        }

        public Set<String> getFlags() {
            return flags;
        }

        public Map<String, Set<String>> getKeyValuesPair() {
            return keyValuesPair;
        }

        public List<String> getUnrecognized() {
            return unrecognized;
        }
    }

    public static class Quotation {
        public final String left;
        public final String right;

        public Quotation(String same) {
            this.left = same;
            this.right = same;
        }

        public Quotation(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }

    private String keyValuePairSeparator = "=";

    private Set<ArgumentParser.Quotation> trimQuotation = new HashSet<>();

    private boolean trimDeep = true;

    private boolean trimBlank = true;

    public ArgumentParser() {
        this.trimQuotation.add(new Quotation("\""));
        this.trimQuotation.add(new Quotation("'"));
    }

    public ArgumentParser.Result parse(String... args) {
        return parse(Arrays.asList(args));
    }

    public ArgumentParser.Result parse(Collection<String> args) {
        Result result = new Result();

        String flagPrefix = "--";
        int flagPrefixLength = flagPrefix.length();
        String keyValuePairPrefix = "-";
        int keyValuePairPrefixLength = keyValuePairPrefix.length();

        for (String arg : args) {
            boolean isFlag = arg.startsWith(flagPrefix);
            boolean isKeyValuePair = arg.startsWith(keyValuePairPrefix);

            if (isFlag) {
                String flag = arg.substring(flagPrefixLength);
                flag = trim(flag);
                result.addFlag(flag);

            } else if (isKeyValuePair) {
                int splitIndex = arg.indexOf(this.keyValuePairSeparator);
                int argLength = arg.length();
                boolean hasSeparator = splitIndex > 0;
                String key = arg.substring(keyValuePairPrefixLength, hasSeparator ? splitIndex : argLength);
                String value = hasSeparator ? arg.substring(splitIndex + this.keyValuePairSeparator.length(), argLength) : "";

                key = trim(key);
                value = trim(value);

                if (key.isEmpty()) {
                    result.addKeyValuePair(key, value);
                } else {
                    result.addUnrecognized(arg);
                }

            } else {
                result.addUnrecognized(arg);
            }
        }

        return result;
    }

    public String trim(String arg) {
        if (arg.isEmpty()) {
            return arg;
        }

        arg = trimQuotation(arg);
        arg = trimBlank(arg);
        return arg;
    }

    public String trimQuotation(String arg) {
        if (this.trimQuotation.isEmpty()) {
            return arg;
        }

        for (ArgumentParser.Quotation q : this.trimQuotation) {
            boolean enclosed = arg.startsWith(q.left) && arg.endsWith(q.right);
            if (!enclosed) {
                continue;
            }

            arg = arg.substring(q.left.length(), arg.length() - q.right.length());

            if (this.trimDeep) {
                arg = trim(arg);
            }
        }

        return arg;
    }

    public String trimBlank(String arg) {
        if (arg.isEmpty() || !this.trimBlank) {
            return arg;
        }

        String after = arg.trim();

        if (after.equals(arg)) {
            return arg;
        }

        if (this.trimDeep) {
            arg = trim(arg);
        }

        return arg;
    }

    public String getKeyValuePairSeparator() {
        return keyValuePairSeparator;
    }

    public void setKeyValuePairSeparator(String keyValuePairSeparator) {
        Examiner.refuseNullAndEmpty("keyValuePairSeparator", keyValuePairSeparator);
        this.keyValuePairSeparator = keyValuePairSeparator;
    }

    public Set<Quotation> getTrimQuotation() {
        return trimQuotation;
    }

    public void setTrimQuotation(Set<Quotation> trimQuotation) {
        Examiner.refuseNullAndEmpty("trimQuotation", trimQuotation);
        this.trimQuotation = trimQuotation;
    }

    public boolean isTrimDeep() {
        return trimDeep;
    }

    public void setTrimDeep(boolean trimDeep) {
        this.trimDeep = trimDeep;
    }

    public boolean isTrimBlank() {
        return trimBlank;
    }

    public void setTrimBlank(boolean trimBlank) {
        this.trimBlank = trimBlank;
    }
}
