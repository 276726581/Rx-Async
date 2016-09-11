package com.timogroup.async.callback.map;

import com.timogroup.async.scheduler.IOAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface IOAsyncMap<T, R> extends RxAsyncMap<T, R>, IOAsyncScheduler {

}
