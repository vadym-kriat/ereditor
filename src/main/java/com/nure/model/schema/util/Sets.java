package com.nure.model.schema.util;

import java.util.Set;

/**
 * Created by Vadim_ on 02.02.2018.
 */
public class Sets {
    public static <T> T getElementByIndex(Set<T> set, int index) {
        int i = 0;
        for (T element : set) {
            if (i == index) {
                return element;
            }
            i++;
        }
        return null;
    }
}
