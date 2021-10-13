package org.misty.ut.common;

import org.assertj.core.api.ThrowableAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssertionsTool {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssertionsTool.class);

    public static ThrowableAssert.ThrowingCallable wrap(String term, ThrowableAssert.ThrowingCallable callable) {
        return () -> {
            try {
                callable.call();
            } catch (Throwable t) {
                LOGGER.error(term, t);
                throw t;
            }
        };
    }

}
