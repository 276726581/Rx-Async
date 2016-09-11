package com.timogroup.async.callback.map;

import com.timogroup.async.scheduler.TrampolineAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface TrampolineAsyncMap<T, R> extends RxAsyncMap<T, R>, TrampolineAsyncScheduler {

}
