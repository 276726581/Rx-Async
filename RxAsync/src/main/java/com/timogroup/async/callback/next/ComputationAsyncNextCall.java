package com.timogroup.async.callback.next;

import com.timogroup.async.scheduler.ComputationAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ComputationAsyncNextCall<T, R> extends RxAsyncNextCall<T, R>, ComputationAsyncScheduler {

}
