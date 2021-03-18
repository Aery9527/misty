package org.misty.description;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class MistyDescriptionFinder {

    public static List<MistyDescription> findBySPI() {
        ServiceLoader<MistyDescription> mistyDescriptionServiceLoader = ServiceLoader.load(MistyDescription.class);

        List<MistyDescription> list = new ArrayList<>();
        mistyDescriptionServiceLoader.forEach(list::add);
        return list;
    }

    public static List<MistyDescriptionWrapper> findBySPI(ClassLoader classLoader) throws ClassNotFoundException {
        Class<?> clazz = classLoader.loadClass("org.misty.description.MistyDescription");
        ServiceLoader<?> mistyDescriptionServiceLoader = ServiceLoader.load(clazz, classLoader);

        List<MistyDescriptionWrapper> list = new ArrayList<>();
        mistyDescriptionServiceLoader.forEach((mistyDescription) -> {
            list.add(new MistyDescriptionWrapper(mistyDescription));
        });

        return list;
    }

}
