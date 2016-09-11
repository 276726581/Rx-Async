package com.timogroup.async.callback.observer;

import com.timogroup.async.scheduler.TrampolineAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface TrampolineAsyncObserver<T> extends RxAsyncObserver<T>, TrampolineAsyncScheduler {

}
