package org.misty.smooth.core.constant;

import org.misty.util.verify.Examiner;
import org.misty.util.verify.ExaminerOfIntRange;
import org.misty.util.verify.Judge;

import java.util.function.Function;

public class ThreadPoolArgument {

    public static final String PREFIX = SmoothCoreArgument.PREFIX + ".thread-pool";

    public static class CoreSize {
        public static final String KEY = PREFIX + ".core-size";

        public static final int PRESET = 1024;

        public static final Function<String, Integer> VERIFY_AND_TRANSFORM = (value) -> {
            if (Judge.isNullOrEmpty(value)) {
                return PRESET;
            }

            int i = Examiner.requireInteger(value);
            Examiner.ofRange(1, 4096).refuseExcludeExclude(KEY, i);

            return i;
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
