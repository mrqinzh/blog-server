package com.mrqinzh.framework.common.resp;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectionDataResp<T extends Collection<?>> extends DataResp<T> {

    private CollectionDataResp(T data) {
        super(data);
    }

    /**
     * @param <E> element
     * @param <C> ? extends Collection
     * @return collection<xx> -> collection<xxx>
     */
    public static <E, C extends Collection<E>> CollectionDataResp<?> ok(C data, Function<E, ?> mapping) {
        if (data == null) {
            return ok(null);
        }
        return new CollectionDataResp<>(data.stream().map(mapping).collect(Collectors.toList()));
    }

    public static CollectionDataResp<?> ok(Collection<?> data) {
        return new CollectionDataResp<>(data);
    }

}
