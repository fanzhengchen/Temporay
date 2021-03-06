
package com.hcb.jingle.util;

import java.util.List;

public class ListUtil {

    public static boolean isEmpty(final List<? extends Object> list) {
        return null == list || list.size() == 0;
    }

    public static <E> E getIndex(final List<E> list, final int i) {
        if (null == list || i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }
}
