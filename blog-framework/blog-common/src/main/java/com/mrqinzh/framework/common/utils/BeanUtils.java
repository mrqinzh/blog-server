package com.mrqinzh.framework.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class BeanUtils {

    public static <R, T, P extends Collection<T>> List<R> convertList(P data, Function<T, R> mapping) {
        if (data == null) {
            return null;
        }
        return data.stream().map(mapping).toList();
    }

}
