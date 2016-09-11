package com.timogroup.async.callback.map;

import com.timogroup.async.scheduler.ComputationAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ComputationAsyncMap<T, R> extends RxAsyncMap<T, R>, ComputationAsyncScheduler {

}
