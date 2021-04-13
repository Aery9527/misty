package org.misty.smooth.core.context.validator;

import org.misty.util.verify.Examiner;
import org.misty.util.verify.ExaminerOfIntRange;
import org.misty.util.verify.ExaminerOfShortRange;
import org.misty.util.verify.Judge;

import java.util.function.Function;

public class EnvironmentShortValidator implements Function<String, Short> {

    private final String key;

    private final short preset;

    private final ExaminerOfShortRange examiner;

    public EnvironmentShortValidator(String key, short preset) {
        this.key = key;
        this.preset = preset;
        this.examiner = Examiner.ofRange(Short.MIN_VALUE, Short.MAX_VALUE);
    }

    public EnvironmentShortValidator(String key, short preset, short min, short max) {
        this.key = key;
        this.preset = preset;
        this.examiner = Examiner.ofRange(min, max);
    }

    @Override
    public Short apply(String value) {
        if (Judge.isNullOrEmpty(value)) {
            return this.preset;
        } else {
            short i = Examiner.requireShort(value);
            this.examiner.requireIncludeInclude(this.key, i);
            return i;
        }
    }

}
