package com.timogroup.async.callback.subscribe;

import com.timogroup.async.scheduler.TrampolineAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface TrampolineAsyncSubscribe<T> extends RxAsyncSubscribe<T>, TrampolineAsyncScheduler {

}
