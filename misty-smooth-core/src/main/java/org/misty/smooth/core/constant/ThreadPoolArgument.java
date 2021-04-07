package org.misty.smooth.core.constant;

import org.misty.util.verify.Examiner;
import org.misty.util.verify.ExaminerOfIntRange;
import org.misty.util.verify.Judge;

import java.util.function.Function;

public class ThreadPoolArgument {

    public static final String PREFIX = SmoothCoreArgument.PREFIX + ".thread-pool";

    public static class CoreSize {
        public static final String KEY = PREFIX + ".core-size";

        public static final int PRESET = 2;

        public static final int MIX = PRESET;

        public static final int MAX = 4096;

        public static final Function<String, Integer> VERIFY_AND_TRANSFORM = (value) -> {
            if (Judge.isNullOrEmpty(value)) {
                return PRESET;
            } else {
                int i = Examiner.requireInteger(value);
                Examiner.ofRange(MIX, MAX).requireIncludeInclude(KEY, i);
                return i;
            }
        };
    }

    public static class Keys {
        //        public static final String CORE_SIZE = PREFIX + ".core-size";
        public static final String MAX_SIZE = PREFIX + ".max-size";
        public static final String ALIVE_SECOND = PREFIX + ".alive-second";
        public static final String QUEUE_SIZE = PREFIX + ".queue-size";
        public static final String NAME_PREFIX = PREFIX + ".name-prefix";
    }


}
