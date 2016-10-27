package com.timogroup.async;

import java.util.concurrent.Callable;

/**
 * Created by TimoRD on 2016/10/27.
 */
public interface RxCall<V> extends Callable<V> {

    V call() throws Exception;
}