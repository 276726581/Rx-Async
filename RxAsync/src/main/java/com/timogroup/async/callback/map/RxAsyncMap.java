package com.timogroup.async.callback.map;

import com.timogroup.async.scheduler.AsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface RxAsyncMap<T, R> extends AsyncScheduler {

    R[] call(T t);
}
