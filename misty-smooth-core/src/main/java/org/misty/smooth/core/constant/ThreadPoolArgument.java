package org.misty.smooth.core.constant;

public class ThreadPoolArgument {

    public static final String PREFIX = SmoothCoreArgument.PREFIX + ".thread-pool";

    public static class CoreSize {
        public static final String KEY = PREFIX + ".core-size";

        public static final int PRESET = 2;

        public static final int MIX = PRESET;

        public static final int MAX = 256;
    }

    public static class MaxSize {
        public static final String KEY = PREFIX + ".max-size";

        public static final int PRESET = 1024;

        public static final int MIX = CoreSize.MAX;

        public static final int MAX = PRESET * 4;
    }

    public static class AliveSecond {
        public static final String KEY = PREFIX + ".alive-second";

        public static final int PRESET = 60;

        public static final int MIX = PRESET;

        public static final int MAX = PRESET * 60;
    }

    public static class NamePrefix {
        public static final String KEY = PREFIX + ".name-prefix";

        public static final String PRESET = "smooth-core-";
    }

}
