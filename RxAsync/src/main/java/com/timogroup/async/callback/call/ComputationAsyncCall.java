package com.timogroup.async.callback.call;

import com.timogroup.async.scheduler.ComputationAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ComputationAsyncCall<T> extends RxAsyncCall<T>, ComputationAsyncScheduler {

}
