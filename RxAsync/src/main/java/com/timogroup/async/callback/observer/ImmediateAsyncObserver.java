package com.timogroup.async.callback.observer;

import com.timogroup.async.scheduler.ImmediateAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ImmediateAsyncObserver<T> extends RxAsyncObserver<T>, ImmediateAsyncScheduler {

}
