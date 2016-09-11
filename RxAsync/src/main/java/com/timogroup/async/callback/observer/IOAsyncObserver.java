package com.timogroup.async.callback.observer;

import com.timogroup.async.scheduler.IOAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface IOAsyncObserver<T> extends RxAsyncObserver<T>, IOAsyncScheduler {

}
