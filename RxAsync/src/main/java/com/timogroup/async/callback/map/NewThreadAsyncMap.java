package com.timogroup.async.callback.map;

import com.timogroup.async.scheduler.NewThreadAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface NewThreadAsyncMap<T, R> extends RxAsyncMap<T, R>, NewThreadAsyncScheduler {

}
