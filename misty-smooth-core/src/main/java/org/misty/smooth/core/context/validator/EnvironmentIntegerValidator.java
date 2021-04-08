package org.misty.smooth.core.context.validator;

import org.misty.util.verify.Examiner;
import org.misty.util.verify.ExaminerOfIntRange;
import org.misty.util.verify.Judge;

import java.util.function.Function;

public class EnvironmentIntegerValidator implements Function<String, Integer> {

    private final String key;

    private final int preset;

    private final ExaminerOfIntRange examiner;

    public EnvironmentIntegerValidator(String key, int preset) {
        this.key = key;
        this.preset = preset;
        this.examiner = Examiner.ofRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public EnvironmentIntegerValidator(String key, int preset, int min, int max) {
        this.key = key;
        this.preset = preset;
        this.examiner = Examiner.ofRange(min, max);
    }

    @Override
    public Integer apply(String value) {
        if (Judge.isNullOrEmpty(value)) {
            return this.preset;
        } else {
            int i = Examiner.requireInteger(value);
            this.examiner.requireIncludeInclude(this.key, i);
            return i;
        }
    }

}
