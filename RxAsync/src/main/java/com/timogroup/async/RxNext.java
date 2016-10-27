package com.timogroup.async;

/**
 * Created by TimoRD on 2016/10/27.
 */
public interface RxNext<T, R> {

    R call(T o) throws Exception;
}