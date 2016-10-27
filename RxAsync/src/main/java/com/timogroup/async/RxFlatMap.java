package com.timogroup.async;

/**
 * Created by TimoRD on 2016/10/27.
 */
public interface RxFlatMap<T, R> {

    R[] call(T t) throws Exception;
}