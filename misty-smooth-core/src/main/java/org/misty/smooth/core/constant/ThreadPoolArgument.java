package org.misty.smooth.core.constant;

public class ThreadPoolArgument {

    public static final String PREFIX = SmoothCoreArgument.PREFIX + ".thread-pool";

    public static class CoreSize {
        public static final String KEY = PREFIX + ".core-size";

        public static final short PRESET = 2;

        public static final short MIX = PRESET;

        public static final short MAX = 256;
    }

    public static class MaxSize {
        public static final String KEY = PREFIX + ".max-size";

        public static final short PRESET = 1024;

        public static final short MIX = CoreSize.MAX;

        public static final short MAX = 4096;
    }

    public static class AliveSecond {
        public static final String KEY = PREFIX + ".alive-second";

        public static final short PRESET = 60;

        public static final short MIX = PRESET;

        public static final short MAX = 3600;
    }

    public static class NamePrefix {
        public static final String KEY = PREFIX + ".name-prefix";

        public static final String PRESET = "smooth-core-";
    }

    public static class Rotation {
        public static final String KEY = PREFIX + ".rotation";

        public static final short PRESET = 999;

        public static final short MIX = 100;

        public static final short MAX = PRESET;
    }

}
