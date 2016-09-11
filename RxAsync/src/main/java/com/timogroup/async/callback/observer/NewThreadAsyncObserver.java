package com.timogroup.async.callback.observer;

import com.timogroup.async.scheduler.NewThreadAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface NewThreadAsyncObserver<T> extends RxAsyncObserver<T>, NewThreadAsyncScheduler {

}
