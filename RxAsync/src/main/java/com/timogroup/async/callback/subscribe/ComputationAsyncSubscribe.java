package com.timogroup.async.callback.subscribe;

import com.timogroup.async.scheduler.ComputationAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ComputationAsyncSubscribe<T> extends RxAsyncSubscribe<T>, ComputationAsyncScheduler {

}
