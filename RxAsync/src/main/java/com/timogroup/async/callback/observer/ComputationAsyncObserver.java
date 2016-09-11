package com.timogroup.async.callback.observer;

import com.timogroup.async.scheduler.ComputationAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ComputationAsyncObserver<T> extends RxAsyncObserver<T>, ComputationAsyncScheduler {

}
