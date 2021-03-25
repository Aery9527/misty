package org.misty.util.tool;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.misty.util.error.MistyException;

import java.util.*;

class ArgumentParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"=", ":", "-"})
    public void test_normal_and_separator(String separator) {
        ArgumentParser argumentParser = new ArgumentParser();
        boolean isDefault = "=".equals(separator);
        if (!isDefault) {
            if ("-".equals(separator)) {
                Assertions.assertThatThrownBy(() -> argumentParser.setKeyValuePairSeparator(separator))
                        .isInstanceOf(MistyException.class)
                        .hasMessageContaining(ArgumentParser.KEY_VALUE_PAIR_SEPARATOR_ERROR_MSG);
                return;
            } else {
                argumentParser.setKeyValuePairSeparator(separator);
            }
        }

        String f1 = "kerker";
        String f2 = "haha";
        String f3 = "kerker" + separator + "9527";
        String k1 = "aa";
        String k2 = "bb";
        String k3 = "kerker";
        String k4 = "kerker9527";
        String v1 = "123";
        String v2 = "456";
        String v3 = "123";
        String v4 = "9527";
        String v5 = "";
        String u1 = "-" + separator + "kerker9527";
        String u2 = "-kerker9527";
        String u3 = "rion" + separator + "awesome";
        String u4 = "aery";
        String u5 = "--";
        String u6 = "-";

        Collection<String> args = Arrays.asList(
                // flag, --開頭
                "--" + f1,
                "--" + f2,
                "--" + f2,
                "--" + f3, // 因為是--開頭, 所以不管=, 會視為flag
                // kvPair, -開頭
                "-" + k1 + separator + v1,
                "-" + k1 + separator + v2,
                "-" + k2 + separator + v3,
                "-" + k2 + separator + v3,
                "-" + k3 + separator + v4,
                "-" + k4 + separator + v5, // value可以為空
                // unrecognized
                u1, // key不能為空, 所以無法識別
                u2, // 因為沒有=, 所以無法識別
                u3, // 因為不是--或-開頭, 所以無法識別
                u3, // 因為不是--或-開頭, 所以無法識別
                u4, // 因為不是--或-開頭, 所以無法識別
                u5,
                u6
        );

        ArgumentParser.Result result = argumentParser.parse(args);

        Set<String> flags = result.getFlags();
        System.out.println("flags:" + flags);
        Assertions.assertThat(flags).containsAll(Arrays.asList(f1, f2, f3));

        Map<String, Set<String>> kvPair = result.getKeyValuesPair();
        System.out.println("kvPair:" + kvPair);
        Assertions.assertThat(kvPair.keySet()).containsAll(Arrays.asList(k1, k2, k3, k4));
        Assertions.assertThat(kvPair.get(k1)).containsAll(Arrays.asList(v1, v2));
        Assertions.assertThat(kvPair.get(k2)).containsAll(Collections.singletonList(v3));
        Assertions.assertThat(kvPair.get(k3)).containsAll(Collections.singletonList(v4));
        Assertions.assertThat(kvPair.get(k4)).containsAll(Collections.singletonList(v5));

        Set<String> unrecognized = result.getUnrecognized();
        System.out.println("unrecognized:" + unrecognized);
        Assertions.assertThat(unrecognized).containsAll(Arrays.asList(u1, u2, u3, u4, u5, u6));
    }

    @Test
    public void test_default_trim() {
        ArgumentParser argumentParser = new ArgumentParser();

        String f = "kerker";
        String f1 = "--\"" + f + "\""; // trim "
        String f2 = "-- " + f + " "; // trim blank
        String f3 = "--'" + f + "'"; // trim '
        String f4 = "--\"\"" + f + "\"\""; // deep trim
        String f5 = "--\" " + f + "\" "; // deep trim
        String f6 = "--\"'" + f + "'\""; // deep trim
        String f7 = "--  \"" + f + "  \""; // deep trim
        String f8 = "--  ' " + f + "  ' "; // deep trim

        Collection<String> args = Arrays.asList(
                f1, f2, f3, f4, f5, f6, f7, f8
        );

        ArgumentParser.Result result = argumentParser.parse(args);

        Set<String> flags = result.getFlags();
        System.out.println("flags:" + flags);
        Assertions.assertThat(flags).containsAll(Collections.singletonList(f));


    }


}