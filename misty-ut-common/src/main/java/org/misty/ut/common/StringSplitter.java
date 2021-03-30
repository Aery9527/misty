package org.misty.ut.common;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class StringSplitter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (source == null) {
            return null;
        } else if (source instanceof String[]) {
            return source;
        }

        String[] result = ((String) source).split(",");


        for (int i = 0; i < result.length; i++) {
            String s = result[i];
            if (s.equals("'null'")) {
                result[i] = null;
            } else if (s.equals("''")) {
                result[i] = "";
            } else if (s.equals("' '")) {
                result[i] = " ";
            }
        }

        return result;
    }
}
